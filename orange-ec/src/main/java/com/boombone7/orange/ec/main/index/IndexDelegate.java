package com.boombone7.orange.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.refresh.RefreshHandler;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/4
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSrlIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mEtSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar mTbIndex;

    RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mSrlIndex);
        RestClient.builder()
                .url(I.URL.FIRST_PAGE_URL)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        ArrayList<MultipleItemEntity> list = converter.convert();
                        final String image = list.get(1).getField(I.MultipleFields.IMAGE_URL);
                        Toast.makeText(Orange.getApplicationContext(), image, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefresh();
//        mRefreshHandler.firstPage(I.URL.FIRST_PAGE_URL);
    }

    private void initRefresh() {
        mSrlIndex.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        );
        mSrlIndex.setProgressViewOffset(true, 120, 300);
    }
}
