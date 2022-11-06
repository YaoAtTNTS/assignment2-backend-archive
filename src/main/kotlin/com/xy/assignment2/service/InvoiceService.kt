package com.xy.assignment2.service

import com.baomidou.mybatisplus.extension.service.IService
import com.xy.assignment2.entity.InvoiceEntity
import com.xy.assignment2.exception.SQLOperationException
import com.xy.assignment2.utils.PageUtils

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:20 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */


open interface InvoiceService : IService<InvoiceEntity?> {

    fun queryAll(params: Map<String?, String?>?): PageUtils

    @Throws(SQLOperationException::class)
    fun saveInvoice(invoice: InvoiceEntity)
}