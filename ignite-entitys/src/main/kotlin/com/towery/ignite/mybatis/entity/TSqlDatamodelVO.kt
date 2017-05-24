package com.towery.ignite.mybatis.entity

import com.baomidou.mybatisplus.activerecord.Model
import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableId
import com.baomidou.mybatisplus.annotations.TableName
import java.io.Serializable

/**
 * Created by User on 2017/5/24.
 */
@TableName(value = "t_database")
class TSqlDatamodelVO : Model<TSqlDatamodelVO>(), Serializable {
    @TableId(value = "id")
    var id: String? = null

    @TableField(value = "name")
    var name: String? = null
    override fun pkVal(): Serializable {
        return id!!
    }
}