package com.qqq.thehomeofthecar.userdefined;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by 秦谦谦 on 16/5/19 20:20.
 */
public class FindCarBrandDetailListView extends ListView {
    public FindCarBrandDetailListView(Context context) {
        super(context);
    }

    public FindCarBrandDetailListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec= View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
