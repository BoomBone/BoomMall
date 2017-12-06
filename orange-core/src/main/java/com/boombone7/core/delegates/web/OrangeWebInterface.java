package com.boombone7.core.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * @author Ting
 * @date 2017/12/6
 */

public class OrangeWebInterface {
    private final WebDelegate DELEGATE;

    public OrangeWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static OrangeWebInterface create(WebDelegate delegate){
        return new OrangeWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
