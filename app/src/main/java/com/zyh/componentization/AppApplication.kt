package com.zyh.componentization

import android.app.Application
import android.content.Context
import com.google.auto.service.AutoService
import com.zyh.lib_base.app.ApplicationLifecycle
import com.zyh.lib_base.e

@AutoService(ApplicationLifecycle::class)
class AppApplication: ApplicationLifecycle {


    /**
     * 同[Application.attachBaseContext]
     * @param context Context
     */
    override fun onAttachBaseContext(context: Context) {
    }

    /**
     * 同[Application.onCreate]
     * @param application Application
     */
    override fun onCreate(application: Application) {
        e("AppApplication onCreate:$application")
    }

    /**
     * 同[Application.onTerminate]
     * @param application Application
     */
    override fun onTerminate(application: Application) {}

    /**
     * 主线程前台初始化
     * @return MutableList<() -> String> 初始化方法集合
     */
    override fun initByFrontDesk(): MutableList<() -> String> {
        val list = mutableListOf<() -> String>()
        return list
    }

    /**
     * 不需要立即初始化的放在这里进行后台初始化
     */
    override fun initByBackstage() {

    }

}