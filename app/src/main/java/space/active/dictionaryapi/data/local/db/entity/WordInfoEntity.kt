package space.active.dictionaryapi.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import space.active.dictionaryapi.data.remote.dto.MeaningDto
import space.active.dictionaryapi.domain.models.Word

@Entity
data class WordInfoEntity (
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWord() : Word {
        return Word(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word
        )
    }
}