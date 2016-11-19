package com.hhzmy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hhzmy.main.R;


public class BaoZ_xqFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bao_z_xq, container, false);
        WebView webView= (WebView) inflate.findViewById(R.id.webView_bz);

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl("http://product.suning.com/pds-web/product/graphicSaleApp/0000000000/102295661.html");

        return inflate;
    }


}
