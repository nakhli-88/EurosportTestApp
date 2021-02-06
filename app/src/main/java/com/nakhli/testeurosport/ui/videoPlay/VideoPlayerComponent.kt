package com.nakhli.testeurosport.ui.videoPlay

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.nakhli.testeurosport.R
import timber.log.Timber

class VideoPlayerComponent(
    private val context: Context,
    private val playerView: PlayerView,
    private val url: String
) : LifecycleObserver {

    private var mPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    private fun initPlayer() {
        mPlayer = context.let { SimpleExoPlayer.Builder(it).build() }
        // Bind the player to the view.
        playerView.player = mPlayer

        mPlayer!!.playWhenReady = true
        mPlayer!!.seekTo(currentWindow, playbackPosition)
        mPlayer!!.prepare(buildMediaSource(url), false, false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartPlayer() {
        Timber.i("onStartPlayer")
        initPlayer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumePlayer() {
        Timber.i("onResumePlayer")
        hideSystemUi()
        if (mPlayer == null) {
            initPlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopPlayer() {
        Timber.i("onStopPlayer")
        releasePlayer()
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun releasePlayer() {
        if (mPlayer == null) {
            return
        }
        playWhenReady = mPlayer!!.playWhenReady
        playbackPosition = mPlayer!!.currentPosition
        currentWindow = mPlayer!!.currentWindowIndex
        mPlayer!!.release()
        mPlayer = null
    }

    private fun buildMediaSource(url: String): MediaSource {
        val userAgent =
            Util.getUserAgent(playerView.context, playerView.context.getString(R.string.app_name))

        val dataSourceFactory = DefaultHttpDataSourceFactory(userAgent)
        val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url), null, null)
        return mediaSource
    }
}