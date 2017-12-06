package com.boombone7.core.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.boombone7.core.delegates.web.WebDelegate;
import com.boombone7.core.delegates.web.route.Router;
import com.boombone7.core.util.log.OLog;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        OLog.d("shouldOverrideUrlLoading",url);
        return Router.getInstatnce().handleWebUrl(DELEGATE,url);
    }
}
