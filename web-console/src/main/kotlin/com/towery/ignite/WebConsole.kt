package com.towery.ignite

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer


/**
 * Created by User on 2017/5/22.
 */


@SpringBootApplication
open class WebConsole : SpringBootServletInitializer() {

    // Used when deploying to a standalone servlet container
    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(WebConsole::class.java)
    }

    companion object {

        @JvmStatic open fun main(args: Array<String>) {
            SpringApplication.run(WebConsole::class.java, *args)
        }
    }
}
