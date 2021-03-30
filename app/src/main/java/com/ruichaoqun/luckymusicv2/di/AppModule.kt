package com.ruichaoqun.luckymusicv2.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.ruichaoqun.luckymusicv2.data.ApiService
import com.ruichaoqun.luckymusicv2.data.source.AppRepository
import com.ruichaoqun.luckymusicv2.data.source.DataSource
import com.ruichaoqun.luckymusicv2.data.source.DefaultAppRepository
import com.ruichaoqun.luckymusicv2.data.source.local.TaskLocalDataSource
import com.ruichaoqun.luckymusicv2.data.source.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 18:26
 * @Description:    AppModule
 * @Version:        1.0
 */
@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalTasksDataSource

    @Singleton
    @LocalTasksDataSource
    @Provides
    fun provideTasksLocalDataSource(
        database: ToDoDatabase,
        ioDispatcher: CoroutineDispatcher
    ):DataSource{
        return TaskLocalDataSource(database.taskDao(),ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):ToDoDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "Tasks.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideTaskRepository(
        @AppModule.LocalTasksDataSource local:DataSource,
        dispatcher:CoroutineDispatcher,
        apiService: ApiService
    ):AppRepository{
        return DefaultAppRepository(local,dispatcher,apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(client:OkHttpClient,gson:Gson): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideGson():Gson{
        return Gson()
    }
}