package com.laubetech.comiccovers.models

import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.laubetech.comiccovers.ui.main.MainViewModel
import com.laubetech.comiccovers.util.HashUtils
import okhttp3.*
import java.io.*


class MarvelComicsAPI constructor(publicKey:String, privateKey:String){
    var characterId = 1009351
    var comicId = 73822
    var seriesId = 24278
    private var publicKey:String = ""
    private var privateKey:String = ""
    val currentTimestamp = System.currentTimeMillis()


    init  {
        this.publicKey = publicKey
        this.privateKey = privateKey
    }

    fun fetchImage(url:String, size:String, extension:String, fileLocation:File, fileName:String,viewModel: MainViewModel ){
        var updatedUrl:String
        if(url.startsWith("http:",true)){
            updatedUrl = url.replace("http:","https:")
        }else{
            updatedUrl = url
        }

        val finalURL = "$updatedUrl/$size.$extension"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(finalURL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.code.toString() + ":" + response.message

                Log.e("ServerResponse", responseData)
                if (response.code == 200) {
                   // save file to storage
                    val downloadedFile = File(fileLocation, fileName)

                    val input = BufferedInputStream(response.body!!.byteStream())
                    val output: OutputStream = FileOutputStream(downloadedFile, false)

                    val data = ByteArray(1024)
                    var count:Int
                    while (input.read(data).also { count = it } != -1) {
                        output.write(data, 0, count)
                    }
                    viewModel.updateImage(fileName)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerResponse", "Request Failure." + e.localizedMessage)
            }
        })
    }

    fun getSingleComic(comicId:String, viewModel: MainViewModel){
        val buildUrl: HttpUrl= HttpUrl.Builder()
            .host("gateway.marvel.com")
            .scheme("https")
            .addEncodedPathSegments("/v1/public")
            .addPathSegment("comics")
            .addPathSegment(comicId)
            .addEncodedQueryParameter("apikey", publicKey)
            .addEncodedQueryParameter("ts", currentTimestamp.toString())
            .addEncodedQueryParameter("hash", generateHash(currentTimestamp))
            .build()

        Log.e("ServerResponse",buildUrl.toString())
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(buildUrl)
            .addHeader("Accept-Encoding", "identity")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.code.toString() + ":" + response.message

                // there is a trick here, use .body.string() not body.toString()
                val data = response.body!!.string()
                val gson = Gson()

                val marvelData:MarvelResponse = gson.fromJson(data)


                Log.e("ServerResponse", responseData)
                if (response.code == 200) {
                    val comicData = ComicData(marvelData)
                    Log.e("ServerResponse",comicData.toString() )
                    viewModel.currentComicData.postValue(comicData)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerResponse","Request Failure." + e.localizedMessage)
            }
        })
    }

    fun getHulkComics(){
        val buildUrl: HttpUrl= HttpUrl.Builder()
            .host("gateway.marvel.com")
            .scheme("https")
            .addEncodedPathSegments("/v1/public")
            .addPathSegment("characters")
            .addPathSegment(characterId.toString())
            .addPathSegment("series")
            .addEncodedQueryParameter("apikey", publicKey)
            .addEncodedQueryParameter("ts", currentTimestamp.toString())
            .addEncodedQueryParameter("hash", generateHash(currentTimestamp))
            .build()

        Log.e("ServerResponse",buildUrl.toString())
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(buildUrl)
            .addHeader("Accept-Encoding", "identity")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.code.toString() + ":" + response.message

                // there is a trick here, use .body.string() not body.toString()
                val data = response.body!!.string()
                val gson = Gson()

                val marvelData:MarvelResponse = gson.fromJson(data)


                Log.e("ServerResponse", responseData)
                if (response.code == 200) {
                    Log.e("ServerResponse", marvelData.toString())
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerResponse","Request Failure." + e.localizedMessage)
            }
        })
    }

    fun findSeries(filter:String){

        val buildUrl: HttpUrl= HttpUrl.Builder()
            .host("gateway.marvel.com")
            .scheme("https")
            .addEncodedPathSegments("/v1/public")
            .addPathSegment("series")
            .addEncodedQueryParameter("titleStartsWith", filter)
            .addEncodedQueryParameter("apikey", publicKey)
            .addEncodedQueryParameter("ts", currentTimestamp.toString())
            .addEncodedQueryParameter("hash", generateHash(currentTimestamp))
            .build()

        Log.e("ServerResponse",buildUrl.toString())
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(buildUrl)
            .addHeader("Accept-Encoding", "identity")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.code.toString() + ":" + response.message
                val data = response.body!!.string()
                val gson = Gson()

                val marvelData:MarvelResponse = gson.fromJson(data)


                Log.e("ServerResponse", responseData)
                if (response.code == 200) {
                    Log.e("ServerResponse", marvelData.data.results.toString())
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerResponse","Request Failure." + e.localizedMessage)
            }
        })
    }



    fun listComics(){

        val buildUrl: HttpUrl= HttpUrl.Builder()
            .host("gateway.marvel.com")
            .scheme("https")
            .addEncodedPathSegments("/v1/public")
            .addPathSegment("characters")
            .addEncodedQueryParameter("nameStartsWith", "hulk")
            .addEncodedQueryParameter("apikey", publicKey)
            .addEncodedQueryParameter("ts", currentTimestamp.toString())
            .addEncodedQueryParameter("hash", generateHash(currentTimestamp))
            .build()

        Log.e("ServerResponse",buildUrl.toString())
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(buildUrl)
            .addHeader("Accept-Encoding", "identity")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.code.toString() + ":" + response.message
                val data = response.body!!.string()
                val gson = Gson()

                val marvelData:MarvelResponse = gson.fromJson(data)


                Log.e("ServerResponse", responseData)
                if (response.code == 200) {
                    Log.e("ServerResponse", marvelData.data.results.toString())
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerResponse","Request Failure." + e.localizedMessage)
            }
        })
    }

    fun generateHash(timeStamp : Long): String?{
        val hashBase = timeStamp.toString()+privateKey+publicKey
        return HashUtils.MD5(hashBase)

    }

}
