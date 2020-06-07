package com.laubetech.comiccovers.models
import com.laubetech.comiccovers.models.response.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelAPI {
    @GET("/v1/public/comics/{comicId}")
    fun getComic(@Path("comicId")comicId: String,
                 @Query("apikey" )key:String,
                 @Query("ts")time:String,
                 @Query("hash")hash:String?
    ): Single<MarvelResponse>
}