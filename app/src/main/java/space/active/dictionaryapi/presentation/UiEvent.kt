package space.active.dictionaryapi.presentation

sealed class UIEvent {
    data class ShowSnackbar(val message: String): UIEvent()
}