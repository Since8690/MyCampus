package com.example.since.mycampus.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.since.mycampus.R;
import com.example.since.mycampus.offer.OfferActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private DrawerLayout myDrawerLayout;//滑动菜单
    private NavigationView navView;//华丽的滑动菜单

    //主页的五个模块
    private CardView cardViewNews;
    private CardView cardViewGuide;
    private CardView cardViewOffer;
    private CardView cardViewViliager;
    private CardView cardViewTribe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置ToolBar代替系统默认的ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置滑动菜单
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //处理NavigationView菜单项的单击事件
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_call);//设置默认选中的子菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                //在这里处理菜单项的选中
                return false;
            }
        });

        cardViewNews = (CardView)findViewById(R.id.card_news);
        cardViewGuide = (CardView)findViewById(R.id.card_guide);
        cardViewOffer = (CardView)findViewById(R.id.card_offer);
        cardViewViliager = (CardView)findViewById(R.id.card_viliager);
        cardViewTribe = (CardView)findViewById(R.id.card_tribe);
        cardViewNews.setOnClickListener(this);
        cardViewGuide.setOnClickListener(this);
        cardViewOffer.setOnClickListener(this);
        cardViewViliager.setOnClickListener(this);
        cardViewTribe.setOnClickListener(this);
    }

    //对HomeAsUp按钮的点击事件进行处理
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                myDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }


    //响应主页五个模块的点击
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.card_news:
                Toast.makeText(getApplicationContext(),"You clicked News",Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_offer:
                Intent intent = new Intent(MainActivity.this, OfferActivity.class);
                startActivity(intent);
        }

    }
}
