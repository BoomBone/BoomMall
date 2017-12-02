package com.boombone7.orange.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.boombone7.core.I;
import com.boombone7.core.app.AccountManager;
import com.boombone7.core.app.IUserChecker;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.ui.launcher.ILauncherListener;
import com.boombone7.core.ui.launcher.LauncherHolderCreator;
import com.boombone7.core.util.storage.OrangePreference;
import com.boombone7.orange.ec.R;

import java.util.ArrayList;

import static com.boombone7.core.I.OnLauncherFinishTag.NOT_SIGNED;
import static com.boombone7.core.I.OnLauncherFinishTag.SIGNED;

/**
 * @author Ting
 * @date 2017/12/1.
 */

public class LauncherScrollDelegate extends OrangeDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {
            OrangePreference.setAppFlag(I.ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP, true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(NOT_SIGNED);
                    }
                }
            });

        }
    }
}
