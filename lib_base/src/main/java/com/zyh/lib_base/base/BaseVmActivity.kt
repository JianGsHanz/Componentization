package com.zyh.lib_base.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType


abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    protected open var mViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        // 因为Activity恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        if (savedInstanceState == null) {
            initData()
        }
    }

    private fun initViewModel() {
        if (mViewModel == null) {
            val type = javaClass.genericSuperclass
            val modelClass: Class<BaseViewModel> = if (type is ParameterizedType) {
                type.actualTypeArguments[0] as Class<BaseViewModel>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseViewModel::class.java
            }
            mViewModel = ViewModelProvider(this).get(modelClass) as VM
        }
    }

    open fun initData() {
        // Override if need
    }

}
