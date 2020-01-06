package com.laubetech.comiccovers.models.data

import androidx.lifecycle.LiveData

class ComicRepository(private val comicDao: ComicDao){

    suspend fun insert(comic: Comic){
        comicDao.insertComic(comic)
    }

    suspend fun find(comicId: String): LiveData<Comic>{
        return comicDao.findComic(comicId)
    }

}