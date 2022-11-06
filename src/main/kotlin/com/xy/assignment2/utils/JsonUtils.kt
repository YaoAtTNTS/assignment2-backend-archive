package com.xy.assignment2.utils

import com.alibaba.fastjson.JSON

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:16 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */


object JsonUtils {
    fun toJsonString(key: String, value: Any): String {
        val res: MutableMap<String, Any> = HashMap()
        res[key] = value
        return JSON.toJSONString(res)
    }
}