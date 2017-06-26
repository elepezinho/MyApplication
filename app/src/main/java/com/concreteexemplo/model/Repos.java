package com.concreteexemplo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repos implements Serializable
{
    //name, owner(login, avatar_url) , description,
    //pulls_url(html_url ,title,user(login, avatar_url), body, created_at), stargazers_count, forks
    @SerializedName("name")
    private String name;

    @SerializedName("owner")
    private Owner ownerInfo;

    @SerializedName("description")
    private String description;

    @SerializedName("pulls_url")
    private String pullsInfo;

    @SerializedName("stargazers_count")
    private String starsCount;

    @SerializedName("forks")
    private String forksCount;

    public Repos (String name, Owner ownerInfo, String description, String pullsInfo, String starsCount, String forksCount)
    {
        this.name = name;
        this.ownerInfo = ownerInfo;
        this.description = description;
        this.pullsInfo = pullsInfo;
        this.starsCount = starsCount;
        this.forksCount = forksCount;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Owner getOwnerInfo()
    {
        return ownerInfo;
    }
    public void setOwnerInfo(Owner ownerInfo)
    {
        this.ownerInfo = ownerInfo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getPullsInfo()
    {
        return pullsInfo;
    }
    public void setPullsInfo(String pullsInfo)
    {
        this.pullsInfo = pullsInfo;
    }
    public String getStarsCount()
    {
        return starsCount;
    }
    public void setStarsCount(String starsCount)
    {
        this.starsCount = starsCount;
    }
    public String getForksCount()
    {
        return forksCount;
    }
    public void setForksCount(String forksCount)
    {
        this.forksCount = forksCount;
    }

}
