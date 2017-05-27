package com.towery.ignite.mybatis.mapper

import com.baomidou.mybatisplus.mapper.BaseMapper
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemColumnVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelItemVO
import com.towery.ignite.mybatis.entity.TSqlDatamodelVO

/**
 * Created by User on 2017/5/24.
 */
interface TSqlDatamodelMapper : BaseMapper<TSqlDatamodelVO> {
}
interface TSqlDatamodelItemMapper : BaseMapper<TSqlDatamodelItemVO> {
}
interface TSqlDatamodelItemColumnMapper : BaseMapper<TSqlDatamodelItemColumnVO> {
}