package com.towery.ignite.services

import com.towery.ignite.mybatis.entity.TDatabaseVO
import com.towery.ignite.mybatis.mapper.TDatabaseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
}