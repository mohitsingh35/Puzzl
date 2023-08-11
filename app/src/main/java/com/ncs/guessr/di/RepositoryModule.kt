package com.ncs.guessr.di

import com.ncs.guessr.repository.RealtimeDBRepository
import com.ncs.guessr.repository.RealtimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesRealtimeRepository(
        repo: RealtimeDBRepository
    ): RealtimeRepository
}