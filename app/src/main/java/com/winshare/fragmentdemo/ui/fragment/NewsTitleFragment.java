package com.winshare.fragmentdemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.winshare.fragmentdemo.ui.News;
import com.winshare.fragmentdemo.R;
import com.winshare.fragmentdemo.ui.NewsContentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private Boolean isTwoPane;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);

        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.newsTitleFrag);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //通过判断是否存在newsContentLayout的view判断单页双页
        if(getActivity().findViewById(R.id.newsContentLayout) != null){
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        }else {
            isTwoPane = false; // 可以找到news_content_layout布局时，为双页模式
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }


    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.title);
            }
        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //实例化
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        //双页模式，设置contentFragment显示 并更新内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.newsContentFrag);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }


}
