package com.boombone7.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.web.IPageLoadListener;
import com.boombone7.core.delegates.web.WebDelegate;
import com.boombone7.core.delegates.web.route.Router;
import com.boombone7.core.ui.loader.OrangeLoader;
import com.boombone7.core.util.log.OLog;
import com.boombone7.core.util.storage.OrangePreference;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Orange.getHandler();

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        OLog.d("shouldOverrideUrlLoading", url);
        return Router.getInstatnce().handleWebUrl(DELEGATE, url);
    }

    //获取浏览器cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
        注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = Orange.getConfiguration(I.Configkey.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()){
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.equals("")) {
                    OrangePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                OrangeLoader.stopLoading();
            }
        }, 1000);

    }
}
