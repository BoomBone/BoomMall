package com.boombone7.orange.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.orange.ec.R;

/**
 *
 * @author Ting
 * @date 2017/12/4
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
