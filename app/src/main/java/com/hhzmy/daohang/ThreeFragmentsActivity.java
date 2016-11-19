package com.hhzmy.daohang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.hhzmy.adapter.ThreeFragmentAdapter;
import com.hhzmy.main.MainActivity;
import com.hhzmy.main.R;

import java.util.ArrayList;
import java.util.List;

public class ThreeFragmentsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewpager;
    private List<View> viewlist;
    private View view3;
    private View view1;
    private View view2;
    private ImageView jump_one;
    private ImageView jump_two;
    private ImageView jump_three;
    private List<View> dotlist;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_fragments);
        //保存狀態
        sp=getPreferences(MODE_PRIVATE);
        Boolean isfrist=sp.getBoolean("isfrist", false);
        if (isfrist) {
            Intent intent=new Intent(ThreeFragmentsActivity.this,MainActivity.class);
            startActivity(intent);
            //销毁
            finish();
            return ;
        }
        initData();
        //viewpager
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        //适配器
      ThreeFragmentAdapter tfAdapter=new ThreeFragmentAdapter(viewlist);
        viewpager.setAdapter(tfAdapter);
        //
        initImage();
        //
        initPoints();
    }
    //点
    private void initPoints() {
        dotlist=new ArrayList<>();

    }
    //三个view布局中的imageview及监听
    private void initImage() {
        jump_one = (ImageView)view1.findViewById(R.id.jump_one);
        //jump_one.setImageResource(R.mipmap.guide_pass);
        jump_one.setOnClickListener(this);
        jump_two = (ImageView)view2.findViewById(R.id.jump_two);
        //jump_two.setImageResource(R.mipmap.guide_pass);
        jump_two.setOnClickListener(this);
        jump_three = (ImageView)view3.findViewById(R.id.jump_three);
        //jump_three.setImageResource(R.mipmap.guide_start);
        jump_three.setOnClickListener(this);
    }
    //三个view集合
    private void initData() {
        viewlist =new ArrayList<View>();
        view1 = View.inflate(this, R.layout.view_picture_one,null);
        //view1.setBackgroundResource(R.mipmap.guide_page1);
        view2 = View.inflate(this, R.layout.view_picture_two,null);
        //view2.setBackgroundResource(R.mipmap.guide_page2);
        view3 = View.inflate(this, R.layout.view_picture_three,null);
        //view3.setBackgroundResource(R.mipmap.guide_page3);

        viewlist.add(view1);
        viewlist.add(view2);
        viewlist.add(view3);

    }

    //监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jump_one:
                startActivity(new Intent(ThreeFragmentsActivity.this,MainActivity.class));
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putBoolean("isfrist", true);
                edit1.commit();

                break;
            case R.id.jump_two:
                startActivity(new Intent(ThreeFragmentsActivity.this,MainActivity.class));
                SharedPreferences.Editor edit2 = sp.edit();
                edit2.putBoolean("isfrist", true);
                edit2.commit();

                break;
            case R.id.jump_three:
                startActivity(new Intent(ThreeFragmentsActivity.this,MainActivity.class));
                SharedPreferences.Editor edit3 = sp.edit();
                edit3.putBoolean("isfrist", true);
                edit3.commit();

                break;

        }
    }
}
