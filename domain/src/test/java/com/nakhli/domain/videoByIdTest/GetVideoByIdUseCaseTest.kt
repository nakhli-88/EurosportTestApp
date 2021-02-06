package com.nakhli.domain.storyByIdTest

import com.nakhli.domain.model.Sport
import com.nakhli.domain.model.Video
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoRepository
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito

class GetVideoByIdUseCaseTest {

    private val geVideoByIdRepository =
        Mockito.mock(PlayVideoRepository::class.java)
    private val getVideoByIdUseCase =
        PlayVideoUseCase(
            geVideoByIdRepository
        )


    private val video1 =
        Video(
            1,
            "video1",
            "https://i.eurosport.com/40.jpg",
            "https://vod-eurosport.akamaized.net/ebu-au/2019/12/08/trophey-1269106-700-512-288.mp4",
            500.4879,
            Sport(4, "FOOTBALL"),
            345
        )
    private val video2 =
        Video(
            2,
            "video1",
            "https://i.eurosport.com/40.jpg",
            "https://vod-eurosport.akamaized.net/ebu-au/2019/12/08/trophey-1269106-700-512-288.mp4",
            21000.5767,
            Sport(4, "FOOTBALL"),
            345
        )

    private val videos = listOf(video1,video2)

    @Test
    fun success() {
        Mockito.`when`(geVideoByIdRepository.getVideo(videos[1].id))
            .thenReturn(Single.just(video2))

        val testObserver = TestObserver<Video>()
        val single = getVideoByIdUseCase.getVideo(videos[1].id)

        single.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Assertions.assertThat(testObserver.values())
            .containsExactly(video2)

        Mockito.verify(geVideoByIdRepository, Mockito.times(1))
            .getVideo(videos[1].id)
        Mockito.verifyNoMoreInteractions(geVideoByIdRepository)
    }


    @Test
    fun error() {
        val testException = Exception()
        Mockito.`when`(geVideoByIdRepository.getVideo(videos[1].id))
            .thenReturn(Single.error(testException))

        val valueResponseObservable = getVideoByIdUseCase.getVideo(videos[1].id)
        val testObserver = TestObserver<Video>()

        valueResponseObservable.subscribe(testObserver)

        Mockito.verify(geVideoByIdRepository).getVideo(videos[1].id)
        Mockito.verifyNoMoreInteractions(geVideoByIdRepository)

        testObserver.assertError(testException)
    }

}