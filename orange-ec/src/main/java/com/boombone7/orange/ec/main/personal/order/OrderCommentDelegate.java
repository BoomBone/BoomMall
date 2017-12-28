package com.boombone7.orange.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.ui.widget.StarLayout;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/28
 */

public class OrderCommentDelegate extends OrangeDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mCustomStarLayout;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.top_tv_comment_commit)
    public void onCommitClicked() {
        Toast.makeText(getContext(), "评分：" + mCustomStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }
}
