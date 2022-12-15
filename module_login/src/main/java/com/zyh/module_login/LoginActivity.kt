package com.zyh.module_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.router.RouterManger
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = RoutePath.Login.ACTIVITY_LOGIN)
class LoginActivity : AppCompatActivity() {

    var userName = ""
    var userId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Login"

        val bundle = intent?.getBundleExtra("bundle_info")
        if (bundle != null) {
          userName = bundle.getString("userName").toString()
          userId = bundle.getInt("userId")
        }

        tv.text = "拦截到 userName = $userName, userId = $userId"

        btLogin.setOnClickListener {
            RouterManger.navigationActivityParams(
                RoutePath.Me.ACTIVITY_ME,
                "userName" to userName,
                "userId" to userId
            )
            finish()
        }
    }

    //temp interceptor
    override fun onBackPressed() {
    }
}