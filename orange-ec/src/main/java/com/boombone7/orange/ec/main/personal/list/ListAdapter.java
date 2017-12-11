package com.boombone7.orange.ec.main.personal.list;

import android.widget.ImageView;

import com.boombone7.core.I;
import com.boombone7.orange.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Ting
 * @date 2017/12/11
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(I.ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(I.ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);

    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case I.ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case I.ListItemType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
