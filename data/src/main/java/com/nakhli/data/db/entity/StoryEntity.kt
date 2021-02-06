package com.nakhli.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nakhli.domain.model.Sport

const val STORY_TABLE = "story"
const val STORY_PREFIX = "story_sport"
const val STORY_ID = "id"
const val STORY_TITLE = "title"
const val STORY_TEASER = "teaser"
const val STORY_IMAGE = "image"
const val STORY_DATE = "date"
const val STORY_AUTHOR = "author"

/**
 * Story Entity
 *
 * @property storyId
 * @property storyTitle
 * @property storyTeaser
 * @property storyImage
 * @property storyDate
 * @property storyAuthor
 * @property sport
 */
@Entity(tableName = STORY_TABLE)
data class StoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = STORY_ID)
    val storyId: Int,
    @ColumnInfo(name = STORY_TITLE)
    val storyTitle: String,
    @ColumnInfo(name = STORY_TEASER)
    val storyTeaser: String,
    @ColumnInfo(name = STORY_IMAGE)
    val storyImage: String,
    @ColumnInfo(name = STORY_DATE)
    val storyDate: Double,
    @ColumnInfo(name = STORY_AUTHOR)
    val storyAuthor: String,

    @Embedded(prefix = STORY_PREFIX)
    val sport: Sport
)