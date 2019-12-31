package com.laubetech.comiccovers.models.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Comic::class), version = 1)
abstract class ComicDatabase : RoomDatabase(){
    abstract fun comicDao(): ComicDao
}