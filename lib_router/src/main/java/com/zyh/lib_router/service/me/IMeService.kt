package com.zyh.lib_router.service.me

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author zyh
 * @date 2022/12/14 0014
 * @Description:
 */
interface IMeService: IProvider {
    fun getUser(): String
}