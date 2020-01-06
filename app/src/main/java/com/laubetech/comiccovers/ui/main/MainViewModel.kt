package com.laubetech.comiccovers.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laubetech.comiccovers.BuildConfig
import com.laubetech.comiccovers.ComicApp
import com.laubetech.comiccovers.models.ComicData
import com.laubetech.comiccovers.models.MarvelComicsAPI
import com.laubetech.comiccovers.models.data.Comic
import com.laubetech.comiccovers.models.data.ComicDatabase
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MainViewModel() : ViewModel() {
    var reloadImage = MutableLiveData<Boolean>()

    var currentImageName = MutableLiveData<String>()

    var currentComicData = MutableLiveData<ComicData>()

    var targetComic = MutableLiveData<Comic>()

    private var lastComicId : Long = 0L

    //fun targetComic(comicId: String) =  getValue( ComicApp.appDatabase.comicDao().findComic(comicId) )

    init{
        reloadImage.value = false
        currentImageName.value = "image.jpg"
    }

    fun goButton( selectedComic:Long){
        lastComicId = selectedComic
        targetComic.value = this.getValue( ComicApp.appDatabase.comicDao().findComic(findIssueId(selectedComic)) )

       // val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
       // apiRequest.getSingleComic(findIssueId(selectedComic), this)
    }

    fun downloadIssueInfo(){
        val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        apiRequest.getSingleComic(findIssueId(lastComicId), this)
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

    /**
     * Helper method for testing LiveData objects, from
     * https://github.com/googlesamples/android-architecture-components.
     *
     * Get the value from a LiveData object. We're waiting for LiveData to emit, for 2 seconds.
     * Once we got a notification via onChanged, we stop observing.
     */
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            data[0] = o
            latch.countDown()
        }
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}
