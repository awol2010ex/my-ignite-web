package com.towery.ignite.controllers

import com.alibaba.fastjson.JSON
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.services.SqlDatamodelService
import com.towery.ignite.utils.StringUtils
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by User on 2017/5/24.
 */
@Controller
@RequestMapping(value="/services/SqlDatamodelService")
open class SqlDatamodelController {

    @Autowired
    val sqlDatamodelService : SqlDatamodelService? = null
    @RequestMapping(value="/getSqlDatamodelList")
    @ResponseBody
    fun getDatabaseList() :List<TSqlDatamodelVO>?{
        return  sqlDatamodelService!!.getSqlDatamodelList()
    }

    @RequestMapping(value="/saveDatabase")
    @ResponseBody
    fun saveSqlDatamodel(data:String ) :Boolean {
        var result:Boolean =true
        try{
            var d :TSqlDatamodelVO= JSON.parseObject(data, TSqlDatamodelVO::class.java)

            if (d.id ==null || d.id ==""){
                d.id = StringUtils.UUID()
                sqlDatamodelService!!.insertSqlDatamodel(d)
            }
            else{
                sqlDatamodelService!!.updateSqlDatamodel(d)
            }

        }catch (e :Exception){
            logger.error("",e)

            result =false
        }

        return result
    }

    @RequestMapping(value="/deleteSqlDatamodel")
    @ResponseBody
    fun deleteSqlDatamodel(id:String ) :Boolean {
        var result:Boolean =true
        try{
            sqlDatamodelService!!.deleteSqlDatamodel(id)

        }catch (e :Exception){
            logger.error("",e)

            result =false
        }

        return result
    }


    companion object {
        var logger = Logger.getLogger(SqlDatamodelController::class.java)
    }
}