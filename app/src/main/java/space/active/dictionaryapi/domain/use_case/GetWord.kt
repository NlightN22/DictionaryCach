package space.active.dictionaryapi.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import space.active.dictionaryapi.domain.models.Word
import space.active.dictionaryapi.domain.repository.WordRepository
import space.active.dictionaryapi.domain.utils.Resource

class GetWord(
    private val repository: WordRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<Word>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWord(word)
    }
}