package space.active.dictionaryapi.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.active.dictionaryapi.data.local.db.Converters
import space.active.dictionaryapi.data.local.db.WordInfoDao
import space.active.dictionaryapi.data.local.db.WordInfoDatabase
import space.active.dictionaryapi.data.local.db.util.GsonParser
import space.active.dictionaryapi.data.remote.DictionaryApi
import space.active.dictionaryapi.data.repository.WordRepositoryImpl
import space.active.dictionaryapi.domain.repository.WordRepository
import space.active.dictionaryapi.domain.use_case.GetWord
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {

    @Provides
    @Singleton
    fun provideGetWordUseCase(repository: WordRepository): GetWord {
        return GetWord(repository)
    }

    @Provides
    @Singleton
    fun provideWordRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordRepository {
        return WordRepositoryImpl(api, dao = db.dao)
    }

    @Provides
    @Singleton
    fun provideWordDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}