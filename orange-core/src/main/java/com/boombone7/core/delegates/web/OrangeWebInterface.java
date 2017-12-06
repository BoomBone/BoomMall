package com.boombone7.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.boombone7.core.delegates.web.event.Event;
import com.boombone7.core.delegates.web.event.EventManager;
import com.boombone7.core.util.log.OLog;

/**
 * @author Ting
 * @date 2017/12/6
 */

public class OrangeWebInterface {
    private final WebDelegate DELEGATE;

    public OrangeWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static OrangeWebInterface create(WebDelegate delegate){
        return new OrangeWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        OLog.d("WEB_EVENT",params);
        if (event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
