package com.boombone7.boommall.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.delegates.web.event.Event;
import com.boombone7.core.util.log.OLog;
import com.mob.MobSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Ting on 2018/1/12.
 */

public class ShareEvent extends Event {
    @Override
    public String execute(String params) {
        OLog.json("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        MobSDK.init(getContext());
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());
        return null;
    }
}
