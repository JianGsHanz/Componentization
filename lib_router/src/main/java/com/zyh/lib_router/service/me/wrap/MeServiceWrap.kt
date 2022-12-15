package com.zyh.lib_router.service.me.wrap

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.service.me.IMeService

/**
 * @author zyh
 * @date 2022/12/14 0014
 * @Description:
 */
object MeServiceWrap {

    @Autowired(name = RoutePath.Me.SERVICE_ME)
    lateinit var service: IMeService

    init {
        ARouter.getInstance().inject(this)
    }

    fun getUser(): String {
        return service.getUser()
    }
//    companion object {
//        val instance = Singleton.holder
//        object Singleton {
//            val holder = MeServiceWrap()
//        }
//    }
}