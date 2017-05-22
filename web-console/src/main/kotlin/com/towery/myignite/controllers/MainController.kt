package com.towery.myignite.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping



/**
 * Created by User on 2017/5/22.
 */
@Controller
@RequestMapping(value="/")
open class MainController {
    @RequestMapping
    fun main(): String {
        return "main"
    }
}