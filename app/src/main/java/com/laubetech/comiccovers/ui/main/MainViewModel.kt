package com.laubetech.comiccovers.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laubetech.comiccovers.BuildConfig
import com.laubetech.comiccovers.models.ComicData
import com.laubetech.comiccovers.models.MarvelComicsAPI

class MainViewModel : ViewModel() {
    private var _reloadImage = MutableLiveData<Boolean>()
    val reloadImage :LiveData<Boolean>
        get() = _reloadImage

    private var _currentImageName = MutableLiveData<String>()
    val currentImageName : LiveData<String>
        get() = _currentImageName

    var currentComicData = MutableLiveData<ComicData>()

    init {
        _reloadImage.value = false
        _currentImageName.value = "image.jpg"
    }

    fun goButton( selectedComic:Long){
        val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        apiRequest.getSingleComic(findIssueId(selectedComic), this)
    }

    fun startImageDownload(context:Context?, url:String, fileName:String){
        val apiRequest = MarvelComicsAPI(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        apiRequest.fetchImage(url,"portrait_uncanny", "jpg", context!!.filesDir, fileName, this  )
    }

    fun updateImage(newImageName:String){
        _currentImageName.postValue(newImageName)
        // this may happen on background thread, so do a postValue here
        _reloadImage.postValue(true)
    }

    private fun findIssueId(index:Long):String{
        return when(index){
            0L -> "73821"
            1L -> "73822"
            2L -> "73823"
            else -> ""

        }
    }

}
