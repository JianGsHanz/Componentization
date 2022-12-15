package com.zyh.lib_net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *Time:2019/5/30
 *Author:zyh
 *Description:定义retrofit
 */
object RetrofitFactory {
    //读取时间
    private const val DEFAULT_READ_TIMEOUT_MILLIS = (20 * 1000).toLong()

    //写入时间
    private const val DEFAULT_WRITE_TIMEOUT_MILLIS = (20 * 1000).toLong()

    //超时时间
    private const val DEFAULT_CONNECT_TIMEOUT_MILLIS = (20 * 1000).toLong()

    //最大缓存
    private const val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = (20 * 1024 * 1024).toLong()//设置20M

    //初始化Retrofit
    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S = Retrofit.Builder()
        //baseurl
        .baseUrl(baseUrl)
        //JSON转换器,使用Gson来转换
        .addConverterFactory(GsonConverterFactory.create())
        //RxJava2适配器
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(serviceClass)


    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //设置连接超时时间
        .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
        //设置读取超时时间
        .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
        //设置写入超时时间
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
        //默认重试一次
//            .retryOnConnectionFailure(true)
        .addInterceptor(InterceptorHelper.getHeaderInterceptor())
        //添加日志拦截器
        .addInterceptor(InterceptorHelper.getLogInterceptor())
        //添加缓存拦截器
//            .addInterceptor()
        //添加重试拦截器
//            .addInterceptor(InterceptorHelper.getRetryInterceptor())
//            .addInterceptor(InterceptorHelper.getResponeInterceptor())
        //缓存
//            .cache(getCache(BaseApplication.context))
        .build()

    /**
     * 获取缓存路径
     *
     * @return cache
     */
//    private fun getCache(context: Context): Cache {
//        var cache: Cache? = null
//        val path = FileUtils.createRootPath(context)
//        val baseDir = File(path)
//        val cacheDir = File(baseDir, "CopyCache")
//        cache = Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)
//        return cache
//    }
}