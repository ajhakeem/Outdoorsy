package com.example.outdoorsy.di

import com.example.outdoorsy.datasource.services.RentalService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://google.com/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
    }

    @Singleton
    @Provides
    fun provideRentalService(retrofitBuilder: Retrofit.Builder): RentalService {
        return  retrofitBuilder.build().create(RentalService::class.java)
    }

}