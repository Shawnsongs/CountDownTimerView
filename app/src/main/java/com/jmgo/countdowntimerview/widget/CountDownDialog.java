package com.jmgo.countdowntimerview.widget;

import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jmgo.countdowntimerview.R;


/**
 * Created by songzhihao on 2018/7/17.
 */

public class CountDownDialog extends Dialog implements View.OnClickListener,CountDownView.OnTimeCompleteListener {
    private static String TAG = "TAG_CountDownDialog";
    private int time;
    private Context mContext;
    private Button bt_end;
    private Button bt_return;
    private CountDownView mCountDownView;
    //private HolatekOSManager holatekOSManager;
    private int hour;
    private int minute;
    private int second;

    public CountDownDialog(Context context, int time) {
        super(context, R.style.WifiDialogTheme);
        this.time = time;
        this.mContext = context;
        //Log.d(TAG, "构造函数---->");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.d(TAG, "onCreate---->");
        setContentView(R.layout.layout_countdown_time);
        getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.main_bg));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        holatekOSManager = (HolatekOSManager) mContext.getSystemService("holatekos_mananger");
        hour = time % 3600;
        minute = (time - hour * 3600) % 60;
        second = time - hour * 3600 - minute * 60;
        mCountDownView = (CountDownView) findViewById(R.id.countdown_timer_hour);
        mCountDownView.initTime(hour, minute, second);
        mCountDownView.start();
        bt_end = (Button) findViewById(R.id.btn_end);
        bt_return = (Button) findViewById(R.id.btn_return);
        bt_return.setOnClickListener(this);
        bt_end.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_end:
                mCountDownView.stop();
                //setCountdownTime(0);
                dismiss();
                break;
            case R.id.btn_return:
//                if (getCountdownTime() > 0) {
//                    if (timingShutdownDialog!=null){
//                        timingShutdownDialog.dismiss();
//                    }
//
//                }
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeComplete() {

    }
//    private void setCountdownTime(int time) {
//        holatekOSManager.set(HolatekOSConfig.Service.DLP, HolatekOSConfig.DLP_INDEX_TIMER_SHUTDOWN, time);
//    }
//
//    private int getCountdownTime() {
//        Log.d(TAG, "倒计时时间是:" + holatekOSManager.get(HolatekOSConfig.Service.DLP, HolatekOSConfig.DLP_INDEX_TIMER_SHUTDOWN));
//        return holatekOSManager.get(HolatekOSConfig.Service.DLP, HolatekOSConfig.DLP_INDEX_TIMER_SHUTDOWN);
//    }
//
//
//    @Override
//    public void onTimeComplete() {
//        holatekOSManager.set(HolatekOSConfig.Service.DLP, HolatekOSConfig.DLP_INDEX_POWER_OFF_SYSTEM, 0);
//    }
}
