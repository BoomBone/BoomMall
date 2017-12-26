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
import com.boombone7.orange.ec.main.personal.address.AddressDelegate;
import com.boombone7.orange.ec.main.personal.list.ListAdapter;
import com.boombone7.orange.ec.main.personal.list.ListBean;
import com.boombone7.orange.ec.main.personal.order.OrderListDelegate;
import com.boombone7.orange.ec.main.personal.profile.UserProfileDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/11
 */

public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvPersonalSetting;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    //点击全部订单
    @OnClick(R2.id.tv_all_order)
    void onAllOrderClick() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    //点击头像
    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(I.ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
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
        mRvPersonalSetting.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
