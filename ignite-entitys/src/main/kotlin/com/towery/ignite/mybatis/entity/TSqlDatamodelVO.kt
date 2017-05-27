package com.towery.ignite.mybatis.entity

import com.baomidou.mybatisplus.activerecord.Model
import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableId
import com.baomidou.mybatisplus.annotations.TableName
import java.io.Serializable

/**
 * Created by User on 2017/5/24.
 */
@TableName(value = "t_sql_datamodel")
class TSqlDatamodelVO : Model<TSqlDatamodelVO>(), Serializable {
    @TableId(value = "id")
    var id: String? = null

    @TableField(value = "name")
    var name: String? = null

    @TableField(exist = false)
    var itemList:List<TSqlDatamodelItemVO>?=null

    override fun pkVal(): Serializable {
        return id!!
    }
}

@TableName(value = "t_sql_datamodel_item")
class TSqlDatamodelItemVO : Model<TSqlDatamodelItemVO>(), Serializable {
    @TableId(value = "id")
    var id: String? = null
    @TableField(value = "modelid")
    var modelid: String? = null
    @TableField(value = "tablename")
    var tablename: String? = null

    @TableField(exist = false)
    var columnList :List<TSqlDatamodelItemColumnVO>?=null

    override fun pkVal(): Serializable {
        return id!!
    }
}


@TableName(value = "t_sql_datamodel_item_column")
class TSqlDatamodelItemColumnVO : Model<TSqlDatamodelItemColumnVO>(), Serializable {
    @TableId(value = "id")
    var id: String? = null
    @TableField(value = "columnname")
    var columnname: String? = null
    @TableField(value = "columntype")
    var columntype: String? = null
    @TableField(value = "modelitemid")
    var modelitemid: String? = null
    override fun pkVal(): Serializable {
        return id!!
    }
}