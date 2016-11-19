package com.hhzmy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_welcome);
        //去掉标题栏
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        jump();
    }
    public void jump() {
        timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //跳转
                        startActivity(new Intent(WelcomeActivity.this,ThreeFragmentsActivity.class));
                        //销毁
                        finish();
                        timer.cancel();
                    }
                });
            }
        };
        timer.schedule(task,3000);
    }

}
