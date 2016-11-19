package com.hhzmy.xiangqing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.main.R;
import com.hhzmy.shop.ShopCarActivity;
import com.hhzmy.zhifubao.PayDemoActivity;

public class Main2Activity extends AppCompatActivity {
    public static Fragment[] fragments;
    public static TextView[] textViews;
    private TextView lijigoumai;
    private TextView goShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        getFragment();
        textViews=new TextView[3];
        textViews[0] = (TextView) findViewById(R.id.shop);
        textViews[1] = (TextView) findViewById(R.id.xiangqing);
        textViews[2] = (TextView) findViewById(R.id.pingjia);
        //foot点击事件
        textViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.YELLOW);
                textViews[1].setTextColor(Color.BLACK);
                getSupportFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[2])
                        .show(fragments[0]).commit();
            }
        });
        textViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.BLACK);
                textViews[1].setTextColor(Color.YELLOW);
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[2])
                        .show(fragments[1]).commit();
            }
        });
        textViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.BLACK);
                textViews[1].setTextColor(Color.YELLOW);
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1])
                        .show(fragments[2]).commit();
            }
        });
    }
    //立即购买
    private void initView() {
        lijigoumai = (TextView) findViewById(R.id.lijigoumai);
        goShop = (TextView) findViewById(R.id.Go_Shop);
        goShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,ShopCarActivity.class));
                Toast.makeText(getApplicationContext(),"加入购物车",Toast.LENGTH_SHORT).show();
            }
        });
        lijigoumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即购买
//               startActivity(new Intent(Main2Activity.this,ShopCarActivity.class));
//                startActivity(new Intent(Main2Activity.this, ShopFragment.class));
//                startActivityForResult(new Intent(Main2Activity.this, ShopFragment.class),1);
                Toast.makeText(getApplicationContext(),"立即购买",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main2Activity.this,PayDemoActivity.class));
            }
        });
    }

    public void getFragment(){
        //得到Fragment
        fragments = new Fragment[3];
        fragments[0] = getSupportFragmentManager().findFragmentById(R.id.one);
        fragments[1] = getSupportFragmentManager().findFragmentById(R.id.two);
        fragments[2] = getSupportFragmentManager().findFragmentById(R.id.three);
        //显示第一个Fragment
        getSupportFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[2])
                .show(fragments[0]).commit();


    }

}
