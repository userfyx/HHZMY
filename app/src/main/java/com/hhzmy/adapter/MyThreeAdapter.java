package com.hhzmy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.bean.Data;
import com.hhzmy.main.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by a on 2016/11/15.
 */
public class MyThreeAdapter extends RecyclerView.Adapter<MyThreeAdapter.MyViewHolder> {
    private Context context;
    private List<Data.DataBean.TagBean> list;
    private MyViewHolder holder;

    public MyThreeAdapter(Context context,List<Data.DataBean.TagBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        holder = new MyViewHolder(LayoutInflater.from(
                context ).inflate(R.layout.my_three_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DisplayImageOptions options=new DisplayImageOptions.Builder().build();
        ImageLoader instance = ImageLoader.getInstance();

        instance.displayImage("http://image1.suning.cn/"+list.get(position+1).getPicUrl(),holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size()-1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);

            iv=(ImageView) itemView.findViewById(R.id.iv);
        }
    }
}

