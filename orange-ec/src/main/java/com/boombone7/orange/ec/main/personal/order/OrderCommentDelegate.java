package com.boombone7.orange.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boombone7.core.I;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.ui.widget.AutoPhotoLayout;
import com.boombone7.core.ui.widget.StarLayout;
import com.boombone7.core.util.callback.CallbackManager;
import com.boombone7.core.util.callback.IGlobalCallback;
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
    StarLayout mCustomStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(I.CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }

    @OnClick(R2.id.top_tv_comment_commit)
    public void onCommitClicked() {
        Toast.makeText(getContext(), "评分：" + mCustomStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }
}
