package com.tanim.androidbasetemplate.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    var userId:String,
    var name:String
)
