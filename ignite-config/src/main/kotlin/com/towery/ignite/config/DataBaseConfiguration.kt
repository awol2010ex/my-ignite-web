package com.towery.ignite.config


import com.alibaba.druid.pool.DruidDataSource
import org.apache.log4j.Logger
import org.springframework.boot.bind.RelaxedPropertyResolver
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.sql.SQLException
import javax.sql.DataSource

//数据库配置
@Configuration
open class DataBaseConfiguration : EnvironmentAware {
    private var propertyResolver: RelaxedPropertyResolver? = null

    override fun setEnvironment(env: Environment) {
        this.propertyResolver = RelaxedPropertyResolver(env, "spring.datasource.")
    }

    @Bean(name = arrayOf("base"), destroyMethod = "close", initMethod = "init")
    @Throws(SQLException::class)
    open fun writeDataSource(): DataSource {
        logger.info("注入druid")


        val dataSource = DruidDataSource()
        dataSource.url = propertyResolver!!.getProperty("url")
        dataSource.username = propertyResolver!!.getProperty("username")//用户名
        dataSource.password = propertyResolver!!.getProperty("password")//密码
        dataSource.driverClassName = propertyResolver!!.getProperty("driver-class-name")
        dataSource.initialSize = Integer.parseInt(propertyResolver!!.getProperty("initialSize"))
        dataSource.maxActive = Integer.parseInt(propertyResolver!!.getProperty("maxActive"))
        dataSource.minIdle = Integer.parseInt(propertyResolver!!.getProperty("minIdle"))
        dataSource.maxWait = Integer.parseInt(propertyResolver!!.getProperty("maxWait")).toLong()
        dataSource.timeBetweenEvictionRunsMillis = Integer.parseInt(propertyResolver!!.getProperty("timeBetweenEvictionRunsMillis")).toLong()
        dataSource.minEvictableIdleTimeMillis = Integer.parseInt(propertyResolver!!.getProperty("minEvictableIdleTimeMillis")).toLong()
        dataSource.validationQuery = propertyResolver!!.getProperty("validationQuery")
        dataSource.isTestOnBorrow = java.lang.Boolean.getBoolean(propertyResolver!!.getProperty("testOnBorrow"))
        dataSource.isTestWhileIdle = java.lang.Boolean.getBoolean(propertyResolver!!.getProperty("testWhileIdle"))
        dataSource.isTestOnReturn = java.lang.Boolean.getBoolean(propertyResolver!!.getProperty("testOnReturn"))
        dataSource.isPoolPreparedStatements = java.lang.Boolean.getBoolean(propertyResolver!!.getProperty("poolPreparedStatements"))
        dataSource.maxPoolPreparedStatementPerConnectionSize = Integer.parseInt(propertyResolver!!.getProperty("maxPoolPreparedStatementPerConnectionSize"))
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        dataSource.setFilters(propertyResolver!!.getProperty("filters"))
        return dataSource
    }

    companion object {
        var logger = Logger.getLogger(DataBaseConfiguration::class.java)
    }

}