package com.towery.ignite.data

import com.towery.ignite.mybatis.entity.TSqlDatamodelItemColumnVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.services.SqlDatamodelService
import org.apache.ignite.Ignition
import org.apache.ignite.cache.QueryEntity
import org.apache.ignite.configuration.CacheConfiguration
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*

/** 服务启动执行
 * Created by User on 2017/5/31.
 */
@Component
@Configuration
open class DataNode : CommandLineRunner {


    @Autowired
    val sqlDatamodelService: SqlDatamodelService? = null
/*　启动脚本
*   gradle bootRun --offline -PappArgs="['1']"
* */
    override  fun run(vararg args: String) {
        println(">>>>>>>>>>>>>>>data node启动执行，执行加载数据等操作 start <<<<<<<<<<<<<")

        val modelId :String?= args[0];//模型ID
        val modelVO :TSqlDatamodelVO?   =sqlDatamodelService!!.getSqlDatamodel(modelId!!)
        val cacheCfg = CacheConfiguration<Any, Any>(modelVO!!.name)
        val  sqlDatamodelItemList:List<TSqlDatamodelItemVO>  =sqlDatamodelService!!.getSqlDatamodelItemListByModelId(modelId!!)

        var entitys =ArrayList<QueryEntity>()
        for(itemVO :TSqlDatamodelItemVO  in sqlDatamodelItemList){
            val entity = QueryEntity("CustomKey", itemVO.tablename)
            val flds = LinkedHashMap<String, String>()
            val keyFlds = HashSet<String>()
            for( columnVO : TSqlDatamodelItemColumnVO in  itemVO.columnList!!){
                flds.put( columnVO.columnname!! ,sqlDatamodelService!!.getDataTypeClassName(columnVO.columntype!!))
                if(columnVO.pk== Integer(1)){
                    keyFlds.add(columnVO.columnname!! );
                }
            }
            entity.fields = flds

            entity.setKeyFields(keyFlds);
            // End of new settings, nothing else here is DML related
            entity.setIndexes(Collections.emptyList());
            entitys.add(entity)
        }
    cacheCfg.setQueryEntities(entitys);
    // Create new configuration.
    val cfg = IgniteConfiguration();
    cfg.setCacheConfiguration(cacheCfg);


    val ipFinder = TcpDiscoveryMulticastIpFinder();
    ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47509"));
    val tcpDiscoverySpi = TcpDiscoverySpi();
    tcpDiscoverySpi.setIpFinder(ipFinder);
    cfg.setDiscoverySpi(tcpDiscoverySpi);
    // Start Ignite node with given configuration.
    Ignition.start(cfg);
        println(">>>>>>>>>>>>>>>data node启动执行，执行加载数据等操作 end <<<<<<<<<<<<<")
    }
}