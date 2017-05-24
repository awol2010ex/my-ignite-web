package com.towery.ignite.utils

import java.util.*


/**
 * Created by User on 2017/5/23.
 */
class StringUtils {
    companion object {
        fun UUID() :String{
            val uuid = UUID.randomUUID()

            return uuid.toString()
        }
    }
}
