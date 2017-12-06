package com.boombone7.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.boombone7.core.I;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(I.RouteKeys.URL, url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return null;
    }

    @Override
    public WebViewClient initWebViewClient() {
        return null;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return null;
    }

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public IWebViewInitializer setInitializer() {
        return null;
    }
}
