package com.laubetech.comiccovers.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laubetech.comiccovers.BuildConfig
import com.laubetech.comiccovers.ComicApp
import com.laubetech.comiccovers.models.ComicData
import com.laubetech.comiccovers.models.MarvelComicsAPI
import com.laubetech.comiccovers.models.data.Comic
import com.laubetech.comiccovers.models.data.ComicRepository


class MainViewModel: ViewModel() {
    var reloadImage = MutableLiveData<Boolean>()

    var currentImageName = MutableLiveData<String>()

    var currentComicData = MutableLiveData<ComicData>()

    var targetComic  = MutableLiveData<Comic>()

    private var lastComicId : String

    private val repository : ComicRepository

    init {
        reloadImage.value = false
        currentImageName.value = "image.jpg"
        lastComicId = ""
        repository = ComicRepository(ComicApp.appDatabase.comicDao())
    }

    fun goButton( selectedComic:Long){
        lastComicId = findIssueId(selectedComic)
        Log.d("MainViewModel","trying to load record id $lastComicId")

        val returnedList = repository.find(lastComicId)
       // if (returnedList.value != null ){
            targetComic.value = returnedList.value?.get(0)
        //}
    }

    fun downloadIssueInfo(){
        val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        apiRequest.getSingleComic(lastComicId, this)
    }

    fun startImageDownload(context:Context?, url:String, fileName:String){
        val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        apiRequest.fetchImage(url,"portrait_uncanny", "jpg", context!!.filesDir, fileName, this  )
    }

    fun updateImage(newImageName:String){
        currentImageName.postValue(newImageName)
        // this may happen on background thread, so do a postValue here
        reloadImage.postValue(true)
    }

    fun checkResults(): String{
        val comicDetails = targetComic.value
        if (comicDetails == null){
            Log.d("MainViewModel","DB load failed, trying to load from server")
            downloadIssueInfo()
        }else {
            updateImage(comicDetails!!.issueId)
            return comicDetails.toString()
        }
        return ""
    }

    fun storeComic(comic: Comic){
        repository.insert(comic)
    }

    private fun findIssueId(index:Long):String{
        return when(index){
            0L -> "83288"
            1L -> "77342"
            2L -> "82476"
            3L -> "77341"
            4L -> "77340"
            5L -> "78799"
            6L -> "81311"
            7L -> "83517"
            8L -> "77339"
            9L -> "78318"
            10L -> "78795"
            11L -> "78796"
            12L -> "78797"
            13L -> "78798"
            14L -> "73828"
            15L -> "78317"
            16L -> "73827"
            17L -> "78245"
            18L -> "73826"
            19L -> "77663"
            else -> ""
        }
    }
}
