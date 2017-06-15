package com.towery.ignite.data

import com.towery.ignite.mybatis.entity.TSqlDatamodelItemColumnVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.services.SqlDatamodelService
import org.apache.ignite.Ignition
import org.apache.ignite.cache.CacheMode
import org.apache.ignite.cache.QueryEntity
import org.apache.ignite.cache.QueryIndex
import org.apache.ignite.configuration.CacheConfiguration
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.configuration.MemoryConfiguration
import org.apache.ignite.configuration.MemoryPolicyConfiguration
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.util.*


/** 服务启动执行
 * Created by User on 2017/5/31.
 */
@Component
@Configuration
open class DataNode : CommandLineRunner {
    @Autowired
    private val env: Environment? = null
    @Autowired
    val sqlDatamodelService: SqlDatamodelService? = null

    /*　启动脚本
    *   gradle bootRun --offline -PappArgs="['1']"
    * */
    override fun run(vararg args: String) {
        println(">>>>>>>>>>>>>>>data node启动执行，执行加载数据等操作 start <<<<<<<<<<<<<")

        val modelId: String? = args[0];//模型ID
        val modelVO: TSqlDatamodelVO? = sqlDatamodelService!!.getSqlDatamodel(modelId!!)

        // Create new configuration.
        val cfg = IgniteConfiguration();

        val memCfg = MemoryConfiguration()

        val plc = MemoryPolicyConfiguration()

        plc.name = "dfltPlc"
        plc.initialSize = 4 * 1024L * 1024L * 1024L
        plc.isMetricsEnabled = true

        memCfg.setMemoryPolicies(plc)
        memCfg.defaultMemoryPolicyName = "dfltPlc"



        cfg.memoryConfiguration = memCfg

        val cacheCfg = CacheConfiguration<Any, Any>(modelVO!!.name)
        val sqlDatamodelItemList: List<TSqlDatamodelItemVO> = sqlDatamodelService!!.getSqlDatamodelItemListByModelId(modelId!!)

        var entitys = ArrayList<QueryEntity>()

        for (itemVO: TSqlDatamodelItemVO in sqlDatamodelItemList) {
            val entity = QueryEntity("CustomKey", itemVO.tablename)
            var indexs =ArrayList<QueryIndex>()
            val flds = LinkedHashMap<String, String>()
            val keyFlds = HashSet<String>()
            for (columnVO: TSqlDatamodelItemColumnVO in itemVO.columnList!!) {
                flds.put(columnVO.columnname!!, sqlDatamodelService!!.getDataTypeClassName(columnVO.columntype!!))
                if (columnVO.pk == Integer(1)) {
                    keyFlds.add(columnVO.columnname!!);
                    indexs.add(QueryIndex(columnVO.columnname!!))
                }
            }
            entity.fields = flds
            if (keyFlds.size > 0) {
                entity.setKeyFields(keyFlds)
            } else {
                entity.setKeyType("java.lang.Long")

                //indexs.add(QueryIndex("_key"))
            }

            // End of new settings, nothing else here is DML related
            entity.setIndexes(indexs);
            entitys.add(entity)
        }
        cacheCfg.setQueryEntities(entitys);

        cacheCfg.isCopyOnRead = (env!!.getProperty("ignite.copyOnRead", "false")).toBoolean()

        cacheCfg.isOnheapCacheEnabled = false
        cacheCfg.cacheMode = CacheMode.PARTITIONED
        cacheCfg.memoryPolicyName ="dfltPlc"
        cacheCfg.queryParallelism =32
        cacheCfg.backups=0

        cfg.setCacheConfiguration(cacheCfg);

        cfg.isPeerClassLoadingEnabled = false


        val ipFinder = TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList(env!!.getProperty("ignite.ipAddress", "127.0.0.1:47500..47509")));
        val tcpDiscoverySpi = TcpDiscoverySpi();
        tcpDiscoverySpi.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(tcpDiscoverySpi);

        cfg.publicThreadPoolSize = (env!!.getProperty("ignite.systemThreadPoolSize", "64")).toInt()
        cfg.systemThreadPoolSize = (env!!.getProperty("ignite.systemThreadPoolSize", "64")).toInt()


        // Start Ignite node with given configuration.
        Ignition.start(cfg);
        println(">>>>>>>>>>>>>>>data node启动执行，执行加载数据等操作 end <<<<<<<<<<<<<")
    }
}