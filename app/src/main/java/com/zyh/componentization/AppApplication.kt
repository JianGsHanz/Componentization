package com.zyh.componentization

import android.app.Application
import com.zyh.lib_base.app.IBaseApplication
import com.zyh.lib_base.e

class AppApplication : IBaseApplication {

    override fun init(application: Application) {
        e("init AppApplication")
    }
}