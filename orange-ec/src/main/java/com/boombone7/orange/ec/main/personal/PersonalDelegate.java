package com.boombone7.orange.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boombone7.core.I;
import com.boombone7.core.delegates.bottom.BottomItemDelegate;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;
import com.boombone7.orange.ec.main.personal.list.ListAdapter;
import com.boombone7.orange.ec.main.personal.list.ListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/11
 */

public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvPersonalSetting;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(I.ListItemType.ITEM_NORMAL)
                .setId(1)
//                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(I.ListItemType.ITEM_NORMAL)
                .setId(2)
//                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvPersonalSetting.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvPersonalSetting.setAdapter(adapter);
    }
}
