package space.active.dictionaryapi.domain.repository

import kotlinx.coroutines.flow.Flow
import space.active.dictionaryapi.domain.models.Word
import space.active.dictionaryapi.domain.utils.Resource


interface WordRepository {
    fun getWord(word: String): Flow<Resource<List<Word>>>
}