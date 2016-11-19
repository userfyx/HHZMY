package com.hhzmy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.bean.Data;
import com.hhzmy.webview.HomeTopActivity;
import com.hhzmy.main.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by a on 2016/11/14.
 */
public class MyTopAdapter extends PagerAdapter{
        private Context context;
    private List<Data.DataBean.TagBean> list;
    private ImageView home_image;

    public MyTopAdapter(Context context, List<Data.DataBean.TagBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=View.inflate(context, R.layout.top_image,null);

        home_image = (ImageView) view.findViewById(R.id.home_image);
        home_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HomeTopActivity.class);
                intent.putExtra("web",list.get(position).getLinkUrl());
                context.startActivity(intent);
            }
        });
        home_image.setScaleType(ImageView.ScaleType.FIT_XY);//铺满ViewPager
        ImageLoader.getInstance().displayImage("http://image1.suning.cn"+list.get(position%list.size()).getPicUrl(),home_image);
        container.addView(view);

        return view;
    }

}