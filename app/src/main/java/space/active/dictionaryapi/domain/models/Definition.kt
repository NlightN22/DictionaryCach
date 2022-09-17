package space.active.dictionaryapi.domain.models

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toRecycler(): String {
        return "$antonyms \n $definition \n $example \n $synonyms"
    }
}