package com.boombone7.boommall;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.boombone7.boommall.event.TestEvent;
import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.interceptors.DebugInterceptor;
import com.boombone7.core.net.rx.AddCookieInterceptor;
import com.boombone7.orange.ec.database.DatabaseManager;
import com.boombone7.orange.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 *
 * @author Ting
 * @date 2017/12/1
 */

public class BoomMallApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Orange.init(getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("orange")
                .withApiHost(I.URL.HOST_API)
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("https://www.baidu.com/")
                .withWebEvent("test",new TestEvent())
                .configure();
        DatabaseManager.getInstance().init(this);
        Utils.init(this);
    }
}
