package com.zyh.lib_base.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.text.TextUtils

/**
 * @author Created by zyh on 2020/11/11.
 * @description activityUtils
 */
object ActivityUtils {
    /**
     * 判断某个界面是否在前台
     *
     * @param activity 要判断的Activity
     * @return 是否在前台显示
     */
    fun isForeground(activity: Activity): Boolean {
        return isForeground(activity, activity.javaClass.name)
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    fun isForeground(context: Context?, className: String): Boolean {
        if (context == null || TextUtils.isEmpty(className)) return false
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = am.getRunningTasks(1)
        if (list != null && list.size > 0) {
            val cpn = list[0].topActivity
            if (className == cpn!!.className) return true
        }
        return false
    }

    fun startActivity(a: Activity, b: Activity){
        a.startActivity(Intent(a, b::class.java))
    }

    /**
     * 判断进程是否存活
     */
    fun isProcessExist(context: Context, processName: String): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val lists = am.runningAppProcesses ?: return false
        for (appProcess in lists) {
            if (appProcess.processName == processName) {
                return true
            }
        }
        return false
    }
}