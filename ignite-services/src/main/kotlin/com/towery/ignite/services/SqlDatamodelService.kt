package com.towery.ignite.services

import com.towery.ignite.mybatis.entity.TSqlDatamodelItemColumnVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.mybatis.mapper.TSqlDatamodelItemColumnMapper
import com.towery.ignite.mybatis.mapper.TSqlDatamodelItemMapper
import com.towery.ignite.mybatis.mapper.TSqlDatamodelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.sql.Date

/**
 * Created by User on 2017/5/24.
 */
@Service
open class SqlDatamodelService {

    @Autowired
    val sqlDatamodelMapper : TSqlDatamodelMapper? = null;
    @Autowired
    val sqlDatamodelItemMapper : TSqlDatamodelItemMapper? = null;
    @Autowired
    val sqlDatamodelItemColumnMapper : TSqlDatamodelItemColumnMapper? = null;

    fun getSqlDatamodelList()  :List<TSqlDatamodelVO>?{
        return sqlDatamodelMapper?.selectList(null)
    }

    fun insertSqlDatamodel( vo :TSqlDatamodelVO){
        sqlDatamodelMapper?.insert(vo)
    }
    fun insertSqlDatamodelItem( vo : TSqlDatamodelItemVO){
        sqlDatamodelItemMapper?.insert(vo)
    }
    fun insertSqlDatamodelItemColumn( vo : TSqlDatamodelItemColumnVO){
        sqlDatamodelItemColumnMapper?.insert(vo)
    }

    fun getSqlDatamodelItemListByModelId( modelId : String) :List<TSqlDatamodelItemVO>{
        var result :List<TSqlDatamodelItemVO>? =sqlDatamodelItemMapper?.selectByMap(mapOf("modelid" to modelId))

        result!!.forEach {
            it.columnList =sqlDatamodelItemColumnMapper?.selectByMap(mapOf("modelitemid" to it.id ))
        }

        return result!!
    }

    fun updateSqlDatamodel( vo :TSqlDatamodelVO){
        sqlDatamodelMapper?.updateById(vo)
    }
    fun deleteSqlDatamodel( id :String){
        sqlDatamodelMapper?.deleteById(id)
    }
    fun deleteSqlDatamodelItem( id :String){
        sqlDatamodelItemMapper?.deleteById(id)
    }
    fun getSqlDatamodel( id :String) :TSqlDatamodelVO?{

        var result :TSqlDatamodelVO?= sqlDatamodelMapper?.selectById(id)
        result!!.itemList =this.getSqlDatamodelItemListByModelId(id)
        return result
    }


    fun getDataTypeClassName( type:String):String{
        if("VACHAR".equals(type)){
            return java.lang.String::class.java.name
        }
        if("CHAR".equals(type)){
            return java.lang.String::class.java.name
        }
        if("CLOB".equals(type)){
            return java.lang.String::class.java.name
        }
        if("INTEGER".equals(type)){
            return java.lang.Integer::class.java.name
        }
        if("TIMESTAMP".equals(type)){
            return java.sql.Timestamp::class.java.name
        }
        if("DECIMAL".equals(type)){
            return java.math.BigDecimal::class.java.name
        }
        if("SMALLINT".equals(type)){
            return  java.lang.Short::class.java.name
        }
        if("DOUBLE".equals(type)){
            return java.lang.Double::class.java.name
        }
        if("DATE".equals(type)){
            return java.util.Date::class.java.name
        }


        return java.lang.String::class.java.name
    }
}