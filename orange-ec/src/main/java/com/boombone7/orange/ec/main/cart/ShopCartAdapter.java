package com.boombone7.orange.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.recycler.MultipleRecyclerAdapter;
import com.boombone7.core.ui.recycler.MultipleViewHolder;
import com.boombone7.core.util.log.OLog;
import com.boombone7.orange.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * @author Ting
 * @date 2017/12/8
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(I.ShopCart.PRICE);
            final int count = entity.getField(I.ShopCart.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        addItemType(I.ShopCart.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()){
            case I.ShopCart.SHOP_CART_ITEM:
                //先取出所有值
                final int id = item.getField(I.MultipleFields.ID);
                final String thumb = item.getField(I.MultipleFields.IMAGE_URL);
                final String title = item.getField(I.ShopCart.TITLE);
                final String desc = item.getField(I.ShopCart.DESC);
                final int count = item.getField(I.ShopCart.COUNT);
                final double price = item.getField(I.ShopCart.PRICE);
                //取出所以控件
                final AppCompatImageView imgThumb = helper.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = helper.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = helper.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = helper.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = helper.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = helper.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = helper.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = helper.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                //在左侧勾勾渲染之前改变全选与否状态
                item.setField(I.ShopCart.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = item.getField(I.ShopCart.IS_SELECTED);
                if (isSelected){
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(Orange.getApplicationContext(), R.color.app_main));
                }else{
                    iconIsSelected.setTextColor(Color.GRAY);
                }

                //添加左侧勾勾的点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final boolean currentSelected = item.getField(I.ShopCart.IS_SELECTED);
                        if (currentSelected){
                            iconIsSelected.setTextColor(Color.GRAY);
                            item.setField(I.ShopCart.IS_SELECTED, false);
                        }else{
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Orange.getApplicationContext(), R.color.app_main));
                            item.setField(I.ShopCart.IS_SELECTED, true);
                        }
                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = item.getField(I.ShopCart.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url(I.URL.SHOP_CART_COUNT)
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemTotal = countNum * price;
                                                mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = item.getField(I.ShopCart.COUNT);
                        RestClient.builder()
                                .url(I.URL.SHOP_CART_COUNT)
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mCartItemListener != null) {
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemTotal = countNum * price;
                                            mCartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });
                break;
            default:
                break;
        }
    }

    public void setIsSelectAll(boolean selectAll) {
        this.mIsSelectedAll = selectAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
