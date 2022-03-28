package com.blankspace.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.blankspace.dictionary.feature_dictionary.data.local.Converters
import com.blankspace.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.blankspace.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.blankspace.dictionary.feature_dictionary.data.reprository.WordInfoReprositoryImpl
import com.blankspace.dictionary.feature_dictionary.data.util.GsonParser
import com.blankspace.dictionary.feature_dictionary.domain.reprository.WordInfoReprository
import com.blankspace.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(reprository : WordInfoReprository): GetWordInfo{
        return GetWordInfo(reprository)
    }
    @Provides
    @Singleton
    fun provideWordInfoReprository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoReprository{
        return WordInfoReprositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java,"word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}