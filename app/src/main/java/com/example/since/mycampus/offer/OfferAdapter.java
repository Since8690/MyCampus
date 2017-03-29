package com.example.since.mycampus.offer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.since.mycampus.R;

import java.util.List;

/**
 * Created by Since on 2017/3/27.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder>
{

    private List<OfferItem> mOfferList;
    private Context context;    //持有OfferActivity的引用，实现WebViewActivity的跳转

    public OfferAdapter(Context context, List<OfferItem> mOfferList)
    {
        this.context = context;
        this.mOfferList = mOfferList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewCompany;
        TextView textViewTime;
        View offerView; //用于实现RecyclerView的点击事件

        public ViewHolder(View itemView)    //itemView为RecyclerView的最外层布局
        {
            super(itemView);
            offerView = itemView;   //用于实现RecyclerView的点击事件
            textViewCompany = (TextView)itemView.findViewById(R.id.offer_company);
            textViewTime = (TextView)itemView.findViewById(R.id.offer_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_offer_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.offerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                OfferItem offerItem = mOfferList.get(position);

                String url = offerItem.getHtml();
                Intent intent = new Intent(context,OfferWebViewActivity.class);
                intent.putExtra("html", url);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        OfferItem offerItem = mOfferList.get(position);
        holder.textViewCompany.setText(offerItem.getCompany());
        holder.textViewTime.setText(offerItem.getTime());
    }

    @Override
    public int getItemCount()
    {
        return mOfferList.size();
    }

}
