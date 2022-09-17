package space.active.dictionaryapi.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import space.active.dictionaryapi.data.local.db.WordInfoDao
import space.active.dictionaryapi.data.remote.DictionaryApi
import space.active.dictionaryapi.domain.models.Word
import space.active.dictionaryapi.domain.repository.WordRepository
import space.active.dictionaryapi.domain.utils.Resource
import java.io.IOException

const val TAG_REPO = "Dictionary Repo"

class WordRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordRepository {
    override fun getWord(word: String): Flow<Resource<List<Word>>> = flow {
        emit(Resource.Loading())

        val wordLocal = dao.getWordInfo(word).map { it.toWord() }
        emit(Resource.Loading(data = wordLocal))

        try {
            val wordRemote = api.getWordInfo(word)
            dao.deleteWordInfo(wordRemote.map { it.word })
            dao.insertWordInfo(wordRemote.map { it.toWordEntity() })
            Log.e(TAG_REPO, "wordRemote: $wordRemote")
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Http exception: ${e.message}"
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Check internet connection!",
                data = wordLocal
            ))
        }

        val wordUpdated = dao.getWordInfo(word).map { it.toWord() }
        Log.e(TAG_REPO, "wordUpdated: $wordUpdated")
        emit(Resource.Success(wordUpdated))
    }
}