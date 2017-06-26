package com.concreteexemplo.rest;

import com.concreteexemplo.model.Pulls;
import com.concreteexemplo.model.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{

    @GET("search/repositories?")
    Call<RepoResponse> getTopRepos(@Query("q") String q, @Query("sort") String sorting, @Query("page") int pageNum);

    @GET("repos/{owner}/{name}/pulls")
    Call<List<Pulls>> getAllPulls(@Path("owner") String owner, @Path("name") String name);
}
