package com.zyh.lib_base.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zyh.lib_base.utils.ActivityStack
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        if (layoutRes() != 0) setContentView(layoutRes())
        if (isUseEventBus()) {
            EventBus.getDefault().register(this)
        }
        ActivityStack.INSTANCE.addActivity(this)
    }

    open fun layoutRes() = 0

    open fun isUseEventBus(): Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        if (isUseEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        ActivityStack.INSTANCE.removeActivity(this)
    }
}
