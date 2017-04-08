package com.example.since.mycampus.news;

/**
 * Created by Since on 2017/4/8.
 */

public class NewsItem
{
    private String title;
    private String href;

    public NewsItem(String title, String href)
    {
        this.title = title;
        this.href = href;
    }

    public String getTitle()
    {
        return title;
    }

    public String getHref()
    {
        return href;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setHref(String href)
    {
        this.href = href;
    }
}
