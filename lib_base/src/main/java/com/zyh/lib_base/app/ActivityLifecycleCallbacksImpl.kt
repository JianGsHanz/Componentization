package com.zyh.lib_base.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zyh.lib_base.e

/**
 * @author zyh
 * @date 2022/12/14 0014
 * @Description: Activity生命周期监听
 */
class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {

    private val TAG = "ActivityLifecycle"

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        e("${activity.javaClass.simpleName} --> onActivityCreated")
    }

    override fun onActivityStarted(activity: Activity) {
        e("${activity.javaClass.simpleName} --> onActivityStarted")
    }

    override fun onActivityResumed(activity: Activity) {
        e("${activity.javaClass.simpleName} --> onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        e("${activity.javaClass.simpleName} --> onActivityPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        e("${activity.javaClass.simpleName} --> onActivityStopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        e("${activity.javaClass.simpleName} --> onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        e("${activity.javaClass.simpleName} --> onActivityDestroyed")
    }
}