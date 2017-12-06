package com.boombone7.orange.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boombone7.core.I;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.boombone7.orange.ec.main.sort.SortDelegate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/6
 */

public class VerticalListDelegate extends OrangeDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRvVerticalMenuList = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvVerticalMenuList.setLayoutManager(manager);
        //屏蔽动画效果
        mRvVerticalMenuList.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url(I.URL.SORT_LIST)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        ArrayList<MultipleItemEntity> data =
                                new VerticalListDataConverter()
                                        .setJsonData(response)
                                        .convert();
                        SortDelegate delegate = getParentDelegate();
                        SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRvVerticalMenuList.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
