package com.nakhli.testeurosport.ui.videoPlay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakhli.domain.model.Video
import com.nakhli.domain.useCase.videoPlayerUseCase.PlayVideoUseCase
import com.nakhli.testeurosport.ui.videoPlay.VideoPlayViewModel.VideoDetailsUsesCase.getVideoDetailsUsesCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 * Video ViewModel
 *
 */
class VideoPlayViewModel : ViewModel() {
    private var disposable: Disposable? = null

    val videoDetailsLiveData = MutableLiveData<Video>()

    object VideoDetailsUsesCase : KoinComponent {
        val getVideoDetailsUsesCase: PlayVideoUseCase by inject()
    }

    fun getVideoDetails(videoId: Int) {
        disposable = getVideoDetailsUsesCase.playVideoRepository.getVideo(videoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSucees){
                Timber.e(it)
            }
    }

    private fun onSucees(video: Video) {
        videoDetailsLiveData.value = video
    }
}