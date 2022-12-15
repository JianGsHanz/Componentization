package com.zyh.module_home

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zyh.lib_base.base.BaseVmActivity
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.router.RouterManger
import com.zyh.lib_router.service.me.wrap.MeServiceWrap
import kotlinx.android.synthetic.main.activity_home.*

@Route(path = RoutePath.Home.ACTIVITY_HOME)
class HomeActivity : BaseVmActivity<HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_home)

        supportActionBar?.title = "Home"

        tv.text = "IProvider ${MeServiceWrap.getUser()}"

        btGoMe.setOnClickListener {
            RouterManger.navigationActivityParams(
                RoutePath.Me.ACTIVITY_ME,
                "userName" to "Jack",
                "userId" to 5421264
            )
        }
    }
}