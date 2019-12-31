package com.laubetech.comiccovers.models.data

class ComicRepository(private val comicDao: ComicDao){

    suspend fun insert(comic: Comic){
        comicDao.insertComic(comic)
    }


}