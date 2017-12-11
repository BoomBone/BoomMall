package com.boombone7.orange.ec.main.personal.order;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.recycler.MultipleRecyclerAdapter;
import com.boombone7.core.ui.recycler.MultipleViewHolder;
import com.boombone7.orange.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * @author Ting
 * @date 2017/12/11
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(I.OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()) {
            case I.OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = helper.getView(R.id.image_order_list);
                final AppCompatTextView title = helper.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = helper.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = helper.getView(R.id.tv_order_list_time);

                final String titleVal = item.getField(I.MultipleFields.TITLE);
                final String timeVal = item.getField(I.OrderItemFields.TIME);
                final double priceVal = item.getField(I.OrderItemFields.PRICE);
                final String imageUrl = item.getField(I.MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);

                title.setText(titleVal);
                price.setText("价格：" + String.valueOf(priceVal));
                time.setText("时间：" + timeVal);
                break;
            default:
                break;
        }
    }
}
