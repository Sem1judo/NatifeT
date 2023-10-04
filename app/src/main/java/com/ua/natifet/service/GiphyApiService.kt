package com.ua.natifet.service

import com.ua.natifet.ui.GifItem
import com.ua.natifet.ui.GiphyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface GiphyApiService {

    @GET("gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): Response<GiphyResponse>

}
