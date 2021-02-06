package com.nakhli.domain.useCase.listNewsUseCase

import com.nakhli.domain.model.NewsWrapper
import io.reactivex.Observable

/**
 * Get news list repository
 *
 */
interface GetNewsListRepository {

    fun getNewsList(): Observable<NewsWrapper>
}