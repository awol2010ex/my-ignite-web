package com.towery.ignite.mybatis.entity


import com.baomidou.mybatisplus.activerecord.Model
import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableId
import com.baomidou.mybatisplus.annotations.TableName

import java.io.Serializable

/**
 * Created by User on 2016/12/30.
 */
@TableName(value = "t_database")
class TDatabaseVO : Model<TDatabaseVO>(), Serializable {

    @TableId(value = "id")
    val id: String? = null

    @TableField(value = "name")
    val name: String? = null

    @TableField(value = "jdbcclass")
    val jdbcclass: String? = null

    @TableField(value = "jdbcurl")
    val jdbcurl: String? = null
    @TableField(value = "dbuser")
    private val dbuser: String? = null
    @TableField(value = "dbpassword")
    val dbpassword: String? = null

    override fun pkVal(): Serializable {
        return id!!
    }
}