package com.xy.assignment2.validator.group

import javax.validation.GroupSequence

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:14 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */


@GroupSequence(AddGroup::class, UpdateGroup::class)
open interface Group
