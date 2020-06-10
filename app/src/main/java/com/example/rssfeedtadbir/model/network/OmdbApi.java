package com.example.rssfeedtadbir.model.network;

import com.example.rssfeedtadbir.model.data.JNewsDetail;
import com.example.rssfeedtadbir.model.data.JSearchResult;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {

   // String BASE_URL = "http://www.omdbapi.com/";
    String BASE_URL = "https://github.com";
    String API_KEY = "3e974fca";

    @GET("./")
    Call<JSearchResult> searchNews(@Query("apikey") String apiKey,
                                   @Query("s") String keyword);

    @GET("./")
    Call<JNewsDetail> getNewsDetail(@Query("apikey") String apiKey,
                                     @Query("i") String imdbId);

}
