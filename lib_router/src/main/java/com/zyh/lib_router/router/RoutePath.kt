package com.zyh.lib_router.router

/**
 * @author zyh
 * @date 2022/12/13
 * @Description:Router路径
 */
object RoutePath {

    object Home {
        private const val HOME = "/home"
        const val ACTIVITY_HOME = "$HOME/home_activity"
        const val SERVICE_HOME = "${HOME}/home_service"
    }

    object Login {
        private const val LOGIN = "/login"
        const val ACTIVITY_LOGIN = "$LOGIN/login_activity"
    }

    object Me {
        private const val ME = "/me"
        const val ACTIVITY_ME = "$ME/me_activity"
        const val SERVICE_ME = "$ME/me_service"
    }
}