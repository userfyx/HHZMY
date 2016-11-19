package com.hhzmy.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hhzmy.main.R;


public class XiangQingFragment extends Fragment {
    public static Fragment[] fragments;
    public static TextView[] textViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_xq, container, false);
        getFragment();
        textViews=new TextView[3];
        textViews[0] = (TextView)inflate.findViewById(R.id.tuwen);
        textViews[1] = (TextView) inflate.findViewById(R.id.guige);
        textViews[2] = (TextView) inflate.findViewById(R.id.baozhuang);
        //foot点击事件
        textViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.YELLOW);
                textViews[1].setTextColor(Color.BLACK);
                textViews[2].setTextColor(Color.BLACK);
                getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[2])
                        .show(fragments[0]).commit();
            }
        });
        textViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.BLACK);
                textViews[1].setTextColor(Color.YELLOW);
                textViews[2].setTextColor(Color.BLACK);
                getChildFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[2])
                        .show(fragments[1]).commit();
            }
        });
        textViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setTextColor(Color.BLACK);
                textViews[1].setTextColor(Color.BLACK);
                textViews[2].setTextColor(Color.YELLOW);
                getChildFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1])
                        .show(fragments[2]).commit();
            }
        });
        return inflate;
    }
    public void getFragment(){
        //得到Fragment
        fragments = new Fragment[3];
        fragments[0] = getChildFragmentManager().findFragmentById(R.id.one_xq);
        fragments[1] = getChildFragmentManager().findFragmentById(R.id.two_xq);
        fragments[2] = getChildFragmentManager().findFragmentById(R.id.three_xq);
        //显示第一个Fragment
        getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[2])
                .show(fragments[0]).commit();


    }

}
