package com.example.summonertimer.di

import com.example.summonertimer.data.remote.DragonApi
import com.example.summonertimer.data.remote.SummonerApi
import com.example.summonertimer.data.repository.DragonRepositoryImpl
import com.example.summonertimer.data.repository.SummonerRepositoryImpl
import com.example.summonertimer.domain.repository.DragonRepository
import com.example.summonertimer.domain.repository.SummonerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSummonerApi():SummonerApi{
        return Retrofit.Builder()
            .baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SummonerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSummonerRepository(api: SummonerApi):SummonerRepository{
        return SummonerRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDragonApi():DragonApi{
        return Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDragonRepository(api:DragonApi):DragonRepository{
        return DragonRepositoryImpl(api)
    }


}