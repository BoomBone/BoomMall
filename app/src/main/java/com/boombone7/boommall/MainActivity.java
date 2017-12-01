package com.boombone7.boommall;


import com.boombone7.core.activities.ProxyActivity;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.launcher.LauncherDelegate;


/**
 * @author Administrator
 */
public class MainActivity extends ProxyActivity {


    @Override
    public OrangeDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
