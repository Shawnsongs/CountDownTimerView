package com.jmgo.countdowntimerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.jmgo.countdowntimerview.R;


/**
 * Created by liangjianpeng on 2018/7/4.
 */

public class SelectStateLayout extends RelativeLayout {
    private boolean select;
    private static final int[] MY_STATE = {R.attr.select_state};
    private boolean shakeEnable = false;

    public SelectStateLayout(Context context) {
        super(context);
    }

    public SelectStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelect(boolean select) {
        if (this.select != select) {
            this.select = select;
            refreshDrawableState();//刷新状态
        }
    }


    public void setShakeEnable(boolean shakeEnable) {
        this.shakeEnable = shakeEnable;
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (select) {
            mergeDrawableStates(drawableState, MY_STATE);
        }
        return drawableState;
    }

    public boolean isSelect() {
        return select;
    }


}
