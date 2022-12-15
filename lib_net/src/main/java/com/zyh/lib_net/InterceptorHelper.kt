package com.zyh.lib_net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor


/**
 *Time:2019/5/30
 *Author:zyh
 *Description:拦截器
 */
object InterceptorHelper {
    var TAG = "Interceptor"

    /**
     * 日志拦截器
     */
    fun getLogInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor { message ->
                Log.w(TAG, "LogInterceptor---------: $message")
            }.setLevel(HttpLoggingInterceptor.Level.BODY)//设置打印数据的级别
        } else {
            HttpLoggingInterceptor { message ->
                Log.w(TAG, "LogInterceptor---------: $message")
            }.setLevel(HttpLoggingInterceptor.Level.NONE)//设置打印数据的级别
        }
    }

    /**
     * 重试拦截器
     *
     * @return
     */
    fun getRetryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val maxRetry = 10//最大重试次数
            var retryNum = 5//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

            val request = chain.request()
            var response = chain.proceed(request)
            while (!response.isSuccessful && retryNum < maxRetry) {
                retryNum++
                response.close()
                response = chain.proceed(request)
            }
            response
        }
    }

    /**
     * 请求头拦截器
     *
     * @return
     */
    fun getHeaderInterceptor(): Interceptor = Interceptor { chain ->

        val originalRequest = chain.request()

//            if (null == originalRequest.body()) {
//                return@Interceptor chain.proceed(originalRequest)
//            }

        val compressedRequest = originalRequest.newBuilder()
//            .removeHeader("User-Agent")
//            .addHeader("User-Agent",getUserAgent())
            .addHeader("Authorization", "Bearer token")
            .addHeader("os-type", "1") //平台类型1-android 2-ios
            .addHeader("org-id", "7")
            .addHeader("version", "") //版本
            .addHeader("channel", "") //渠道
            .addHeader("imei",  "") //imei
            .addHeader("Content-Type", "application/json")
            .addHeader("app-type","7")
            .build()
        return@Interceptor chain.proceed(compressedRequest)
    }
//    private fun getUserAgent():String{
//        var userAgent = ""
//        userAgent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            try {
//                WebSettings.getDefaultUserAgent(MyApplication.context)
//            } catch (e: Exception) {
//                System.getProperty("http.agent")
//            }
//        } else {
//            System.getProperty("http.agent")
//        }
//        var sb = StringBuffer()
//        for (c in userAgent) {
//            if (c <= '\u001f' || c >= '\u007f') {
//                sb.append(String.format("\\u%04x", c.toInt()))
//            } else {
//                sb.append(c)
//            }
//        }
//        return sb.toString()
//    }
//    fun getResponeInterceptor(): Interceptor = Interceptor { it ->
//        val response = it.proceed(it.request())
//        Log.i("response code() ", response.code.toString())
//        var byte = response.body?.bytes()!!
//        var mediatype = response.body?.contentType()
//        if (response.code == 200) {
//            val json = String(byte)
//            var code = JSONObject(json).optString("code")
//            if(code != "0"){
//                GlobalScope.launch(Dispatchers.Main) {
//                    show(JSONObject(json).optString("message"))
//                }
//                response.newBuilder().body(byteArrayOf().toResponseBody(mediatype)).build()
//            }else{
//                response.newBuilder().body(byte.toResponseBody(mediatype)).build()
//            }
//        }else {
//            response.newBuilder().body(byte.toResponseBody(mediatype)).build()
//        }
//    }
}