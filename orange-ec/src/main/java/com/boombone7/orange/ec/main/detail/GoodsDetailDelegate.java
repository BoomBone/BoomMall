package com.boombone7.orange.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author Ting
 * @date 2017/12/5.
 */

public class GoodsDetailDelegate extends OrangeDelegate {

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
