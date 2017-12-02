package com.boombone7.boommall;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.boombone7.core.activities.ProxyActivity;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.launcher.LauncherDelegate;
import com.boombone7.orange.ec.launcher.LauncherScrollDelegate;
import com.boombone7.orange.ec.sign.SignUpDelegate;


/**
 * @author Administrator
 */
public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        //TODO 清单文件设置成竖屏
    }

    @Override
    public OrangeDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
