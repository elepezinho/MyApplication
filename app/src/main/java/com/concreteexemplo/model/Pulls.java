package com.concreteexemplo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Pulls implements Serializable
{
    @SerializedName("html_url")
    private String pullUrl;

    @SerializedName("title")
    private String pullTitle;

    @SerializedName("user")
    private Users userInfo;

    @SerializedName("body")
    private String pullBody;

    @SerializedName("created_at")
    private String createdAt;

    public Pulls (String pullUrl, String pullTitle, Users userInfo, String pullBody, String createdAt)
    {
        this.pullUrl = pullUrl;
        this.pullTitle = pullTitle;
        this.userInfo = userInfo;
        this.pullBody = pullBody;
        this.createdAt = createdAt;
    }

    public String getPullUrl()
    {
        return pullUrl;
    }
    public void setPullUrl(String pullUrl)
    {
        this.pullUrl = pullUrl;
    }
    public String getPullTitle()
    {
        return pullTitle;
    }
    public void setPullTitle(String pullTitle)
    {
        this.pullTitle = pullTitle;
    }
    public Users getUserInfo()
{
    return userInfo;
}
    public void setUserInfo(Users userInfo)
    {
        this.userInfo = userInfo;
    }
    public String getPullBody()
    {
        return pullBody;
    }
    public void setPullBody(String pullBody)
    {
        this.pullBody = pullBody;
    }
    public String getCreatedAt()
    {
        return createdAt;
    }
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
}
