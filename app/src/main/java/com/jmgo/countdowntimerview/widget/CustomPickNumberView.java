package com.jmgo.countdowntimerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jmgo.countdowntimerview.R;

/**
 * Created by songzhihao on 2018/7/26.
 */


public class CustomPickNumberView extends LinearLayout implements View.OnKeyListener, View.OnFocusChangeListener {
    private static String TAG = "TAG_CountDownView";
    private Button mHour, mMinute;
    private View customCountHour, customCountMinute;
    private ImageView prv1, prv2, prv3, prv4;
    private OnCustomPickTime pickTime;

    public CustomPickNumberView(Context context) {
        super(context);
        Log.d(TAG, "--CustomPickNumberView--");
    }

    public CustomPickNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPickNumberView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void getPickTime(OnCustomPickTime onCustomPickTime) {
        pickTime = onCustomPickTime;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "--onFinishInflate---");
        LayoutInflater.from(getContext()).inflate(R.layout.custome_coundown_timer, this, true);
        customCountHour = findViewById(R.id.hour);
        customCountHour.setOnKeyListener(this);
        customCountHour.setOnFocusChangeListener(this);
        mHour = (Button) findViewById(R.id.bt_hour);
        mHour.setText(String.valueOf(0));
        prv1 = (ImageView) findViewById(R.id.prev1);
        prv2 = (ImageView) findViewById(R.id.prev2);
        customCountMinute = findViewById(R.id.minute);
        customCountMinute.setOnKeyListener(this);
        customCountMinute.setOnFocusChangeListener(this);
        mMinute = (Button) findViewById(R.id.bt_minute);
        mMinute.setText(String.valueOf(0));
        prv3 = (ImageView) findViewById(R.id.prev3);
        prv4 = (ImageView) findViewById(R.id.prev4);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //Log.d(TAG,"v="+v+",keyCode="+keyCode+",event="+event);
        if (v == customCountHour) {
            int i = Integer.valueOf((String) mHour.getText());
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                //Log.d(TAG, "左键被按下了"+",i="+i);
                if (i == 0) {
                    mHour.setText(String.valueOf(24));
                } else {
                    mHour.setText(String.valueOf(i - 1));
                }
                Log.d(TAG, "mHour.getText()=" + mHour.getText() + ",mMinute.getText()=" + mMinute.getText());
                pickTime.getPickTime(Integer.valueOf((String) mHour.getText()), Integer.valueOf((String) mMinute.getText()));
                return true;
            } else if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                //Log.d(TAG, "右键被按下了");
                if (i == 24) {
                    mHour.setText(String.valueOf(0));
                } else {
                    mHour.setText(String.valueOf(i + 1));
                }
                Log.d(TAG,"onCustomPickTime=="+pickTime);
                Log.d(TAG, "mHour.getText()=" + mHour.getText() + ",mMinute.getText()=" + mMinute.getText());
                Log.d(TAG,"--"+ Integer.valueOf((String) mHour.getText())+",.... "+ Integer.valueOf((String) mMinute.getText()));
                pickTime.getPickTime(Integer.valueOf((String) mHour.getText()), Integer.valueOf((String) mMinute.getText()));
                return true;
            }

        } else if (v == customCountMinute) {
            //
            int i = Integer.valueOf((String) mMinute.getText());
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (i == 0) {
                    mMinute.setText(String.valueOf(60));
                } else {
                    mMinute.setText(String.valueOf(i - 1));
                }
                pickTime.getPickTime(Integer.valueOf((String) mHour.getText()), Integer.valueOf((String) mMinute.getText()));
                return true;
            } else if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (i == 60) {
                    mMinute.setText(String.valueOf(0));
                } else {
                    mMinute.setText(String.valueOf(i + 1));
                }
                pickTime.getPickTime(Integer.valueOf((String) mHour.getText()), Integer.valueOf((String) mMinute.getText()));
                return true;
            }

        }
        return false;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view instanceof SelectStateLayout) {
            Log.d(TAG, "view=" + view + ",hasFocus=" + hasFocus);
            setItemStatus((SelectStateLayout) view, hasFocus);
        }
        switch (view.getId()) {
            case R.id.hour:
                if (hasFocus) {
                    prv1.setVisibility(VISIBLE);
                    prv2.setVisibility(VISIBLE);
                } else {
                    prv1.setVisibility(INVISIBLE);
                    prv2.setVisibility(INVISIBLE);
                }
                break;
            case R.id.minute:
                if (hasFocus) {
                    prv3.setVisibility(VISIBLE);
                    prv4.setVisibility(VISIBLE);
                } else {
                    prv3.setVisibility(INVISIBLE);
                    prv4.setVisibility(INVISIBLE);
                }
                break;
            default:
                break;
        }

    }

    private void setItemStatus(final SelectStateLayout view, final boolean focus) {
        view.setSelect(focus);
    }


    public interface OnCustomPickTime {
        void getPickTime(int hour, int minute);
    }

}
