package com.nakhli.domain.model

/**
 * News data class
 *
 * @property id
 * @property title
 * @property date
 * @property sport
 */

abstract class News {
    abstract val id: Int
    abstract val title: String
    abstract val date: Double
    abstract val sport: Sport
}