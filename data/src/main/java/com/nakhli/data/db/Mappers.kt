package com.nakhli.data.db

import com.nakhli.data.db.entity.StoryEntity
import com.nakhli.data.db.entity.VideoEntity
import com.nakhli.domain.model.Story
import com.nakhli.domain.model.Video

fun mapStoryDataToEntity(story: Story): StoryEntity = StoryEntity(
    storyId = story.id,
    storyTitle = story.title,
    storyTeaser = story.teaser,
    storyImage = story.image,
    storyDate = story.date,
    storyAuthor = story.author,
    sport = story.sport
)

fun mapStoriesDataToEntities(stories: List<Story>): List<StoryEntity> {
    val storyEntities = mutableListOf<StoryEntity>()
    for (story in stories) {
        storyEntities.add(mapStoryDataToEntity(story))
    }
    return storyEntities
}

fun mapStoryEntityToData(storyEntity: StoryEntity): Story = Story(
    id = storyEntity.storyId,
    title = storyEntity.storyTitle,
    teaser = storyEntity.storyTeaser,
    image = storyEntity.storyImage,
    date = storyEntity.storyDate,
    author = storyEntity.storyAuthor,
    sport = storyEntity.sport
)

fun mapStoriesEntitiesToData(storiesEntities: List<StoryEntity>): List<Story> {
    val stories = mutableListOf<Story>()
    for (storyEntity in storiesEntities) {
        stories.add(mapStoryEntityToData(storyEntity))
    }
    return stories
}


fun mapVideoDataToEntity(video: Video): VideoEntity = VideoEntity(
    videoId = video.id ,
    videoTitle = video.title,
    videoThumb = video.thumb,
    videoUrl = video.url,
    videoDate = video.date,
    sport = video.sport,
    videoViews = video.views
)

fun mapVideosDataToEntities(videos: List<Video>): List<VideoEntity> {
    val videosEntities = mutableListOf<VideoEntity>()
    for (video in videos) {
        videosEntities.add(mapVideoDataToEntity(video))
    }
    return videosEntities
}

fun mapVideoEntityToData(videoEntity: VideoEntity): Video = Video(
    id=videoEntity.videoId,
    title = videoEntity.videoTitle,
    thumb = videoEntity.videoThumb,
    url = videoEntity.videoUrl,
    date = videoEntity.videoDate,
    sport = videoEntity.sport,
    views = videoEntity.videoViews
)

// used in case when we need to store videos into local dataBase
fun mapVideosEntitiesToData(videosEntities: List<VideoEntity>): List<Video> {
    val videos = mutableListOf<Video>()
    for (videoEntity in videosEntities) {
        videos.add(mapVideoEntityToData(videoEntity))
    }
    return videos
}
