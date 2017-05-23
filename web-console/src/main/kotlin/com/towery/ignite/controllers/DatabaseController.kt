package com.towery.ignite.controllers

import com.towery.ignite.mybatis.entity.TDatabaseVO
import com.towery.ignite.services.DatabaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by User on 2017/5/23.
 */
@Controller
@RequestMapping(value="/services/DatabaseService")
open class DatabaseController {

    @Autowired
    val databaseService : DatabaseService? = null
    @RequestMapping(value="/getDatabaseList")
    @ResponseBody
    fun getDatabaseList() :List<TDatabaseVO>?{
       return  databaseService!!.getDatabaseList()
    }
}