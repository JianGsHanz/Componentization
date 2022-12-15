package com.zyh.module_me.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.service.me.IMeService

/**
 * @author zyh
 * @date 2022/12/14 0014
 * @Description:
 */
@Route(path = RoutePath.Me.SERVICE_ME)
class MeServiceImpl: IMeService {
    override fun getUser(): String = "Hello zyh"

    override fun init(context: Context?) {
    }
}