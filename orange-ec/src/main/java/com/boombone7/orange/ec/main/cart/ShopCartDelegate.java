package com.boombone7.orange.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.util.log.OLog;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/7
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener {
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRvShopCart = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconShopCartSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    private ShopCartAdapter mAdapter = null;
    private View stubView = null;
    //购物车数量标记
    private double mTotalPrice = 0.00;

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
        mAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvShopCart.setLayoutManager(manager);
        mRvShopCart.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
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
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    public void onMTvTopShopCartRemoveSelectedClicked() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteData = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(I.ShopCart.IS_SELECTED);
            if (isSelected) {
                deleteData.add(entity);
            }
        }
        for (int i = 0; i < deleteData.size(); i++) {
            int dataCount = data.size();
            int currentPosition = deleteData.get(i).getField(I.ShopCart.POSITION);
            if (currentPosition < data.size()) {
                mAdapter.remove(currentPosition);
                for (; currentPosition < dataCount - 1; currentPosition++) {
                    int reDataPosition = data.get(currentPosition).getField(I.ShopCart.POSITION);
                    data.get(currentPosition).setField(I.ShopCart.POSITION, reDataPosition - 1);
                }
            }
        }
        checkItemCount();
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (stubView == null) {
                stubView = mStubNoItem.inflate();
            }
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRvShopCart.setVisibility(View.GONE);
        } else {
            mRvShopCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    //结算
    @OnClick(R2.id.tv_shop_cart_pay)
    public void onPayClicked() {
        //TODO 编写自己的支付api
        Toast.makeText(getContext(), "支付功能暂未开放", Toast.LENGTH_SHORT).show();
        createOrder();

    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        final String orderUrl = "你的生成订单的API";
        /**
         * 上传到自己服务器参数
         * userid amount comment type ordertype isanonymous followeduser
         * 1，服务器请求支付宝服务器
         */
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        OLog.d("ORDER", response);
                        final int orderId = JSON.parseObject(response).getInteger("result");
//                        FastPay.create(ShopCartDelegate.this)
//                                .setPayResultListener(ShopCartDelegate.this)
//                                .setOrderId(orderId)
//                                .beginPayDialog();
                    }
                })
                .build()
                .post();
    }
}
