package com.xy.assignment2.dao

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.xy.assignment2.entity.InvoiceEntity
import org.apache.ibatis.annotations.Mapper

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:17 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@Mapper
open interface InvoiceDao : BaseMapper<InvoiceEntity?>