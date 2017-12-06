package com.boombone7.core;

/**
 * @author Ting
 * @date 2017/11/10
 */

public interface I {
    interface Configkey {
        String CONFIG_READY = "CONFIG_READY";
        String APPLICATION_CONTEXT = "APPLICATION_CONTEXT";
        String API_HOST = "API_HOST";
        String LOADER_DELAYED = "LOADER_DELAYED";
        String HANDLER = "HANDLER";
        String INTERCEPTOR = "INTERCEPTOR";
        String WE_CHAT_APP_ID = "WE_CHAT_APP_ID";
        String WE_CHAT_APP_SECRET = "WE_CHAT_APP_SECRET";
        String ACTIVITY = "ACTIVITY";
    }

    interface HttpMethod {
        String GET = "GET";
        String POST = "POST";
        String POST_RAW = "POST_RAW";
        String PUT = "PUT";
        String PUT_RAW = "PUT_RAW";
        String DELETE = "DELETE";
        String UPLOAD = "UPLOAD";
    }

    interface ScrollLauncherTag {
        String HAS_FIRST_LAUNCHER_APP = "HAS_FIRST_LAUNCHER_APP";
    }

    interface OnLauncherFinishTag {
        String SIGNED = "SIGNED";
        String NOT_SIGNED = "NOT_SIGNED";
    }

    interface LoaderStyle {
        String BallPulseIndicator = "BallPulseIndicator";
        String BallGridPulseIndicator = "BallGridPulseIndicator";
        String BallClipRotateIndicator = "BallClipRotateIndicator";
        String BallClipRotatePulseIndicator = "BallClipRotatePulseIndicator";
        String SquareSpinIndicator = "SquareSpinIndicator";
        String BallClipRotateMultipleIndicator = "BallClipRotateMultipleIndicator";
        String BallPulseRiseIndicator = "BallPulseRiseIndicator";
        String BallRotateIndicator = "BallRotateIndicator";
        String CubeTransitionIndicator = "CubeTransitionIndicator";
        String BallZigZagIndicator = "BallZigZagIndicator";
        String BallZigZagDeflectIndicator = "BallZigZagDeflectIndicator";
        String BallTrianglePathIndicator = "BallTrianglePathIndicator";
        String BallScaleIndicator = "BallScaleIndicator";
        String LineScaleIndicator = "LineScaleIndicator";
        String LineScalePartyIndicator = "LineScalePartyIndicator";
        String BallScaleMultipleIndicator = "BallScaleMultipleIndicator";
        String BallPulseSyncIndicator = "BallPulseSyncIndicator";
        String BallBeatIndicator = "BallBeatIndicator";
        String LineScalePulseOutIndicator = "LineScalePulseOutIndicator";
        String LineScalePulseOutRapidIndicator = "LineScalePulseOutRapidIndicator";
        String BallScaleRippleIndicator = "BallScaleRippleIndicator";
        String BallScaleRippleMultipleIndicator = "BallScaleRippleMultipleIndicator";
        String BallSpinFadeLoaderIndicator = "BallSpinFadeLoaderIndicator";
        String LineSpinFadeLoaderIndicator = "LineSpinFadeLoaderIndicator";
        String TriangleSkewSpinIndicator = "TriangleSkewSpinIndicator";
        String PacmanIndicator = "PacmanIndicator";
        String BallGridBeatIndicator = "BallGridBeatIndicator";
        String SemiCircleSpinIndicator = "SemiCircleSpinIndicator";
        String CustomIndicator = "CustomIndicator";
    }

    interface MultipleFields {
        String ITEM_TYPE = "ITEM_TYPE";
        String TITLE = "TITLE";
        String TEXT = "TEXT";
        String IMAGE_URL = "IMAGE_URL";
        String BANNERS = "BANNERS";
        String SPAN_SIZE = "SPAN_SIZE";
        String ID = "ID";
        String NAME = "NAME";
        String TAG = "TAG";
    }

    interface ItemType {
        int TEXT = 1;
        int IMAGE = 2;
        int TEXT_IMAGE = 3;
        int BANNER = 4;
        int VERTICAL_MENU_LIST = 5;
        int SINGLE_BIG_IMAGE = 6;
    }

    interface URL {
        String HOST_API = "http://114.67.235.114/RestServer/api/";
        String INDEX = "index.php";
        String REFRESH_INDEX = "refresh.php?index=";
        String SORT_LIST = "sort_list.php";
    }


}
