package com.zyh.lib_router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.router.RouterManger

/**
 * @author zyh
 * @date 2022/12/13
 * @Description:
 */
@Interceptor(priority = 6, name = "login_interceptor")
class LoginInterceptor : IInterceptor {
    private var isInterceptor = true //temp

    override fun init(context: Context?) {
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        if (postcard?.path == RoutePath.Me.ACTIVITY_ME && isInterceptor) {
            RouterManger.navigationActivity(RoutePath.Login.ACTIVITY_LOGIN, postcard.extras)
            isInterceptor = false
            callback?.onInterrupt(null)
        } else {
            callback?.onContinue(postcard)
        }
    }
}