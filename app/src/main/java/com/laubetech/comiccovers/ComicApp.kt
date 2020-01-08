package com.laubetech.comiccovers

import android.app.Application
import com.laubetech.comiccovers.models.data.ComicDatabase

class ComicApp:Application() {


    companion object{
        lateinit var appDatabase : ComicDatabase
    }


    override fun onCreate() {
        super.onCreate()
        appDatabase = ComicDatabase.getDatabase(this)
    }
}