package com.boombone7.orange.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.orange.ec.R;

/**
 *
 * @author Ting
 * @date 2017/12/7
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
