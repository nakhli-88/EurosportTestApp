package com.nakhli.domain.useCase.detailsNewsUseCase

import com.nakhli.domain.model.Story
import io.reactivex.Single


/**
 * Class to handle details story use case
 *
 * @property getDetailsStoryRepository
 */
class GetDetailsStoryUseCase(private val getDetailsStoryRepository: GetDetailsStoryRepository) {
    fun getDetailsStory(idStory: Int): Single<Story> {
        return getDetailsStoryRepository.getDetailsStory(idStory)
    }
}