package com.towery.ignite.config


import com.baomidou.mybatisplus.MybatisConfiguration
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean
import org.apache.ibatis.mapping.DatabaseIdProvider
import org.apache.ibatis.plugin.Interceptor
import org.apache.log4j.Logger
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.MybatisProperties
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils
import javax.sql.DataSource

/**
 * Created by User on 2016/11/23.
 */
@Configuration
@MapperScan(basePackages = arrayOf(MyBatisConfig.MYBATIS_PACKAGE,MyBatisConfig.ENTITY_PACKAGE), sqlSessionFactoryRef = "BaseSqlSessionFactory")
open class MyBatisConfig {


    @Autowired
    private val properties: MybatisProperties? = null

    @Autowired
    private val resourceLoader = DefaultResourceLoader()

    @Autowired(required = false)
    private val interceptors: Array<Interceptor>? = null

    @Autowired(required = false)
    private val databaseIdProvider: DatabaseIdProvider? = null


    @Bean(name = arrayOf("BaseSqlSessionFactory"))
    @Throws(Exception::class)
    open fun baseSqlSessionFactory(@Qualifier("base") dataSource: DataSource): MybatisSqlSessionFactoryBean {
        val mybatisPlus = MybatisSqlSessionFactoryBean()
        mybatisPlus.setDataSource(dataSource)
        mybatisPlus.vfs = SpringBootVFS::class.java
        if (StringUtils.hasText(this.properties!!.configLocation)) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.configLocation))
        }
        mybatisPlus.setConfiguration(properties.configuration)
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors)
        }
        val mc = MybatisConfiguration()
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver::class.java)
        mybatisPlus.setConfiguration(mc)
        if (this.databaseIdProvider != null) {
            mybatisPlus.databaseIdProvider = this.databaseIdProvider
        }
        if (StringUtils.hasLength(this.properties.typeAliasesPackage)) {
            mybatisPlus.setTypeAliasesPackage(this.properties.typeAliasesPackage)
        }
        if (StringUtils.hasLength(this.properties.typeHandlersPackage)) {
            mybatisPlus.setTypeHandlersPackage(this.properties.typeHandlersPackage)
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations())
        }
        return mybatisPlus
    }

    companion object {
        var logger = Logger.getLogger(MyBatisConfig::class.java)


        const internal val MYBATIS_PACKAGE = "com.towery.ignite.mybatis"
        const internal val ENTITY_PACKAGE = "com.towery.ignite.mybatis.entity"
    }
}
