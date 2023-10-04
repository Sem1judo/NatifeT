package com.ua.natifet.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.ua.natifet.service.GiphyApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

data class GiphyResponse(
    val data: List<GifItem>
)

data class GifItem(
    val images: Images // Assuming 'images' contains the URL of the original GIF
)

data class Images(
    val original: Original // Assuming 'original' contains the URL of the original GIF
)

data class Original(
    val url: String // The URL of the original GIF
)


class GifViewModel() : ViewModel() {

    private val BASE_URL = "https://api.giphy.com/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val giphyApiService: GiphyApiService = retrofit.create(GiphyApiService::class.java)


    suspend fun fetchGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ): List<GifItem>? {
        val response: Response<GiphyResponse> =
            giphyApiService.searchGifs(apiKey, query, limit, offset, rating, lang)
        return if (response.isSuccessful) {
            val giphyResponse = response.body()
            giphyResponse?.data
        } else {
            null
        }
    }
}
