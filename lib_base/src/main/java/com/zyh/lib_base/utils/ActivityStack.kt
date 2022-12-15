package com.zyh.lib_base.utils

import android.app.Activity

/**
 * @author Created by zyh on 2020/11/02.
 * @description activity管理
 */

class ActivityStack private constructor(){
    companion object{
        private val list = ArrayList<Activity>()
        val INSTANCE: ActivityStack by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityStack()
        }
    }

    fun addActivity(activity: Activity){
        list.add(activity)
    }

    fun removeActivity(activity: Activity){
        list.remove(activity)
    }

    fun finishActivity(activity: Activity?){
        if (list.contains(activity)) activity?.finish()
    }

    fun finishActivity(activity: Class<*>){
        list.forEach {
            if (it.javaClass.name == activity.name){
                it.finish()
            }
        }
    }

    fun finishAll(){
        list.forEach {
            it.finish()
        }
    }

    fun existActivity(activity: Activity): Boolean{
        return if (list.isNullOrEmpty()) false
        else list.contains(activity)
    }
}