package com.zyh.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus


/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    open fun layoutRes() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isUseEventBus()){
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isUseEventBus()){
            EventBus.getDefault().unregister(this)
        }
    }

    open fun isUseEventBus(): Boolean = false

}
