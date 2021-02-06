package com.nakhli.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nakhli.domain.model.Sport

const val VIDEO_TABLE = "video"
const val SPORT_PREFIX = "video_sport"

const val VIDEO_ID = "id"
const val VIDEO_TITLE = "title"
const val VIDEO_THUMB = "thumb"
const val VIDEO_URL = "url"
const val VIDEO_DATE = "date"
const val VIDEO_VIEWS = "views"
/**
 * Video Entity
 *
 * @property videoId
 * @property videoTitle
 * @property videoThumb
 * @property videoUrl
 * @property videoDate
 * @property sport
 * @property videoViews
 */
@Entity(tableName = VIDEO_TABLE)
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = VIDEO_ID)
    val videoId: Int,
    @ColumnInfo(name = VIDEO_TITLE)
    val videoTitle: String,
    @ColumnInfo(name = VIDEO_THUMB)
    val videoThumb: String,
    @ColumnInfo(name = VIDEO_URL)
    val videoUrl: String,
    @ColumnInfo(name = VIDEO_DATE)
    val videoDate: Double,
    @Embedded(prefix = SPORT_PREFIX)
    val sport: Sport,
    @ColumnInfo(name = VIDEO_VIEWS)
    val videoViews: Int
)