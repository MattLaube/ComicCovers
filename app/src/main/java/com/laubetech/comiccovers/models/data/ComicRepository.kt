package com.laubetech.comiccovers.models.data

import androidx.lifecycle.LiveData

class ComicRepository(private val comicDao: ComicDao){
    fun insert(comic: Comic){
        comicDao.insertComic(comic)
    }

    fun find(comicId: String): LiveData<List<Comic>>{
        return comicDao.findComic(comicId)
    }
}