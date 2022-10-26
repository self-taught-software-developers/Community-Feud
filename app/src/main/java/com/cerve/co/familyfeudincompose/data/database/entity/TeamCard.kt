package com.cerve.co.familyfeudincompose.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamCard(
    @PrimaryKey val name: String,
    val points: Int,
    val turns: Int
)