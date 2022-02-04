package com.example.dictionary.frameworks.rx

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CompositeDisposableProviderModule {
    @Singleton
    @Provides
    internal fun provideCompositeDisposableProvider(): ICompositeDisposableProvider {
        return CompositeDisposableProvider()
    }
}