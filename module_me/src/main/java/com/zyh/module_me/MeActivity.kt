package com.zyh.module_me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.zyh.lib_router.router.RoutePath
import com.zyh.lib_router.router.RouterManger
import kotlinx.android.synthetic.main.activity_me.*

@Route(path = RoutePath.Me.ACTIVITY_ME)
class MeActivity : AppCompatActivity() {

    @Autowired
    @JvmField
    var userName = ""
    @Autowired
    @JvmField
    var userId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)

        supportActionBar?.title = "Me"

        RouterManger.inject(this)

        tvUser.text = "userName = $userName, userId = $userId"
    }
}