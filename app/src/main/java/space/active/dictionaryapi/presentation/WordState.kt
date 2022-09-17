package space.active.dictionaryapi.presentation

import space.active.dictionaryapi.domain.models.Word

data class WordState(
    val wordItems: List<Word> = emptyList(),
    val isLoading: Boolean = false
)
