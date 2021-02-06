package com.nakhli.domain.model

/**
 * Story data class
 *
 * @property id
 * @property title
 * @property teaser
 * @property image
 * @property date
 * @property author
 * @property sport
 */

data class Story (
    override val id: Int,
    override val title: String,
    val teaser: String,
    val image: String,
    override val date: Double,
    val author: String,
    override val sport: Sport
): News()