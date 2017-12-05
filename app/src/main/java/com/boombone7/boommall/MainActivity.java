package com.boombone7.boommall;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.boombone7.core.I;
import com.boombone7.core.activities.ProxyActivity;
import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.ui.launcher.ILauncherListener;
import com.boombone7.orange.ec.launcher.LauncherDelegate;
import com.boombone7.orange.ec.launcher.LauncherScrollDelegate;
import com.boombone7.orange.ec.main.EcBottomDelegate;
import com.boombone7.orange.ec.sign.ISignListener;
import com.boombone7.orange.ec.sign.SignInDelegate;
import com.boombone7.orange.ec.sign.SignUpDelegate;

import qiu.niorgai.StatusBarCompat;

import static com.boombone7.core.I.OnLauncherFinishTag.NOT_SIGNED;
import static com.boombone7.core.I.OnLauncherFinishTag.SIGNED;


/**
 * @author Administrator
 */
public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Orange.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
        //TODO 清单文件设置成竖屏
    }

    @Override
    public OrangeDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(String tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
