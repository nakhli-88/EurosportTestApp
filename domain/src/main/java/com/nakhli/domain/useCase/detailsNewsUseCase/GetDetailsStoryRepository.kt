package com.nakhli.domain.useCase.detailsNewsUseCase

import com.nakhli.domain.model.Story
import io.reactivex.Single

/**
 * Get details story repository
 *
 */
interface GetDetailsStoryRepository {

    fun getDetailsStory(idStory:Int): Single<Story>
}