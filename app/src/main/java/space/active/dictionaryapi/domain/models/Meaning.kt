package space.active.dictionaryapi.domain.models


data class Meaning (
    val definitions: List<Definition>,
    val partOfSpeech: String,
        ) {
    fun toRecycler(): String {
        return "${definitions.mapNotNull { it.toRecycler() }} \n $partOfSpeech"
    }
}
