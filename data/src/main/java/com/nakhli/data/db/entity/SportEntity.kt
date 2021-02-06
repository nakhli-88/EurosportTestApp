package com.nakhli.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val SPORT_TABLE = "sport"


/**
 * Sport Entity
 *
 * @property id
 * @property name
 */
@Entity(tableName = SPORT_TABLE)
data class SportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)