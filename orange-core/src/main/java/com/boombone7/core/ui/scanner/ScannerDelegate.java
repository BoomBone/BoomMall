package com.boombone7.core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.I;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.util.callback.CallbackManager;
import com.boombone7.core.util.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author Ting
 * @date 2018/1/10
 */

public class ScannerDelegate extends OrangeDelegate implements ZBarScannerView.ResultHandler {
    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView!=null){
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result result) {
        final IGlobalCallback<String> callback = CallbackManager
                .getInstance()
                .getCallback(I.CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }
        getSupportDelegate().pop();
    }
}
