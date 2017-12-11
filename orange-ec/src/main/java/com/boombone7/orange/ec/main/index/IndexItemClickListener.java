package com.boombone7.orange.ec.main.index;

import android.view.View;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.main.detail.GoodsDetailDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * @author Ting
 * @date 2017/12/5.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;

    private IndexItemClickListener(OrangeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(OrangeDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
