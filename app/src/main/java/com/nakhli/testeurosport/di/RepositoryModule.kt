package com.nakhli.testeurosport.di


import com.nakhli.data.repository.NewsRepositoryImpl
import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryRepository
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListRepository
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoRepository
import org.koin.dsl.module

val GatewayModule = module {
    single { NewsRepositoryImpl(get(), get()) as GetNewsListRepository }
    single { NewsRepositoryImpl(get(), get()) as GetDetailsStoryRepository }
    single { NewsRepositoryImpl(get(), get()) as PlayVideoRepository }
}