package com.nakhli.testeurosport.ui.videoPlay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nakhli.domain.model.Video
import com.nakhli.testeurosport.R
import kotlinx.android.synthetic.main.fragment_video_player.*
import timber.log.Timber

lateinit var videoDetailsViewModel: VideoPlayViewModel

class VideoPlayFragment : Fragment() {
    companion object {
        const val VIDEO_DETAILS_EXTRA = "video_details"
        fun newVideoInstance() = VideoPlayFragment()
    }

    private var videoId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView()")
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        if (activity?.intent!!.hasExtra(VIDEO_DETAILS_EXTRA)) {
            videoId = requireActivity().intent.getIntExtra(VIDEO_DETAILS_EXTRA, -1)
        }

        videoDetailsViewModel = ViewModelProviders.of(this).get(VideoPlayViewModel::class.java)
        videoId?.let { videoDetailsViewModel.getVideoDetails(it) }
        videoDetailsViewModel.videoDetailsLiveData.observe(viewLifecycleOwner, Observer { video ->
            playVideo(video)
        })
    }

    private fun playVideo(video: Video) {
        val videoPlayerComponent =
            VideoPlayerComponent(requireActivity().applicationContext, playerView, video.url)
        lifecycle.addObserver(videoPlayerComponent)
    }
}