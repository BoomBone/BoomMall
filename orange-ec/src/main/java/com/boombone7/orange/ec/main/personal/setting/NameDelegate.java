package com.boombone7.orange.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.R;

/**
 *
 * @author Ting
 * @date 2017/12/11
 */

public class NameDelegate extends OrangeDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
