package com.boombone7.orange.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.app.AccountManager;
import com.boombone7.orange.ec.database.DatabaseManager;
import com.boombone7.orange.ec.database.UserProfile;

/**
 * @author Ting
 * @date 2017/12/2.
 */

public class SignHandler {
    public static void onSignIn(String response,ISignListener listener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();
    }
}
