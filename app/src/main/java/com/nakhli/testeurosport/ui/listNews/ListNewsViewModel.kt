package com.nakhli.testeurosport.ui.listNews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakhli.domain.model.News
import com.nakhli.domain.useCase.listNewsUseCase.GetNewsListUseCase
import com.nakhli.testeurosport.ui.listNews.ListNewsViewModel.NewsUsesCase.getNewsUsesCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 * List news ViewModel
 *
 */
class ListNewsViewModel : ViewModel() {
    private var disposable: Disposable? = null

    object NewsUsesCase : KoinComponent {
        val getNewsUsesCase: GetNewsListUseCase by inject()
    }

    val newsLiveData = MutableLiveData<NewsFragmentState>()

    sealed class NewsFragmentState {
        object OnLoading : NewsFragmentState()
        class OnFinish(val newsList: List<News>) : NewsFragmentState()
        class OnError(val msgError: String) : NewsFragmentState()
    }

    init {
        Timber.i("init")
        newsLiveData.value =
            NewsFragmentState.OnLoading
        disposable = loadNewsList()
    }

    private fun loadNewsList() =
        getNewsUsesCase.getNewsList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)

    private fun onSuccess(news: List<News>) {
        newsLiveData.value =
            NewsFragmentState.OnFinish(
                news
            )
    }

    private fun onError(throwable: Throwable) {
        newsLiveData.value =
            NewsFragmentState.OnError(
                throwable.toString()
            )
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}