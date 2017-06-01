package com.towery.ignite

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by User on 2017/5/27.
 */
@SpringBootApplication
open class DataNodeServer {
    companion object {

        @JvmStatic open fun main(args: Array<String>) {
            val app = SpringApplication(DataNodeServer::class.java)
            app.isWebEnvironment=false
            app.run(*args)

        }

    }
}