package com.boombone7.core.util.callback;

import android.support.annotation.Nullable;

/**
 * @author Ting
 * @date 2017/12/22
 */

public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
