package com.nakhli.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nakhli.data.db.entity.StoryEntity
import com.nakhli.data.db.entity.VideoEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface NewsDao {

    // Method to get all stories from eurosport database
    @Query("SELECT * From story")
    fun getStoriesListFromDb(): Observable<List<StoryEntity>>

    // Method to insert all news from remote api to Eurosport database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(stories: List<StoryEntity>, videos: List<VideoEntity>)

    // Method to get story by id from Eurosport database
    @Query("SELECT * From story Where id = :idStory")
    fun getStoryById(idStory: Int): Single<StoryEntity>

    // Method to get video by id from Eurosport database
    @Query("SELECT * From video Where id = :idStory")
    fun getVideoById(idStory: Int): Single<VideoEntity>

}