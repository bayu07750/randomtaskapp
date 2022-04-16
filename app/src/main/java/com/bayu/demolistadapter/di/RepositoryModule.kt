package com.bayu.demolistadapter.di

import com.bayu.demolistadapter.core.data.repository.TaskRepositoryImp
import com.bayu.demolistadapter.domain.task.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideTaskRepository(imp: TaskRepositoryImp): TaskRepository
}