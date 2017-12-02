package com.boombone7.core.wechat.templates;


import com.boombone7.core.app.Orange;
import com.boombone7.core.wechat.BaseWXEntryActivity;
import com.boombone7.core.wechat.OrangeWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        OrangeWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
