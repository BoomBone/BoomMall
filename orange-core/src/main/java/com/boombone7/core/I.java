package com.boombone7.core;

import com.yalantis.ucrop.UCrop;

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
        String JAVASCRIPT_INTERFACE = "JAVASCRIPT_INTERFACE";
        String WEB_HOST = "WEB_HOST";
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
        String HOST_API = "http://app.boombone7.com/RestServer/api/";
        String INDEX = "index.php";
        String REFRESH_INDEX = "refresh.php?index=";
        String SORT_LIST = "sort_list.php";
        String SORT_CONTENT_LIST = "sort_content_list.php?contentId=";
        String SHOP_CART = "shop_cart.php";
        String SHOP_CART_COUNT = "shop_cart_count.php";
        String ORDER_LIST = "order_list.php";
        String ADDRESS = "address.php";
        String ABOUT = "about.php";
    }

    interface RouteKeys {
        String URL = "URL";
    }

    interface ShopCart {
        int SHOP_CART_ITEM = 6;
        String TITLE = "TITLE";
        String DESC = "DESC";
        String COUNT = "COUNT";
        String PRICE = "PRICE";
        String IS_SELECTED = "IS_SELECTED";
        String POSITION = "POSITION";
    }

    interface ListItemType {
        int ITEM_NORMAL = 20;
        int ITEM_AVATAR = 21;
        int ITEM_SWITCH = 22;
    }

    interface OrderListItemType {
        int ITEM_ORDER_LIST = 30;
    }

    interface OrderItemFields {
        String PRICE = "PRICE";
        String TIME = "TIME";
    }

    interface RequestCodes {
        int TAKE_PHOTO = 4;
        int PICK_PHOTO = 5;
        int CROP_PHOTO = UCrop.REQUEST_CROP;
        int CROP_ERROR = UCrop.RESULT_ERROR;
        int SCAN = 7;
    }

    interface CallbackType {
        String ON_CROP = "ON_CROP";
        String TAG_OPEN_PUSH = "TAG_OPEN_PUSH";
        String TAG_STOP_PUSH = "TAG_STOP_PUSH";
        String ON_SCAN = "ON_SCAN";
    }

    interface UploadConfig {
        String API_HOST = "你的服务器域名";
        String UPLOAD_IMG = API_HOST + "你的上传地址";
    }

    interface AddressItemType {
        int ITEM_ADDRESS = 40;
    }

    interface AddressItemFields {
        String PHONE = "PHONE";
        String ADDRESS = "ADDRESS";
    }
}
