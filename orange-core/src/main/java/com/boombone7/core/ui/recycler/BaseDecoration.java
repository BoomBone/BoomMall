package com.boombone7.core.ui.recycler;

import android.content.Context;
import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 *
 * @author Ting
 * @date 2017/12/5
 */

public class BaseDecoration extends DividerItemDecoration {
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLoopupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color, size);
    }
}
