package com.zyh.lib_base.app

/**
 * @author zyh
 * @date 2022/12/14 0014
 * @Description:
 */
object AppConfig {
    const val HOME = "com.zyh.module_home.HomeApplication"
    const val LOGIN = "com.zyh.module_login.LoginApplication"
    const val ME = "com.zyh.module_me.MeApplication"

    val modules = arrayOf(HOME, LOGIN, ME)
}