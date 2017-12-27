package com.boombone7.orange.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/27
 */

public class AboutDelegate extends OrangeDelegate {
    @BindView(R2.id.tv_info)
    AppCompatTextView mTvInfo;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTvInfo.setText(info);
                    }
                })
                .build()
                .get();
    }
}
