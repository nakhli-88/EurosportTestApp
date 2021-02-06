package com.nakhli.testeurosport.di

import com.nakhli.testeurosport.ui.listNews.ListNewsViewModel
import com.nakhli.testeurosport.ui.detailsStory.DetailsStoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { DetailsStoryViewModel(get()) }
}