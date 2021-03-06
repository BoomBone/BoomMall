package com.boombone7.core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.boombone7.core.I;
import com.boombone7.core.delegates.web.event.Event;
import com.boombone7.core.delegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

import static com.boombone7.core.app.Orange.getApplicationContext;

/**
 * @author Ting
 * @date 2017/11/10
 */

public class Configurator {
    private static final HashMap<String, Object> ORANGE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        ORANGE_CONFIGS.put(I.Configkey.CONFIG_READY, false);
        ORANGE_CONFIGS.put(I.Configkey.HANDLER, HANDLER);
    }

    private static class LazyHolder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return LazyHolder.INSTANCE;
    }
    public final void configure(){
        initIcons();
        ORANGE_CONFIGS.put(I.Configkey.CONFIG_READY, true);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i =0;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    final HashMap<String,Object> getConfig(){
        return ORANGE_CONFIGS;
    }

    public final Configurator withApiHost(String apiHost){
        ORANGE_CONFIGS.put(I.Configkey.API_HOST, apiHost);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        ORANGE_CONFIGS.put(I.Configkey.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        ORANGE_CONFIGS.put(I.Configkey.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        ORANGE_CONFIGS.put(I.Configkey.ACTIVITY, activity);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        ORANGE_CONFIGS.put(I.Configkey.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        ORANGE_CONFIGS.put(I.Configkey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        ORANGE_CONFIGS.put(I.Configkey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        ORANGE_CONFIGS.put(I.Configkey.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    //浏览器加载host
    public Configurator withWebHost(String webHost){
        ORANGE_CONFIGS.put(I.Configkey.WEB_HOST, webHost);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) ORANGE_CONFIGS.get(I.Configkey.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final  <T> T getConfiguration(String key){
        checkConfiguration();
        return (T) ORANGE_CONFIGS.get(key);
    }
}
