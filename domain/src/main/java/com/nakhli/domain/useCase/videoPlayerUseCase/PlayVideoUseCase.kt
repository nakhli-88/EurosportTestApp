package com.nakhli.domain.useCase.videoPlayerUseCase

import com.nakhli.domain.model.Video
import io.reactivex.Single

/**
 * Class to handle get video by id use case
 *
 * @property playVideoRepository
 */
class PlayVideoUseCase(val playVideoRepository: PlayVideoRepository) {
    fun getVideo(videoId: Int): Single<Video> {
        return playVideoRepository.getVideo(videoId)
    }
}