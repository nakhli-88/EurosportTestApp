package com.nakhli.domain.model

/**
 *  Wrapper news data class
 *
 * @property stories
 * @property videos
 */

data class NewsWrapper(val stories: List<Story>, val videos: List<Video>)