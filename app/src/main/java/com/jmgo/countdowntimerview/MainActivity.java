package com.jmgo.countdowntimerview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jmgo.countdowntimerview.widget.CountDownDialog;
import com.jmgo.countdowntimerview.widget.CustomPickNumberView;

public class MainActivity extends Activity {
private static final String TAG="TAG_MainActivity";
private Button mButton;
private CustomPickNumberView customPickNumberView;
private CountDownDialog countDownDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custome_countdown_timer);
        customPickNumberView=findViewById(R.id.customCountDownView);
        //customPickNumberView=new CustomPickNumberView(this);
        customPickNumberView.getPickTime(new CustomPickNumberView.OnCustomPickTime() {
            @Override
            public void getPickTime(int hour, int minute) {
                countDownDialog=new CountDownDialog(MainActivity.this,hour*3600+minute*60);
            }
        });
        mButton=findViewById(R.id.bt_begin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownDialog.show();
            }
        });
    }
}
