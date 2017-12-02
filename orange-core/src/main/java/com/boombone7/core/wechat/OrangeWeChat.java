package com.boombone7.core.wechat;

import android.app.Activity;

import com.boombone7.core.app.Orange;
import com.boombone7.core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.boombone7.core.I.Configkey.ACTIVITY;
import static com.boombone7.core.I.Configkey.WE_CHAT_APP_ID;
import static com.boombone7.core.I.Configkey.WE_CHAT_APP_SECRET;

/**
 * Created by 傅令杰 on 2017/4/25
 */

public class OrangeWeChat {
    public static final String APP_ID = Orange.getConfiguration(WE_CHAT_APP_ID);
    public static final String APP_SECRET = Orange.getConfiguration(WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final OrangeWeChat INSTANCE = new OrangeWeChat();
    }

    public static OrangeWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private OrangeWeChat() {
        final Activity activity = Orange.getConfiguration(ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public OrangeWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
