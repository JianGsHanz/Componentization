package com.zyh.lib_router.router

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.zyh.lib_router.service.me.IMeService
import java.io.Serializable

/**
 * @author zyh
 * @date 2022/12/13
 * @Description:路由管理
 */
object RouterManger {

    fun inject(context: Context) {
        ARouter.getInstance().inject(context)
    }

    inline fun <reified T> getProvider(): T{
       return ARouter.getInstance().navigation(T::class.java)
    }

    /**
     *普通跳转
     */
    fun navigationActivity(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    /**
     * 回调跳转
     */
    fun navigationActivity(
        path: String,
        context: Activity,
        requestCode: Int,
        vararg params: Pair<String, Any>
    ) {
        ARouter.getInstance().build(path).putExtras(*params).navigation(context, requestCode)
    }

    fun navigationActivity(path: String, bundle: Bundle) {
        ARouter.getInstance().build(path)
            .withBundle("bundle_info", bundle).navigation()
    }

    /**
     * 携带参数跳转
     */
    fun navigationActivityParams(path: String, vararg params: Pair<String, Any>) {
        ARouter.getInstance().build(path)
            .putExtras(*params).navigation()
    }

    private fun Postcard.putExtras(vararg params: Pair<String, Any>): Postcard {
        if (params.isEmpty()) return this
        params.forEach { (key, value) ->
            when (value) {
                is Int -> withInt(key, value)
                is String -> withString(key, value)
                is Boolean -> withBoolean(key, value)
                is Char -> withChar(key, value)
                is Byte -> withByte(key, value)
                is Float -> withFloat(key, value)
                is Bundle -> withBundle(key, value)
                is Double -> withDouble(key, value)
                is ByteArray -> withByteArray(key, value)
                is Serializable -> withSerializable(key, value)
                is ArrayList<*> -> {
                    withStringArrayList(key, value as ArrayList<String>)
                }
                is Object -> withObject(key, value)
            }
        }
        return this
    }
}