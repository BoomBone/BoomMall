package com.boombone7.core.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.boombone7.core.R;
import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.OrangeDelegate;

/**
 * @author Ting
 * @date 2017/12/4
 */

public abstract class BottomItemDelegate extends OrangeDelegate {
    //再点一次，退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Orange.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return super.onBackPressedSupport();
    }
}
