package space.active.dictionaryapi.domain.models

data class Word(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toRecycler(): String {
        return "${meanings.mapNotNull { it.toRecycler() }} \n $phonetic \n $sourceUrls \n $word"

    }
}