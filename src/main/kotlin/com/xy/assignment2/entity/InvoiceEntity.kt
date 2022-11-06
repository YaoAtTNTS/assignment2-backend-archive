package com.xy.assignment2.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.xy.assignment2.validator.group.AddGroup
import java.io.Serializable
import javax.validation.constraints.NotBlank

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:02 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@TableName("invoice")
class InvoiceEntity(id: Long?, invoiceNo: String, stockCode: String, description: String?, quantity: Int?, invoiceDate: String, unitPrice: Double?, customerId: String?, country: String) : Serializable {
    @TableId
    private var id: Long? = null

    @NotBlank(message = "Invoice no cannot be empty", groups = [AddGroup::class])
    private val invoiceNo: String

    @NotBlank(message = "Stock code cannot be empty", groups = [AddGroup::class])
    private val stockCode: String
    private val description: String?
    private val quantity: Int?

    @NotBlank(message = "Invoice date cannot be empty", groups = [AddGroup::class])
    private val invoiceDate: String
    private val unitPrice: Double?
    private val customerId: String?
    private val country: String

    companion object {
        private const val serialVersionID = 1L
    }

    fun getId(): Long? = id
    fun getInvoiceNo(): String = invoiceNo
    fun getStockCode(): String = stockCode
    fun getDescription(): String? = description
    fun getQuantity(): Int? = quantity
    fun getInvoiceData(): String = invoiceDate
    fun getUnitPrice(): Double? = unitPrice
    fun getCustomerId(): String? = customerId
    fun getCountry(): String = country


    init {
        this.id = id
        this.invoiceNo = invoiceNo
        this.stockCode = stockCode
        this.description = description
        this.quantity = quantity
        this.invoiceDate = invoiceDate
        this.unitPrice = unitPrice
        this.customerId = customerId
        this.country = country
    }
}