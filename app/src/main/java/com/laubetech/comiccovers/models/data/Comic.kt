package com.laubetech.comiccovers.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(@PrimaryKey @ColumnInfo(name="issueId") val issueId:String,val issueTitle:String,val issueLength:String  )