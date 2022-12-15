package com.zyh.lib_base.base

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * @author Created by zyh on 2021/11/23.
 * @description
 */
open class BaseWebActivity : BaseActivity(){

    private var mWebView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    fun init(webView: WebView){
        mWebView = webView
        mWebView?.settings?.apply {
            // 让WebView能够执行javaScript
            javaScriptEnabled = true
            // 让JavaScript可以自动打开windows
            javaScriptCanOpenWindowsAutomatically = true
            // 设置缓存
//            setAppCacheEnabled(true)
            // 设置缓存模式,一共有四种模式
            cacheMode = WebSettings.LOAD_NO_CACHE
            // 支持缩放(适配到当前屏幕)
            setSupportZoom(true)
            // 设置页面的文本缩放
            textZoom = 100
            builtInZoomControls = false
            displayZoomControls = false
            // 将图片调整到合适的大小
            useWideViewPort = true
            // 设置可以被显示的屏幕控制
            displayZoomControls = true
            // 设置默认字体大小
            defaultFontSize = 10
            domStorageEnabled = true
        }
        mWebView?.apply {
            removeJavascriptInterface("searchBoxJavaBridge_")
//            addJavascriptInterface(AndroidInterface(this@BaseWebActivity, this),"android")
//            webViewClient = MyWebViewClient()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView?.canGoBack() == true) {
                mWebView?.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView?.apply {
            (parent as? ViewGroup)?.removeView(this)
            stopLoading()
            webChromeClient = null
            removeAllViews()
            destroy()
        }
    }

}