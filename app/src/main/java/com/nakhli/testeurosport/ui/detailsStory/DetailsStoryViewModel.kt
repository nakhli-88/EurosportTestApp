package com.nakhli.testeurosport.ui.detailsStory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakhli.domain.model.Story
import com.nakhli.domain.useCase.detailsNewsUseCase.GetDetailsStoryUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Details story ViewModel
 *
 * @param getDetailsUsesCase
 */
class DetailsStoryViewModel(private val getDetailsUsesCase: GetDetailsStoryUseCase) : ViewModel() {
    private var disposable: Disposable? = null

    val detailsLiveData = MutableLiveData<Story>()

    fun getDetails(idNews: Int) {
        disposable = getDetailsUsesCase.getDetailsStory(idNews)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSucees){
                Timber.e(it)
            }
    }

    private fun onSucees(story: Story) {
        detailsLiveData.value = story
    }
}