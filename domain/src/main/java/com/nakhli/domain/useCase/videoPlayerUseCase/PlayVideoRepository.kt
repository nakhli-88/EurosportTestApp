package com.nakhli.domain.useCase.videoPlayerUseCase

import com.nakhli.domain.model.Video
import io.reactivex.Single

/**
 * Get video by Video id repository
 *
 */
interface PlayVideoRepository {

    fun getVideo(idVideo: Int): Single<Video>
}