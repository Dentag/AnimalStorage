package com.dentag.animalstorage.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Animal(
    @PrimaryKey
    val name: String,
    val age: Int,
    val breed: String
) : Parcelable
