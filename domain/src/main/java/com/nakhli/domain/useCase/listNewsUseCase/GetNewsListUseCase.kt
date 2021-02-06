package com.nakhli.domain.useCase.listNewsUseCase

import com.nakhli.domain.model.News
import io.reactivex.Observable

/**
 * Class to handle News list use case by sort items by date
 *
 * @property getNewsListRepository
 */
class GetNewsListUseCase(val getNewsListRepository: GetNewsListRepository) {
    fun getNewsList(): Observable<List<News>> {
        return getNewsListRepository.getNewsList().map {
            with(
                listOf(
                    // Sort list stories by descending by date
                    it.stories.sortedByDescending { story -> story.date },
                    // Sort list videos by descending by date
                    it.videos.sortedByDescending { video -> video.date })
                    //Sort List News by Descending by merging two list
                    .sortedByDescending { news -> news.count() }
            ) {
                // list news after sort by date
                first().mapIndexed { index, news -> listOfNotNull(news, last().getOrNull(index)) }
            }.flatten()

        }
    }
}