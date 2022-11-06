package com.xy.assignment2.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.xy.assignment2.dao.InvoiceDao
import com.xy.assignment2.entity.InvoiceEntity
import com.xy.assignment2.exception.SQLOperationException
import com.xy.assignment2.service.InvoiceService
import com.xy.assignment2.validator.ValidatorUtils
import com.xy.assignment2.validator.group.AddGroup
import org.springframework.stereotype.Service

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:20 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@Service("invoiceService")
class InvoiceServiceImpl : ServiceImpl<InvoiceDao?, InvoiceEntity?>(), InvoiceService {

    override fun queryAll(params: Map<String?, String?>?): List<InvoiceEntity?> {
        val wrapper: QueryWrapper<InvoiceEntity> = QueryWrapper<InvoiceEntity>()
        return list(wrapper)
    }

    @Throws(SQLOperationException::class)
    override fun saveInvoice(invoice: InvoiceEntity) {
        val isValid: Boolean = ValidatorUtils.validateEntity(invoice, AddGroup::class.java)
        if (!isValid) {
            throw SQLOperationException("Invalid invoice entity.")
        }
        save(invoice)
    }
}