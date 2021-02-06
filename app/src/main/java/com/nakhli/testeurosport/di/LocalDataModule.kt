package com.nakhli.testeurosport.di

import androidx.room.Room
import com.nakhli.data.db.EuroSportDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val LocalDataModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            EuroSportDataBase::class.java, "eurosport_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<EuroSportDataBase>().newsDao() }
}