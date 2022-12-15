package com.zyh.lib_base.base

import com.google.gson.JsonParseException
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit


/**
 * 创建并执行协程
 * @param block 协程中执行
 * @param error 错误时执行
 * @return Job
 */
fun launch(block: Block<Unit>, error: Error? = null, cancel: Cancel? = null): Job {
    return async {
        try {
            block.invoke()
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> {
                    cancel?.invoke(e)
                }
                else -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onError(e)
                        error?.invoke(e)
                    }
                }
            }
        }
    }
}

/**
 * 统一处理错误
 * @param e 异常
 */
private fun onError(e: Exception) {
    when (e) {
        is retrofit2.HttpException -> {
            val json = e.response()?.errorBody()?.bytes()?.let { String(it) }
            when (e.code()) {
                401 -> {
                }
                422 -> {
                }
                443 -> {
                }
                500 -> {
                }
            }
        }
        is ConnectException,
        is UnknownHostException -> {
            // 连接失败
        }
        is SocketTimeoutException -> {
            // 请求超时
        }
        is JsonParseException -> {
            // 数据解析错误
        }
        else -> {
            // 其他错误
        }
    }
}

/**
 * 创建并执行协程
 * @param block 协程中执行
 * @return Deferred<T>
 */
fun <T> async(block: Block<T>): Deferred<T> {
    return CoroutineScope(Dispatchers.IO).async { block.invoke() }
}

/**
 * 取消协程
 * @param job 协程job
 */
fun cancelJob(job: Job?) {
    if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
        job.cancel()
    }
}