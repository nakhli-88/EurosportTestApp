package com.nakhli.testeurosport.ui.listNews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nakhli.domain.model.News
import com.nakhli.domain.model.Story
import com.nakhli.domain.model.Video
import com.nakhli.testeurosport.R
import com.nakhli.testeurosport.ui.CurrentNewsType
import com.nakhli.testeurosport.ui.setImage
import kotlinx.android.synthetic.main.story_item.view.*
import kotlinx.android.synthetic.main.video_item.view.*

class ListNewsAdapter(
    private val newslist: List<News>,
    var itemClickListner: ItemNewsClickListener?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    interface ItemNewsClickListener {
        fun onStoryClick(story: Story)
        fun onVideoClick(video: Video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mContext: Context = parent.context
        return if (viewType == CurrentNewsType.STORY_TYPE.type) {
            val storyView: View =
                LayoutInflater.from(mContext).inflate(R.layout.story_item, parent, false)
            StoryViewHolder(
                storyView
            )
        } else {
            val videoView =
                LayoutInflater.from(mContext).inflate(R.layout.video_item, parent, false)
            VideoViewHolder(
                videoView
            )
        }
    }

    override fun getItemCount() = newslist.size

    override fun getItemViewType(position: Int) =
        if (newslist[position] is Story) CurrentNewsType.STORY_TYPE.type else CurrentNewsType.VIDEO_TYPE.type

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = newslist[position]
        when (getItemViewType(position)) {
            CurrentNewsType.STORY_TYPE.type -> {
                val story = news as Story
                with(holder as StoryViewHolder) {
                    setUiStoryItem(story)
                    storyCardItem.setOnClickListener(this@ListNewsAdapter)
                }
            }
            CurrentNewsType.VIDEO_TYPE.type -> {
                val video = news as Video
                with(holder as VideoViewHolder) {
                    setUiVideoItem(video)
                    videoCardItem.setOnClickListener(this@ListNewsAdapter)
                }
            }
        }
    }


    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mStoryContext = itemView.context
        private val storyImage: ImageView = itemView.image_story
        private val storySport: TextView = itemView.sport_type_story
        private val storyTitle: TextView = itemView.title_story
        private val storyAuthor: TextView = itemView.author_story

        //StoryCard
        val storyCardItem: CardView = itemView.card_item_story

        fun setUiStoryItem(story: Story) {
            storySport.text = story.sport.name
            storyTitle.text = story.title
            storyAuthor.text = mStoryContext.getString(R.string.story_author_txt, story.author)

            mStoryContext.setImage(story.image, storyImage)
            storyCardItem.tag = story
        }
    }

    private class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mVideoContext = itemView.context
        private val videoSport: TextView = itemView.sport_type_video
        private val videoTitle: TextView = itemView.video_title
        private val videoViews: TextView = itemView.video_views
        private val videoImage: ImageView = itemView.video_image

        //VideoCard
        val videoCardItem: CardView = itemView.card_item_video

        fun setUiVideoItem(video: Video) {
            videoSport.text = video.sport.name
            videoTitle.text = video.title
            videoViews.text =
                mVideoContext.getString(R.string.video_views_txt, video.views.toString())

            mVideoContext.setImage(video.thumb, videoImage)
            videoCardItem.tag = video
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.card_item_story -> itemClickListner?.onStoryClick(v.tag as Story)
            R.id.card_item_video -> itemClickListner?.onVideoClick(v.tag as Video)
        }
    }
}