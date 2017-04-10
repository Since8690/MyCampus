package com.example.since.mycampus.guide;

/**
 * Created by Since on 2017/4/10.
 */

public class GuideShijiItem
{
    private String title;
    private String href;

    public GuideShijiItem(String title, String href)
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
}
