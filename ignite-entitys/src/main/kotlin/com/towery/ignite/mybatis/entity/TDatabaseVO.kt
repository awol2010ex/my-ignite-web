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
    var id: String? = null

    @TableField(value = "name")
    var name: String? = null

    @TableField(value = "jdbcclass")
    var jdbcclass: String? = null

    @TableField(value = "jdbcurl")
    var jdbcurl: String? = null
    @TableField(value = "dbuser")
    var dbuser: String? = null
    @TableField(value = "dbpassword")
    var dbpassword: String? = null

    override fun pkVal(): Serializable {
        return id!!
    }
}