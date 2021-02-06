package com.nakhli.domain.newsListTest

import com.nakhli.domain.model.*
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListRepository
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListUseCase
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class GetNewsListUseCaseTest {

    private val getNewsListRepository = Mockito.mock(GetNewsListRepository::class.java)
    private val getNewsListUseCase = GetNewsListUseCase(getNewsListRepository)

    private val story1 =
        Story(
            1,
            "story1",
            "teaser1",
            "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
            1000.44,
            "nakhli",
            Sport(4, "FOOTBALL")
        )
    private val story2 =
        Story(
            2,
            "story2",
            "teaser2",
            "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
            1600.44,
            "nakhli",
            Sport(4, "FOOTBALL")
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

    private val listVideos = listOf(video1, video2)
    private val listStories = listOf(story1, story2)

    // Sorted news list by date
    private val listNews = listOf(story2, video2, story1, video1)


    @Test
    fun success() {
        Mockito.`when`(getNewsListRepository.getNewsList())
            .thenReturn(Observable.just(NewsWrapper(listStories, listVideos)))

        val testObserver = TestObserver<List<News>>()
        val observable = getNewsListUseCase.getNewsList()

        observable.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Assertions.assertThat(testObserver.values())
            .containsExactly(listNews)

        verify(getNewsListRepository, times(1)).getNewsList()
        Mockito.verifyNoMoreInteractions(getNewsListRepository)
    }


    @Test
    fun error() {
        val testException = Exception()
        Mockito.`when`(getNewsListRepository.getNewsList())
            .thenReturn(Observable.error(testException))

        val valueResponseObservable = getNewsListUseCase.getNewsList()
        val testObserver = TestObserver<List<News>>()

        valueResponseObservable.subscribe(testObserver)

        verify(getNewsListRepository).getNewsList()
        Mockito.verifyNoMoreInteractions(getNewsListRepository)
        testObserver.assertError(testException)
    }
}