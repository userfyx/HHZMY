package com.hhzmy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hhzmy.main.R;


public class GuiGe_xqFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_gui_ge_xq, container, false);
        WebView webView= (WebView) inflate.findViewById(R.id.webView_gg);

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl("http://product.suning.com/pds-web/product/graphicDetailsApp/0000000000/102295661/10051/R9000413/1.html");
        return inflate;


    }


}
