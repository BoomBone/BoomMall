package com.boombone7.core.util.timer;

import java.util.TimerTask;

/**
 * @author Ting
 * @date 2017/12/1
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
