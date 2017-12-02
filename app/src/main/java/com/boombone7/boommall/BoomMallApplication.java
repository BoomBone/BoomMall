package com.boombone7.boommall;

import android.app.Application;

import com.boombone7.core.app.Orange;
import com.boombone7.core.net.interceptors.DebugInterceptor;
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
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
