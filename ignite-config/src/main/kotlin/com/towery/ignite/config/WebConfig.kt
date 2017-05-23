package com.towery.ignite.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by User on 2016/11/23.
 */
@Configuration
open class WebConfig(@Autowired
                internal var env: Environment) : WebMvcConfigurerAdapter(), EnvironmentAware {

    override fun setEnvironment(environment: Environment) {
        this.env = environment
    }
}
