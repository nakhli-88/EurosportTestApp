package com.nakhli.domain.storyByIdTest

import com.nakhli.domain.model.Sport
import com.nakhli.domain.model.Story
import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryRepository
import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito

class GetStoryByIdUseCaseTest {

    //mock gateway
    private val geStoryByIdRepository =
        Mockito.mock(GetDetailsStoryRepository::class.java)
    private val getStoryByIdUseCase =
        GetDetailsStoryUseCase(
            geStoryByIdRepository
        )


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

    private val story3 =
        Story(
            3,
            "story3",
            "teaser3",
            "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
            1000.44,
            "nakhli",
            Sport(4, "FOOTBALL")
        )

    private val stories = listOf(story1,story2,story3)

    @Test
    fun success() {
        Mockito.`when`(geStoryByIdRepository.getDetailsStory(stories[0].id))
            .thenReturn(Single.just(story1))

        val testObserver = TestObserver<Story>()
        val single = getStoryByIdUseCase.getDetailsStory(stories[0].id)

        single.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Assertions.assertThat(testObserver.values())
            .containsExactly(story1)

        Mockito.verify(geStoryByIdRepository, Mockito.times(1))
            .getDetailsStory(stories[0].id)
        Mockito.verifyNoMoreInteractions(geStoryByIdRepository)
    }


    @Test
    fun error() {
        val testException = Exception()
        Mockito.`when`(geStoryByIdRepository.getDetailsStory(stories[0].id))
            .thenReturn(Single.error(testException))

        val valueResponseObservable = getStoryByIdUseCase.getDetailsStory(stories[0].id)
        val testObserver = TestObserver<Story>()

        valueResponseObservable.subscribe(testObserver)

        Mockito.verify(geStoryByIdRepository).getDetailsStory(stories[0].id)
        Mockito.verifyNoMoreInteractions(geStoryByIdRepository)

        testObserver.assertError(testException)
    }

}