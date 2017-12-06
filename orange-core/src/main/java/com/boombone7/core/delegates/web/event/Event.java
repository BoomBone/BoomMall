package com.boombone7.core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.delegates.web.WebDelegate;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public OrangeDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
