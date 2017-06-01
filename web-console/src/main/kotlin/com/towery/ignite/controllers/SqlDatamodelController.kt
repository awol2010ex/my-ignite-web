package com.towery.ignite.controllers

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemColumnVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.services.DatabaseService
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
@RequestMapping(value = "/services/SqlDatamodelService")
open class SqlDatamodelController {

    @Autowired
    val sqlDatamodelService: SqlDatamodelService? = null

    @Autowired
    val databaseService: DatabaseService? = null


    @RequestMapping(value = "/getSqlDatamodelList")
    @ResponseBody
    fun getDatabaseList(): List<TSqlDatamodelVO>? {
        return sqlDatamodelService!!.getSqlDatamodelList()
    }

    @RequestMapping(value = "/saveSqlDatamodel")
    @ResponseBody
    fun saveSqlDatamodel(data: String): Boolean {
        var result: Boolean = true
        try {
            var d: TSqlDatamodelVO = JSON.parseObject(data, TSqlDatamodelVO::class.java)

            if (d.id == null || d.id == "") {
                d.id = StringUtils.UUID()
                sqlDatamodelService!!.insertSqlDatamodel(d)
            } else {
                sqlDatamodelService!!.updateSqlDatamodel(d)
            }

        } catch (e: Exception) {
            logger.error("", e)

            result = false
        }

        return result
    }

    @RequestMapping(value = "/deleteSqlDatamodel")
    @ResponseBody
    fun deleteSqlDatamodel(data: String): Boolean {
        var result: Boolean = true
        try {
            val o = JSON.parseObject(data)
            val id = o.getString("id")
            sqlDatamodelService!!.deleteSqlDatamodel(id)

        } catch (e: Exception) {
            logger.error("", e)

            result = false
        }

        return result
    }

    //导入表模型
    @RequestMapping(value = "/importSqlDatamodel")
    @ResponseBody
    fun importSqlDatamodel(data: String): Boolean {
        var result: Boolean = true
        try {
            val o = JSON.parseObject(data)

            val modelId = o.getString("modelId")
            val databaseId = o.getString("databaseId")
            val databaseVO = databaseService!!.getDatabase(databaseId)


            var tableList: JSONArray? = null
            if (o.containsKey("tableList")) {
                tableList = o.getJSONArray("tableList")
                tableList!!.forEach {
                    val j: JSONObject = it as JSONObject;

                    var itemVO = TSqlDatamodelItemVO()
                    itemVO.id = StringUtils.UUID()
                    itemVO.modelid = modelId
                    itemVO.tablename = j.getString("name")
                    //itemVO.insert()
                    sqlDatamodelService!!.insertSqlDatamodelItem(itemVO)

                    val columnList = databaseService.getColumnList(databaseVO!!, j.getString("schem"), j.getString("name"))
                    columnList.forEach {
                        var columnVO = TSqlDatamodelItemColumnVO()
                        columnVO.id =StringUtils.UUID()
                        columnVO.columnname  =it.columnname
                        columnVO.columntype =it.columntype
                        columnVO.modelitemid =itemVO.id
                        columnVO.pk =it.pk
                        //columnVO.insert()
                        sqlDatamodelService!!.insertSqlDatamodelItemColumn(columnVO)
                    };
                }
            }

        } catch (e: Exception) {
            logger.error("", e)

            result = false
        }

        return result
    }


    //模型元素列表
    @RequestMapping(value = "/getSqlDatamodel")
    @ResponseBody
    fun getSqlDatamodel(data: String):TSqlDatamodelVO? {
        var result: TSqlDatamodelVO?  = null
        try {
            val o = JSON.parseObject(data)
            val modelId = o.getString("modelId")
            result =sqlDatamodelService!!.getSqlDatamodel(modelId)
        } catch (e: Exception) {
            logger.error("", e)
        }

        return result
    }


    @RequestMapping(value = "/deleteSqlDatamodelItem")
    @ResponseBody
    fun deleteSqlDatamodelItem(data: String): Boolean {
        var result: Boolean = true
        try {
            val o = JSON.parseObject(data)
            val itemId = o.getString("itemId")
            sqlDatamodelService!!.deleteSqlDatamodelItem(itemId)

        } catch (e: Exception) {
            logger.error("", e)

            result = false
        }

        return result
    }


    companion object {
        var logger = Logger.getLogger(SqlDatamodelController::class.java)
    }
}