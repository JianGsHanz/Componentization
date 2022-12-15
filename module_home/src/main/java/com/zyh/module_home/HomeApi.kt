package com.zyh.module_home

import com.zyh.lib_base.model.BaseModel
import com.zyh.lib_net.RetrofitFactory
import retrofit2.http.GET


interface HomeApi{

    companion object {
        const val BASE_URL = "http://test3.api.gy-idc.com"

        val INSTANCE: HomeApi by lazy {
            RetrofitFactory.getService(HomeApi::class.java, BASE_URL)
        }
    }

    @GET("api/app/user-apps")
    suspend fun getUserApps(): BaseModel<Any>
}