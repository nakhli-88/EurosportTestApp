package com.nakhli.data.api

import com.nakhli.domain.model.NewsWrapper
import io.reactivex.Observable
import retrofit2.http.GET

private const val NEWS_URL = "/api/json-storage/bin/edfefba"

/**
 * Interface to handle api service
 *
 */
interface ApiService {
    @GET(NEWS_URL)
    fun getNewsListFomWs(): Observable<NewsWrapper>
}