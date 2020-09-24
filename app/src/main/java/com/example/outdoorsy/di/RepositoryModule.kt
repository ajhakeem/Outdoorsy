package com.example.outdoorsy.di

import com.example.outdoorsy.data.repository.RentalRepository
import com.example.outdoorsy.datasource.services.RentalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRentalRepository(
        rentalService: RentalService
    ): RentalRepository {
        return RentalRepository(rentalService)
    }

}