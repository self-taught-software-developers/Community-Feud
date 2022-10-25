package com.cerve.co.familyfeudincompose.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey val name: String,
    val points: Int,
    val teamName: String
)
