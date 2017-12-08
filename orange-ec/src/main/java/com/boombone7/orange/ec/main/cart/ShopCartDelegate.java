package com.boombone7.orange.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boombone7.core.I;
import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/7
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRvShopCart = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconShopCartSelectAll = null;

    private ShopCartAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconShopCartSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url(I.URL.SHOP_CART)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvShopCart.setLayoutManager(manager);
        mRvShopCart.setAdapter(mAdapter);
    }

    @OnClick(R2.id.icon_shop_cart_select_all)
    public void onShopSelectedAllClicked() {
        final int tag = (int) mIconShopCartSelectAll.getTag();
        if (tag == 0) {
            mIconShopCartSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconShopCartSelectAll.setTag(1);
            mAdapter.setIsSelectAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconShopCartSelectAll.setTextColor(Color.GRAY);
            mIconShopCartSelectAll.setTag(0);
            mAdapter.setIsSelectAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    public void onMTvTopShopCartClearClicked() {
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    public void onMTvTopShopCartRemoveSelectedClicked() {
    }
}
