package com.xy.assignment2.validator

import javax.validation.Validation
import javax.validation.Validator

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:13 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

object ValidatorUtils {
    private var validator: Validator? = null
    fun validateEntity(`object`: Any, vararg groups: Class<*>?): Boolean {
        val constraintViolations = validator!!.validate(`object`, *groups)
        if (!constraintViolations.isEmpty()) {
            val constraint = constraintViolations.iterator().next()
            return false
        }
        return true
    }

    init {
        validator = Validation.buildDefaultValidatorFactory().validator
    }
}