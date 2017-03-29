package com.example.since.mycampus.offer;

/**
 * Created by Since on 2017/3/27.
 */

public class OfferItem
{
    private String company;
    private String address;
    private String time;
    private String html;

    public OfferItem(String company, String address, String time, String html)
    {
        this.company = company;
        this.address = address;
        this.time = time;
        this.html = html;
    }

    public String getCompany()
    {
        return company;
    }

    public String getAddress()
    {
        return address;
    }

    public String getTime()
    {
        return time;
    }

    public String getHtml()
    {
        return html;
    }
}
