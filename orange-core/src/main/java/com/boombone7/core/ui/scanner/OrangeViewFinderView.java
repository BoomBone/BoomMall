package com.boombone7.core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by Ting on 2018/1/10.
 */

public class OrangeViewFinderView extends ViewFinderView {
    public OrangeViewFinderView(Context context) {
        this(context,null);
    }

    public OrangeViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
