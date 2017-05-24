package com.towery.ignite.services

import com.towery.ignite.mybatis.entity.TSqlDatamodelVO
import com.towery.ignite.mybatis.mapper.TSqlDatamodelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by User on 2017/5/24.
 */
@Service
class SqlDatamodelService {

    @Autowired
    val sqlDatamodelMapper : TSqlDatamodelMapper? = null;


    fun getSqlDatamodelList()  :List<TSqlDatamodelVO>?{
        return sqlDatamodelMapper?.selectList(null)
    }

    fun insertSqlDatamodel( vo :TSqlDatamodelVO){
        sqlDatamodelMapper?.insert(vo)
    }
    fun updateSqlDatamodel( vo :TSqlDatamodelVO){
        sqlDatamodelMapper?.updateById(vo)
    }
    fun deleteSqlDatamodel( id :String){
        sqlDatamodelMapper?.deleteById(id)
    }
    fun getSqlDatamodel( id :String) :TSqlDatamodelVO?{
        return sqlDatamodelMapper?.selectById(id)
    }

}