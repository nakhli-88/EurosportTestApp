package com.nakhli.testeurosport.ui.detailsStory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nakhli.domain.model.Story
import com.nakhli.testeurosport.R
import com.nakhli.testeurosport.ui.setImage
import kotlinx.android.synthetic.main.fragment_story_details.*
import kotlinx.android.synthetic.main.fragment_story_details.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class DetailsStoryFragment : Fragment() {

    companion object {
        const val STORY_DETAILS_EXTRA = "story_details"
        fun newDetailsStoryInstance() = DetailsStoryFragment()
    }

    private val detailsViewModel: DetailsStoryViewModel by viewModel()

    private var storyId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView()")
        val view = inflater.inflate(R.layout.fragment_story_details, container, false)
        view.story_details_back_arrow.setOnClickListener { requireActivity().onBackPressed() }
        view.story_details_share.setOnClickListener {
            shareStory()
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        if (activity?.intent!!.hasExtra(STORY_DETAILS_EXTRA)) {
            storyId = requireActivity().intent.getIntExtra(STORY_DETAILS_EXTRA, -1)
        }

        storyId?.let { detailsViewModel.getDetails(it) }
        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { story ->
            updateUi(story)
        })
    }

    private fun updateUi(story: Story) {
        context?.setImage(story.image, story_details_image)
        story_details_author.text = context?.getString(R.string.story_author_txt, story.author)
        story_details_description.text = story.teaser
        story_details_type.text = story.sport.name
        story_details_title.text = story.title
    }

    private fun shareStory() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share story To:"))
    }
}