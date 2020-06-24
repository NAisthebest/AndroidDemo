package com.winshare.fragmentdemo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winshare.fragmentdemo.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.news_content_frag,container, false);
        return view;
    }

    public void refresh(String title, String content){
        //将隐藏布局设置为显示
        View visibilityLayout = view.findViewById(R.id.contentLayout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById (R.id.title);
        TextView newsContentText = (TextView) view.findViewById (R.id.content);
        newsTitleText.setText(title);
        newsContentText.setText(content);
    }
}
