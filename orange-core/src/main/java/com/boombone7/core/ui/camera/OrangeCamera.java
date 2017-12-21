package com.boombone7.core.ui.camera;

import android.net.Uri;

import com.boombone7.core.delegates.PermissionCheckerDelegate;
import com.boombone7.core.util.file.FileUtil;

/**
 * @author Ting
 * @date 2017/12/21
 * 照相机调用类
 */

public class OrangeCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
