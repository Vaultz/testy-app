package com.example.vault.testyapp.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vault on 16/03/2017.
 */

public interface API {
    @GET("?json=get_recent_posts")
    Call<PostResponse> getRecentPosts();

    @GET("?json=get_category_posts")
    Call<PostResponse> getCategoryPosts(@Query("slug") String category);

}
