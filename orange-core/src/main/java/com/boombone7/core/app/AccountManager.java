package com.boombone7.core.app;

import com.boombone7.core.util.storage.OrangePreference;

/**
 * @author Ting
 * @date 2017/12/2.
 */

public class AccountManager {
    private static final String SIGN_TAG = "SIGN_TAG";

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        OrangePreference.setAppFlag(SIGN_TAG, state);
    }

    private static boolean isSignIn() {
        return OrangePreference.getAppFlag(SIGN_TAG);
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
