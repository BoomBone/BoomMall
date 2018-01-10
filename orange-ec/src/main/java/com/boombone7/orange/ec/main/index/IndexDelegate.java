package com.boombone7.orange.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
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
import com.boombone7.core.ui.recycler.BaseDecoration;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.refresh.RefreshHandler;
import com.boombone7.core.util.callback.CallbackManager;
import com.boombone7.core.util.callback.IGlobalCallback;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.boombone7.orange.ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    private RefreshHandler mRefreshHandler = null;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mSrlIndex, mRvIndex, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(I.CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefresh();
        initRecyclerView();
        mRefreshHandler.firstPage(I.URL.INDEX);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRvIndex.setLayoutManager(manager);
        mRvIndex.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext()
                        , R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRvIndex.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

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
