package com.boombone7.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;

/**
 *
 * @author Ting
 * @date 2017/12/5
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Orange.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Orange.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
