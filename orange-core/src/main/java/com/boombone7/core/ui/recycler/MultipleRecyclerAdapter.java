package com.boombone7.core.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.boombone7.core.I;
import com.boombone7.core.R;
import com.boombone7.core.ui.banner.BannerCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ting
 * @date 2017/12/5
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements
        BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {

    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private void init() {
        //设置不同的item布局
        addItemType(I.ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(I.ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(I.ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(I.ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (helper.getItemViewType()){
            case I.ItemType.TEXT:
                text = item.getField(I.MultipleFields.TEXT);
                helper.setText(R.id.text_single,text);
                break;
            case I.ItemType.IMAGE:
                imageUrl = item.getField(I.MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_single));
                break;
            case I.ItemType.TEXT_IMAGE:
                text = item.getField(I.MultipleFields.TEXT);
                imageUrl = item.getField(I.MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_multiple));
                helper.setText(R.id.tv_multiple, text);
                break;
            case I.ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = item.getField(I.MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = helper.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(I.MultipleFields.SPAN_SIZE);
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }
}
