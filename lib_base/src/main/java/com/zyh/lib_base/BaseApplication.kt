package com.zyh.lib_base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.zyh.lib_base.app.ActivityLifecycleCallbacksImpl
import com.zyh.lib_base.app.LoadModuleProxy
import com.zyh.lib_base.utils.ProcessUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/**
 * @author zyh
 * @date 2022/12/13
 * @Description:
 */
open class BaseApplication : Application() {

    private val mCoroutineScope by lazy(mode = LazyThreadSafetyMode.NONE) { MainScope() }

    private val mLoadModuleProxy by lazy(mode = LazyThreadSafetyMode.NONE) { LoadModuleProxy() }

    companion object {
        // 全局Context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var application: BaseApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
        application = this
        mLoadModuleProxy.onAttachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
//        initComponent()
//        initRouter()
        mLoadModuleProxy.onCreate(this)

        // 全局监听 Activity 生命周期
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())

        initDepends()
    }

    /**
     * 初始化第三方依赖
     */
    private fun initDepends() {
        // 开启一个 Default Coroutine 进行初始化不会立即使用的第三方
        mCoroutineScope.launch(Dispatchers.Default) {
            mLoadModuleProxy.initByBackstage()
        }
        // 前台初始化
        val allTimeMillis = measureTimeMillis {
            // 以下只需要在主进程当中初始化 按需要调整
            if (ProcessUtils.isMainProcess(this)) {
                val depends = mLoadModuleProxy.initByFrontDesk()
                var dependInfo: String
                depends.forEach {
                    val dependTimeMillis = measureTimeMillis { dependInfo = it() }
                    e("BaseApplication initDepends: $dependInfo : $dependTimeMillis ms")
                }
            }
        }
        e("BaseApplication 初始化完成 $allTimeMillis ms")
    }

    override fun onTerminate() {
        super.onTerminate()
        mLoadModuleProxy.onTerminate(this)
        mCoroutineScope.cancel()
    }

//    private fun initComponent() {
//        AppConfig.modules.forEach {
//            try {
//                val clazz = Class.forName(it)
//                val baseApp = clazz.newInstance() as IBaseApplication
//                baseApp.init(this)
//            } catch (e: ClassNotFoundException) {
//                e.printStackTrace()
//            } catch (e: IllegalAccessException) {
//                e.printStackTrace()
//            } catch (e: InstantiationException) {
//                e.printStackTrace()
//            }
//        }
//    }

//    private fun initRouter() {
//        if (BuildConfig.DEBUG) {
//            ARouter.openLog()
//            ARouter.openDebug()
//        }
//        ARouter.init(this)
//    }
}