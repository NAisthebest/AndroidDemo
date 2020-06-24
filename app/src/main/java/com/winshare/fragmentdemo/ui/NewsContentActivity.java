package com.winshare.fragmentdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.winshare.fragmentdemo.R;
import com.winshare.fragmentdemo.ui.fragment.NewsContentFragment;


public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        //获取title
        String title = getIntent().getStringExtra("news_title");
        //获取content
        String content = getIntent().getStringExtra("news_content");
        //刷新NewsContentFragment界面
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.newsContentFrag);
        newsContentFragment.refresh(title,content);
    }
}
