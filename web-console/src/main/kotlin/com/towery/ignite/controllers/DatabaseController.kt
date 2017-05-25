package com.towery.ignite.controllers

import com.alibaba.fastjson.JSON
import com.towery.ignite.mybatis.entity.TDatabaseVO
import com.towery.ignite.services.DatabaseService
import com.towery.ignite.services.TableObject
import com.towery.ignite.services.TableObjectList
import com.towery.ignite.utils.StringUtils
import org.apache.log4j.Logger
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

    @RequestMapping(value="/saveDatabase")
    @ResponseBody
    fun saveDatabase(data:String ) :Boolean {
        var result:Boolean =true
        try{
           var d :TDatabaseVO= JSON.parseObject(data, TDatabaseVO::class.java)

            if (d.id ==null || d.id ==""){
                d.id = StringUtils.UUID()
                databaseService!!.insertDatabase(d)
            }
            else{
                databaseService!!.updateDatabase(d)
            }

        }catch (e :Exception){
            logger.error("",e)

            result =false
        }

        return result
    }

    @RequestMapping(value="/deleteDatabase")
    @ResponseBody
    fun deleteDatabase(id:String ) :Boolean {
        var result:Boolean =true
        try{
            databaseService!!.deleteDatabase(id)

        }catch (e :Exception){
            logger.error("",e)

            result =false
        }

        return result
    }



    @RequestMapping(value="/getTableList")
    @ResponseBody
    fun getTableList(data :String) : TableObjectList {
        val o =JSON.parseObject(data)
        val id =o.getString("id")
        val page =o.getIntValue("page")
        val pageSize =o.getIntValue("pageSize")
        val tableName =o.getString("tableName");
        return  databaseService!!.getTableList(databaseService!!.getDatabase(id)!!,page ,pageSize,tableName)
    }


    companion object {
        var logger = Logger.getLogger(DatabaseController::class.java)
    }
}