package com.boombone7.orange.ec.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.boombone7.core.I;
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
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(I.ShopCart.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
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
                break;
            default:
                break;
        }
    }
}
