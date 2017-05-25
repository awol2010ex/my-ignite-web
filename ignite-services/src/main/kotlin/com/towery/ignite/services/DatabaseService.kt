package com.towery.ignite.services

import com.towery.ignite.mybatis.entity.TDatabaseVO
import com.towery.ignite.mybatis.mapper.TDatabaseMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet
import java.util.*
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

/**
 * Created by User on 2017/5/23.
 */
@Service
open class DatabaseService {


    @Autowired
    val databaseMapper : TDatabaseMapper? = null;


    fun getDatabaseList()  :List<TDatabaseVO>?{
        return databaseMapper?.selectList(null)
    }

    fun insertDatabase( vo :TDatabaseVO){
        databaseMapper?.insert(vo)
    }
    fun updateDatabase( vo :TDatabaseVO){
        databaseMapper?.updateById(vo)
    }
    fun deleteDatabase( id :String){
        databaseMapper?.deleteById(id)
    }
    fun getDatabase( id :String) :TDatabaseVO?{
        return databaseMapper?.selectById(id)
    }

    fun getTableList(vo :TDatabaseVO  , page:Int ,pageSize:Int ,tableName :String) : TableObjectList {
        var result:java.util.ArrayList<TableObject> = java.util.ArrayList<TableObject>()
        var rs: ResultSet? = null
        var conn :Connection?=null
        var rows :Int=0
        try {
            Class.forName(vo.jdbcclass);
            var conn = DriverManager.getConnection(vo.jdbcurl, vo.dbuser, vo.dbpassword) as Connection
            var queryTableName :String? =tableName
            if("".equals(queryTableName)){
                queryTableName = null
            }
            else{
                queryTableName ="%"+queryTableName+"%"
            }
            rs = conn.metaData.getTables(null, vo.dbuser!!.toUpperCase(),queryTableName, arrayOf("TABLE", "VIEW"))



            while(rs.next() ){
                rows++
                if(rows>=(page-1)*pageSize+1 && rows<=page*pageSize) {
                    val t: TableObject = TableObject(schem = rs!!.getString("TABLE_SCHEM"), name = rs!!.getString("TABLE_NAME"), type = rs!!.getString("TABLE_TYPE"));

                    result.add(t)
                }
                else{
                    continue
                }

            }

        }catch (e:Exception){
            logger.error("",e)
        } finally {
            if(rs!=null){
                rs.close()
            }
            if(conn!=null){
                conn.close()
            }
        }

        return TableObjectList(totalcount=rows ,objectList=result)
    }


    companion object {
        var logger = LoggerFactory.getLogger (DatabaseService::class.java)


    }
}


class TableObject(val schem: String, val type: String, val name: String)
class TableObjectList(val totalcount :Int ,var objectList :java.util.ArrayList<TableObject>)