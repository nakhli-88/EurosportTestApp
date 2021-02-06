package com.nakhli.domain.model

/**
 * Video data class
 *
 * @property id
 * @property title
 * @property thumb
 * @property url
 * @property date
 * @property sport
 * @property views
 */

data class Video(
    override val id: Int,
    override val title: String,
    val thumb: String,
    val url: String,
    override val date: Double,
    override val sport: Sport,
    val views: Int
) : News()