package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.main.MyMapActivity;
import com.hhzmy.main.R;

public class ShoppingFragment extends Fragment {

    private TextView map_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_shopping2, container, false);

        map_title = (TextView) inflate.findViewById(R.id.map_title);
        map_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyMapActivity.class));
                Toast.makeText(getActivity(),"百度地图",Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }

}
