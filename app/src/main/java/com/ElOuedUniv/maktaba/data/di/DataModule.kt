package com.ElOuedUniv.maktaba.data.di

import android.content.Context
import android.content.SharedPreferences
import com.ElOuedUniv.maktaba.data.repository.BookRepository
import com.ElOuedUniv.maktaba.data.repository.SupabaseBookRepositoryImpl
import com.ElOuedUniv.maktaba.data.repository.CategoryRepository
import com.ElOuedUniv.maktaba.data.repository.SupabaseCategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        repository: SupabaseCategoryRepositoryImpl
    ): CategoryRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        repository: SupabaseBookRepositoryImpl
    ): BookRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("hasCompletedOnboarding", Context.MODE_PRIVATE)
    }
}
