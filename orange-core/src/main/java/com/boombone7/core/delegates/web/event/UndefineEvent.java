package com.boombone7.core.delegates.web.event;

import com.boombone7.core.util.log.OLog;

/**
 * @author Ting
 * @date 2017/12/6.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        OLog.e("UndefineEvent",params);
        return null;
    }
}
