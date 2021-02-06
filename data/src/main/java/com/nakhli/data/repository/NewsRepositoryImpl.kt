package com.nakhli.data.repository

import android.util.Log
import com.nakhli.data.api.ApiService
import com.nakhli.data.db.*
import com.nakhli.data.db.dao.NewsDao
import com.nakhli.data.db.entity.NewsWrapperEntity
import com.nakhli.domain.model.NewsWrapper
import com.nakhli.domain.model.Story
import com.nakhli.domain.model.Video
import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryRepository
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListRepository
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsRepositoryImpl(private val apiService: ApiService, private val storiesDao: NewsDao) :
    GetNewsListRepository, GetDetailsStoryRepository, PlayVideoRepository {

    // Method to fetch remote data from api and store them in local database
    override fun getNewsList(): Observable<NewsWrapper> {
        return apiService.getNewsListFomWs()
            // In case of server error : loadStoriesFromDB
            .onErrorResumeNext(loadStoriesFromDB())
            .doOnNext {
                // Store news in local database
                insertNewsIntoDB(
                    NewsWrapperEntity(
                        mapStoriesDataToEntities(it.stories),
                        mapVideosDataToEntities(it.videos)
                    )
                )
            }
            .doOnComplete {
                loadStoriesFromDB()
            }
    }

    //Load only stories from db
    private fun loadStoriesFromDB(): Observable<NewsWrapper> =
        storiesDao.getStoriesListFromDb().map {
            NewsWrapper(
                mapStoriesEntitiesToData(it), emptyList()
            )
        }


    private fun insertNewsIntoDB(newsWrapperEntity: NewsWrapperEntity) =
        Observable.fromCallable {
            storiesDao.insertNews(
                newsWrapperEntity.storiesEntity,
                newsWrapperEntity.videosEntity
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d(
                    "NewsRepository",
                    "Inserted Story ${newsWrapperEntity.storiesEntity.size} Videos" +
                            " ${newsWrapperEntity.videosEntity.size} from API in DB.."
                )
            }

    override fun getDetailsStory(idStory: Int): Single<Story> {
        return storiesDao.getStoryById(idStory).map {
            mapStoryEntityToData(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    override fun getVideo(idVideo: Int): Single<Video> {
        return storiesDao.getVideoById(idVideo).map {
            mapVideoEntityToData(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
}