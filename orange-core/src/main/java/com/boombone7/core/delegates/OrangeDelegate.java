package com.boombone7.core.delegates;

/**
 * Created by Ting on 2017/12/1.
 */

public abstract class OrangeDelegate extends PermissionCheckerDelegate {

    public <T extends OrangeDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
