package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhzmy.adapter.MyBobyAdapter;
import com.hhzmy.adapter.MyFiveAdapter;
import com.hhzmy.adapter.MyHomeAdapter;
import com.hhzmy.adapter.MySameAdapter;
import com.hhzmy.adapter.MyThreeAdapter;
import com.hhzmy.adapter.MyTopAdapter;
import com.hhzmy.app.ImageLoaderUtils;
import com.hhzmy.bean.Data;
import com.hhzmy.httputil.OkHttp;
import com.hhzmy.main.ErweiMaActivity;
import com.hhzmy.main.LookMoreActivity;
import com.hhzmy.main.Main2Activity;
import com.hhzmy.main.R;
import com.hhzmy.main.WebViewActivity;
import com.hhzmy.tools.RecyclerViewClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class HomeFragment extends Fragment {

    private String url = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";
    private ViewPager viewpager;
    private Data data;
    private List<Data.DataBean.TagBean> tagBeanList;
    private LinearLayout ll_tag;
    private RecyclerView recyclerView;
    private RecyclerView recyclerview_image;
    private DisplayImageOptions options;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        options = ImageLoaderUtils.initOptions();


        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));

        recyclerview_image = (RecyclerView) view.findViewById(R.id.recyclerview_image);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview_image.setLayoutManager(linearLayoutManager);

        viewpager = (ViewPager) view.findViewById(R.id.vp);
        //viewpager的监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {

                ll_tag = (LinearLayout) getView().findViewById(R.id.ll_tag);
                //将position换算到tagBeanlist的索引范围之内
                int count=position%data.getData().get(0).getTag().size();
                //遍历所有的点对应的ImageView ，判断点的索引是否跟ViewPager当前的索引一致
                for(int i=0;i<ll_tag.getChildCount();i++){
                    ImageView imageView=(ImageView) ll_tag.getChildAt(i);
                    if(i==count){
                        imageView.setSelected(true);
                    }else{
                        imageView.setSelected(false);
                    }
                }
                //添加下面的圆点
                for(int i=0;i<data.getData().get(0).getTag().size();i++){
                    //动态实例化ImageView对象，添加到LinearLayout里面
                    ImageView imageView=new ImageView(getActivity());
                    //手动代码设置间距
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(5, 0, 5, 0);
                    //将当前属性设置给ImageView
                    imageView.setLayoutParams(params);
                    //给ImageView设置显示资源
                    imageView.setBackgroundResource(R.drawable.item_selector);
                    //将ImageView添加到LinearLayout里面
                    ll_tag.addView(imageView);
                    //设置默认选中第一个
                    if(i==0){
                        imageView.setSelected(true);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                data = gson.fromJson(result, Data.class);
                //0--34
              for (int num=0;num<=data.getData().size();num++){
                  //无限轮播
                  if(num==0){
                      tagBeanList = data.getData().get(num).getTag();
                      viewpager.setAdapter(new MyTopAdapter(getContext(), tagBeanList));
                  }//8个主题
                  else if(num==1){
                      tagBeanList = data.getData().get(num).getTag();
                      recyclerView.setAdapter(new MyHomeAdapter(getContext(),tagBeanList));
                     intentJump(recyclerView,num);
                  }//秒杀专题
                  else if(num==2){
                      tagBeanList= data.getData().get(num).getTag();
                      //10点秒杀
                      ImageView image= (ImageView) getView().findViewById(R.id.image);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image,options);
                      recyclerview_image.setAdapter(new MyThreeAdapter(getContext(),tagBeanList));
                    //跳转详情页面
                    intentJump1(recyclerview_image,num);
                  }//傲娇品牌
                  else if(num==4){
                      tagBeanList= data.getData().get(num).getTag();
//                      Log.d("ssssssssssssssss",tagBeanList.toString());
                      ImageView image_aojiao= (ImageView) getView().findViewById(R.id.image_aojiao);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_aojiao,options);
                  }//“衣”，“食”
                  else if(num==5){
                      tagBeanList= data.getData().get(num).getTag();
                     RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.five_recycler);
                      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                      recyclerView.setAdapter(new MyFiveAdapter(getContext(),tagBeanList));
                      //跳转页面
                      intentJump(recyclerView,num);
                  }//“住&行”，“用”
                  else if(num==6){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.six_recycler);
                      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                      recyclerView.setAdapter(new MyFiveAdapter(getContext(),tagBeanList));
                      //跳转页面
                     intentJump(recyclerView,num);
                  }//“玩”，“育”
                  else if(num==7){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.seven_recycler);
                      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                      recyclerView.setAdapter(new MyFiveAdapter(getContext(),tagBeanList));
                      //跳转页面
                      intentJump(recyclerView,num);
                  }//母婴专区
                  else if(num==9){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_nine= (ImageView) getView().findViewById(R.id.image_nine);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_nine,options);
                  }//“准妈妈备孕&孕期及产后”
                  else if(num==10){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.ten);
                      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                      recyclerView.setAdapter(new MyBobyAdapter(getContext(),tagBeanList));
                      //跳转页面
                     intentJump(recyclerView,num);
                  }//“新生儿专区&婴儿专区&幼儿专区&学前专区”
                  else if(num==11){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.oneone);
                      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
                      recyclerView.setAdapter(new MyBobyAdapter(getContext(),tagBeanList));
                      //跳转页面
                      intentJump(recyclerView,num);
                  }//主题专卖
                  else if(num==13){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_project= (ImageView) getView().findViewById(R.id.image_project);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_project,options);
                  }//洋货——超值购
                  else if (num==14){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_project= (ImageView) getView().findViewById(R.id.image_first);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_project,options);
                  }//first
                  else if (num==15){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.first);
                      LinearLayoutManager linearLayoutManager_first = new LinearLayoutManager(getActivity());
                      linearLayoutManager_first.setOrientation(LinearLayoutManager.HORIZONTAL);
                      recyclerView.setLayoutManager(linearLayoutManager_first);
                      recyclerView.setAdapter(new MySameAdapter(getContext(),tagBeanList));
                      //跳转详情页面
                      intentJump1(recyclerView,num);
                  }//聚优聚省聚惠
                  else if (num==16){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_project= (ImageView) getView().findViewById(R.id.image_second);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_project,options);
                  }//second
                  else if (num==17){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.second);
                      LinearLayoutManager linearLayoutManager_second = new LinearLayoutManager(getActivity());
                      linearLayoutManager_second.setOrientation(LinearLayoutManager.HORIZONTAL);
                      recyclerView.setLayoutManager(linearLayoutManager_second);
                      recyclerView.setAdapter(new MySameAdapter(getContext(),tagBeanList));
                      //跳转详情页面
                      intentJump1(recyclerView,num);
                  }//加量——不加价
                  else if (num==18){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_project= (ImageView) getView().findViewById(R.id.image_third);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_project,options);
                  }//third
                  else if (num==19){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.third);
                      LinearLayoutManager linearLayoutManager_third = new LinearLayoutManager(getActivity());
                      linearLayoutManager_third.setOrientation(LinearLayoutManager.HORIZONTAL);
                      recyclerView.setLayoutManager(linearLayoutManager_third);
                      recyclerView.setAdapter(new MySameAdapter(getContext(),tagBeanList));
                      //跳转详情页面
                     intentJump1(recyclerView,num);
                  }//省钱——败好货
                  else if (num==20){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_project= (ImageView) getView().findViewById(R.id.image_fourth);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_project,options);
                  }//fourth
                  else if (num==21){
                      tagBeanList= data.getData().get(num).getTag();
                      RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.fourth);
                      LinearLayoutManager linearLayoutManager_fourth = new LinearLayoutManager(getActivity());
                      linearLayoutManager_fourth.setOrientation(LinearLayoutManager.HORIZONTAL);
                      recyclerView.setLayoutManager(linearLayoutManager_fourth);
                      recyclerView.setAdapter(new MySameAdapter(getContext(),tagBeanList));
                      //跳转详情页面
                     intentJump1(recyclerView,num);
                  }//辣妈拼团
                  else if (num==23){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_pingtuan);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//运智贝
                  else if (num==24){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_24);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//康贝
                  else if (num==26){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_26);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//喜朗
                  else if (num==28){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_28);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//强生
                  else if (num==30){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_30);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//雀氏
                  else if (num==32){
                      tagBeanList= data.getData().get(num).getTag();
                      ImageView image_pt= (ImageView) getView().findViewById(R.id.image_32);
                      ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+tagBeanList.get(0).getPicUrl(),image_pt,options);
                  }//查看更多
                  else if (num==33){
                      tagBeanList= data.getData().get(num).getTag();
                      TextView tv= (TextView) getView().findViewById(R.id.more_search);
                      tv.setText(tagBeanList.get(0).getElementName());
                      tv.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent intent=new Intent(getContext(), LookMoreActivity.class);
                             intent.putExtra("url",tagBeanList.get(0).getLinkUrl());
                              getContext().startActivity(intent);
                          }
                      });
                  }
              }
            }
        });
        ImageView ewm= (ImageView) view.findViewById(R.id.erweima);

        ewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //二维码
                startActivity(new Intent(getActivity(), ErweiMaActivity.class));
            }
        });

        return view;
    }


    //跳转webview页面
        private void intentJump( RecyclerView recyclerView,int a){
            final int finalNum = a;
            recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("one",data.getData().get(finalNum).getTag().get(position).getLinkUrl());
                    getContext().startActivity(intent);
                }
                @Override
                public void onItemLongClick(View view, int position) {

                }
            }));
    }

    //跳转详情页面
    private void intentJump1( RecyclerView recyclerView,int a){
        final int finalNum = a;
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), Main2Activity.class);
                getContext().startActivity(intent);
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

}