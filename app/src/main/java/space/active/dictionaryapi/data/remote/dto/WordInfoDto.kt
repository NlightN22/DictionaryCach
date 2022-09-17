package space.active.dictionaryapi.data.remote.dto

import space.active.dictionaryapi.data.local.db.entity.WordInfoEntity

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordEntity(): WordInfoEntity {
        return WordInfoEntity (
            meanings = meanings,
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word
                )
    }
}