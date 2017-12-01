package com.boombone7.orange.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.util.timer.BaseTimerTask;
import com.boombone7.core.util.timer.ITimerListener;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ting
 * @date 2017/12/1
 */

public class LauncherDelegate extends OrangeDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;
    private int mCount = 5;

    private Timer mTimer = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if(mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer = null;
                        }
                    }
                }
            }
        });
    }
}
