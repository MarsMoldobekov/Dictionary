package com.example.dictionary.frameworks.rx

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerProviderModule {
    @Singleton
    @Provides
    internal fun provideSchedulerProvider(): ISchedulerProvider {
        return SchedulerProvider()
    }
}