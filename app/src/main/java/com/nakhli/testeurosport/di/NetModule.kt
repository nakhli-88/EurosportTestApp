package com.nakhli.testeurosport.di

import com.nakhli.data.api.ApiService
import com.nakhli.testeurosport.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val NetModule = module {

    single { provideInterceptLogging() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofitClient(get()) }

    single { provideNewsWs(get()) }
}

fun provideInterceptLogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideOkHttpClient(loginInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()


fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.SERVICE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()


fun provideNewsWs(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)