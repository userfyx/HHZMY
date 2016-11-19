package com.hhzmy.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hhzmy.fragment.ClassFragment;
import com.hhzmy.fragment.HomeFragment;
import com.hhzmy.fragment.MineFragment;
import com.hhzmy.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private LinearLayout ll= (LinearLayout) findViewById(R.id.ll);
    //private List<ImageView> list=new ArrayList<>();
    private ImageView home;
    private ImageView type;
    private ImageView shop;
    private ImageView mine;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private MineFragment mineFragment;
    private ShopFragment shopFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        initiew();
        flush();
        home.setImageResource(R.mipmap.tab_home_pressed);
        FrameLayout(0);


    }



    private void FrameLayout(int index){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        HideFragment(fragmentTransaction);
        switch (index) {
            case 0:

                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.framelayout, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (classFragment == null) {
                    classFragment = new ClassFragment();
                    fragmentTransaction.add(R.id.framelayout, classFragment);
                } else {
                    fragmentTransaction.show(classFragment);
                }
                break;
            case 2:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    fragmentTransaction.add(R.id.framelayout, shopFragment);
                } else {
                    fragmentTransaction.show(shopFragment);
                }
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.framelayout, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void HideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (classFragment != null) {
            fragmentTransaction.hide(classFragment);
        }
        if (shopFragment != null) {
            fragmentTransaction.hide(shopFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }


    private void initiew() {
        home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);
        type = (ImageView) findViewById(R.id.type);
        type.setOnClickListener(this);
        shop = (ImageView) findViewById(R.id.shop);
        shop.setOnClickListener(this);
        mine = (ImageView) findViewById(R.id.mine);
        mine.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home:
                flush();
                FrameLayout(0);
                home.setImageResource(R.mipmap.tab_home_pressed);
                break;
            case R.id.type:
                flush();
                FrameLayout(1);
                type.setImageResource(R.mipmap.tab_class_pressed);
                break;
            case R.id.shop:
                flush();
                FrameLayout(2);
                shop.setImageResource(R.mipmap.tab_shopping_pressed);
                break;
            case R.id.mine:
                flush();
                FrameLayout(3);
                mine.setImageResource(R.mipmap.tab_myebuy_pressed);
                break;
        }
    }

    private void flush() {
        home.setImageResource(R.mipmap.tab_home_normal);
        type.setImageResource(R.mipmap.tab_class_normal);
        shop.setImageResource(R.mipmap.tab_shopping_normal);
        mine.setImageResource(R.mipmap.tab_myebuy_normal);
    }
}
