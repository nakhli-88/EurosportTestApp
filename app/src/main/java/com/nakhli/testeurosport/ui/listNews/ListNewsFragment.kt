package com.nakhli.testeurosport.ui.listNews

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nakhli.domain.model.News
import com.nakhli.domain.model.Story
import com.nakhli.domain.model.Video
import com.nakhli.testeurosport.R
import com.nakhli.testeurosport.ui.detailsStory.DetailsStoryActivity
import com.nakhli.testeurosport.ui.detailsStory.DetailsStoryFragment.Companion.STORY_DETAILS_EXTRA
import com.nakhli.testeurosport.ui.toast
import com.nakhli.testeurosport.ui.videoPlay.VideoPlayActivity
import com.nakhli.testeurosport.ui.videoPlay.VideoPlayFragment.Companion.VIDEO_DETAILS_EXTRA
import kotlinx.android.synthetic.main.fragment_news_list.*
import timber.log.Timber

lateinit var listNewsAdapter: ListNewsAdapter
lateinit var listNewsViewModel: ListNewsViewModel
lateinit var news: MutableList<News>

class ListNewsFragment : Fragment(), ListNewsAdapter.ItemNewsClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onViewCreated()")
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        news = mutableListOf()
        listNewsAdapter =
            ListNewsAdapter(
                news,
                this@ListNewsFragment
            )
        listNewsAdapter.itemClickListner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        initView()
        initObservers()
    }

    private fun initView() {
        recycler_view_news_list.layoutManager = LinearLayoutManager(context)
        recycler_view_news_list.adapter =
            listNewsAdapter
    }

    private fun initObservers() {
        listNewsViewModel = ViewModelProviders.of(this).get(
            ListNewsViewModel::class.java
        )
        listNewsViewModel.newsLiveData.observe(viewLifecycleOwner, Observer { state ->
            updateUI(state)
        })
    }

    private fun updateUI(state: ListNewsViewModel.NewsFragmentState?) {
        when (state) {
            ListNewsViewModel.NewsFragmentState.OnLoading -> showLoadingState()
            is ListNewsViewModel.NewsFragmentState.OnFinish -> showLoadedState(state)
            is ListNewsViewModel.NewsFragmentState.OnError -> showErrorState(state)
        }
    }

    private fun showLoadingState() {
        Timber.i("Loading news")
        progress_bar_loading_items.visibility = View.VISIBLE
    }

    private fun showErrorState(error: ListNewsViewModel.NewsFragmentState.OnError) {
        Timber.e("error ${error.msgError}")
        progress_bar_loading_items.visibility = View.GONE
        context.toast(error.msgError)
    }

    private fun showLoadedState(loaded: ListNewsViewModel.NewsFragmentState.OnFinish) {
        Timber.i("Loaded state")
        progress_bar_loading_items.visibility = View.GONE
        if (loaded.newsList.isEmpty()) {
            Toast.makeText(context, "there is no news !!!", Toast.LENGTH_LONG).show()
        } else {
            news.clear()
            news.addAll(loaded.newsList)
            listNewsAdapter.notifyDataSetChanged()
        }
    }

    override fun onStoryClick(story: Story) {
        val storyIntent = Intent(context, DetailsStoryActivity::class.java)
        storyIntent.putExtra(STORY_DETAILS_EXTRA, story.id)
        startActivity(storyIntent)
    }

    override fun onVideoClick(video: Video) {
        val videoIntent = Intent(context, VideoPlayActivity::class.java)
        videoIntent.putExtra(VIDEO_DETAILS_EXTRA, video.id)
        startActivity(videoIntent)
    }
}