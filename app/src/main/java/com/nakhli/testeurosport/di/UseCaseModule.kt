package com.nakhli.testeurosport.di

import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryUseCase
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListUseCase
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoUseCase
import org.koin.dsl.module


val UseCaseModule = module {
    single { GetNewsListUseCase(get()) }
    single { GetDetailsStoryUseCase(get()) }
    single { PlayVideoUseCase(get()) }
}