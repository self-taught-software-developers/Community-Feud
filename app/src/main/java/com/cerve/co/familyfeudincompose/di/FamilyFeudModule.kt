package com.cerve.co.familyfeudincompose.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.cerve.co.familyfeudincompose.data.database.FamilyFeudDatabase
import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FamilyFeudModule {

    private const val DATABASE_NAME = "FamilyFeudDatabase.db"
    private const val PRE_POPULATED_ROUTE = "database/familyfeudprepopulated.db"

    @Provides
    @Singleton
    fun provideFamilyFeudDatabase(@ApplicationContext context: Context) : FamilyFeudDatabase {
        return databaseBuilder(context, FamilyFeudDatabase::class.java, DATABASE_NAME)
            .createFromAsset(PRE_POPULATED_ROUTE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAnswerCardDao(database: FamilyFeudDatabase) = database.answerCardDao()

}