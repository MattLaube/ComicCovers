package com.laubetech.comiccovers.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(@PrimaryKey val issueId:String,val issueTitle:String,val issueLength:String  ){

    override fun toString(): String {
        return "$issueTitle $issueLength Pages"
    }
}