package com.example.since.mycampus.guide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.since.mycampus.R;
import com.example.since.mycampus.offer.OfferAdapter;
import com.example.since.mycampus.offer.OfferItem;
import com.example.since.mycampus.offer.OfferWebViewActivity;

import java.util.List;

/**
 * Created by Since on 2017/4/10.
 */

public class GuideShijiAdapter extends RecyclerView.Adapter<GuideShijiAdapter.ViewHolder>
{
    private List<GuideShijiItem> mGuideShijiList;
    private Context context;    //持有OfferActivity的引用，实现WebViewActivity的跳转

    public GuideShijiAdapter(Context context, List<GuideShijiItem> mGuideShijiList)
    {
        this.context = context;
        this.mGuideShijiList = mGuideShijiList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewTitle;
        View guideShijiView; //用于实现RecyclerView的点击事件

        public ViewHolder(View itemView)    //itemView为RecyclerView的最外层布局
        {
            super(itemView);
            guideShijiView = itemView;   //用于实现RecyclerView的点击事件
            textViewTitle = (TextView)itemView.findViewById(R.id.guideshiji_tv_item);
        }
    }

    @Override
    public GuideShijiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_shiji_item, parent, false);
        final GuideShijiAdapter.ViewHolder holder = new GuideShijiAdapter.ViewHolder(view);

        holder.guideShijiView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                GuideShijiItem guideShijiItem = mGuideShijiList.get(position);

                String url = guideShijiItem.getHref();
                Intent intent = new Intent(context,GuideShijiWebView.class);
                intent.putExtra("html", url);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GuideShijiAdapter.ViewHolder holder, int position)
    {
        GuideShijiItem guideShijiItem = mGuideShijiList.get(position);
        holder.textViewTitle.setText(guideShijiItem.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return mGuideShijiList.size();
    }

}
