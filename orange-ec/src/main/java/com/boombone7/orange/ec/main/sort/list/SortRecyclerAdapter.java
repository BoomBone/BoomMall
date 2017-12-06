package com.boombone7.orange.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.recycler.MultipleRecyclerAdapter;
import com.boombone7.core.ui.recycler.MultipleViewHolder;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.main.sort.SortDelegate;

import java.util.List;

/**
 * @author Ting
 * @date 2017/12/6
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    public SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(I.ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()) {
            case I.ItemType.VERTICAL_MENU_LIST:
                final String text = item.getField(I.MultipleFields.TEXT);
                final boolean isClicked = item.getField(I.MultipleFields.TAG);
                final AppCompatTextView name = helper.getView(R.id.tv_vertical_item_name);
                final View line = helper.getView(R.id.view_line);
                final View itemView = helper.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentPosition = helper.getAdapterPosition();
                        if (mPrePosition !=currentPosition){
                            //还原上一个
                            getData().get(mPrePosition).setField(I.MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            item.setField(I.MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition)
                                    .getField(I.MultipleFields.ID);
                        }

                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                helper.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }
}
