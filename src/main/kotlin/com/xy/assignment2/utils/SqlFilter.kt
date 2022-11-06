package com.xy.assignment2.utils

import org.apache.commons.lang.StringUtils
import java.util.*

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:16 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

object SqlFilter {
    fun sqlInject(sql: String): String? {
        var sql = sql
        if (StringUtils.isBlank(sql)) {
            return null
        }
        sql = StringUtils.replace(sql, "'", "")
        sql = StringUtils.replace(sql, "\"", "")
        sql = StringUtils.replace(sql, ";", "")
        sql = StringUtils.replace(sql, "\\", "")
        sql = sql.lowercase(Locale.getDefault())
        val keywords = arrayOf("master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop")
        for (keyword in keywords) {
            if (sql.contains(keyword!!)) {
                throw RuntimeException("Invalid characters in sql command.")
            }
        }
        return sql
    }
}