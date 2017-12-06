package com.boombone7.boommall.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.boombone7.core.delegates.web.event.Event;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
