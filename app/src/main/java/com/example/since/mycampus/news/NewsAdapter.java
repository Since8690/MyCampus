package com.example.since.mycampus.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.since.mycampus.R;
import java.util.List;

/**
 * Created by Since on 2017/4/8.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{

    private List<NewsItem> mNewsList;
    private Context context;    //持有NewsActivity的引用，实现WebViewActivity的跳转

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewTitle;
        View newsView; //用于实现RecyclerView的点击事件

        public ViewHolder(View itemView)    //itemView为RecyclerView的最外层布局
        {
            super(itemView);
            newsView = itemView;   //用于实现RecyclerView的点击事件
            textViewTitle = (TextView)itemView.findViewById(R.id.news_tv_item);
            
        }
    }

    public NewsAdapter(Context context, List<NewsItem> mNewsList)
    {
        this.context = context;
        this.mNewsList = mNewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.newsView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                NewsItem newsItem = mNewsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("newsClass", newsItem);
                Intent intent = new Intent(context,NewsWebViewActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position)
    {
        NewsItem newsItem = mNewsList.get(position);
        holder.textViewTitle.setText(newsItem.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return mNewsList.size();
    }

}
