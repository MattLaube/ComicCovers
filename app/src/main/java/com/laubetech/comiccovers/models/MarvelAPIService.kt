package com.laubetech.comiccovers.models

import com.laubetech.comiccovers.models.response.MarvelResponse
import com.laubetech.comiccovers.util.HashUtils
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelAPIService constructor(publicKey:String, privateKey:String){
    private var publicKey:String = ""
    private var privateKey:String = ""
    private val logTag = "MarvelComicsAPI"

    init  {
        this.publicKey = publicKey
        this.privateKey = privateKey
    }

    private val BASEURL = "https://gateway.marvel.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create() )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create() )
        .build()
        .create(MarvelAPI::class.java)

    fun getComic(comicId: String): Single<MarvelResponse> {
        val currentTimestamp = System.currentTimeMillis()
        return api.getComic(comicId,publicKey, currentTimestamp.toString(), generateHash(currentTimestamp))
    }

    fun generateHash(timeStamp : Long): String?{
        val hashBase = timeStamp.toString()+privateKey+publicKey
        return HashUtils.md5(hashBase)
    }
}