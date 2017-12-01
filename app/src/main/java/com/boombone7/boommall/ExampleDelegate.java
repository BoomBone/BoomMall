package com.boombone7.boommall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.delegates.OrangeDelegate;

/**
 *
 * @author Ting
 * @date 2017/12/1
 */

public class ExampleDelegate extends OrangeDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
