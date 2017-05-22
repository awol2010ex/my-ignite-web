package com.towery.ignite.config

import com.alibaba.druid.support.http.StatViewServlet
import com.alibaba.druid.support.http.WebStatFilter
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean


/**
 * Created by User on 2017/5/22.
 */
open class DruidConfiguration {
    @Bean
    open fun druidServlet(): ServletRegistrationBean {
        return ServletRegistrationBean(StatViewServlet(), "/druid/*")
    }


    @Bean
    open fun filterRegistrationBean(): FilterRegistrationBean {
        val filterRegistrationBean = FilterRegistrationBean()
        filterRegistrationBean.setFilter(WebStatFilter())
        filterRegistrationBean.addUrlPatterns("/*")
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")
        return filterRegistrationBean
    }
}