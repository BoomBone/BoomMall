package com.boombone7.core.delegates;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.boombone7.core.ui.camera.OrangeCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author Ting
 * @date 2017/12/1
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera(){
        OrangeCamera.start(this);
    }

    //真正的调用方法
    public void startCameraWithCheck(){
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
