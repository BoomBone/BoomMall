package com.boombone7.boommall;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.boombone7.boommall.event.ShareEvent;
import com.boombone7.boommall.event.TestEvent;
import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.interceptors.DebugInterceptor;
import com.boombone7.core.net.rx.AddCookieInterceptor;
import com.boombone7.core.util.callback.CallbackManager;
import com.boombone7.core.util.callback.IGlobalCallback;
import com.boombone7.orange.ec.database.DatabaseManager;
import com.boombone7.orange.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;

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
                .withWebEvent("share",new ShareEvent())
                .withWebEvent("test", new TestEvent())
                .configure();
        DatabaseManager.getInstance().init(this);
        Utils.init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        MobSDK.init(this);
        CallbackManager.getInstance()
                .addCallback(I.CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Orange.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Orange.getApplicationContext());
                        }
                    }
                })
                .addCallback(I.CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Orange.getApplicationContext())) {
                            JPushInterface.stopPush(Orange.getApplicationContext());
                        }
                    }
                });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
