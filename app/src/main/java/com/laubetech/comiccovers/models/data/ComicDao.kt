package com.laubetech.comiccovers.models.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicDao{
    @Query("Select * from comic where issueId = :comicId")
    fun findComic(comicId: String) : LiveData<List<Comic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComic(newComic: Comic)
}