package screen

import Note

sealed interface SharedEvents {
    data class ClickNote(
        val note: Note
    ) : SharedEvents

    data class SaveNote(
        val note: Note
    ) : SharedEvents

    data class DeleteNote(
        val note: Note
    ) : SharedEvents

    data object OnBack : SharedEvents
}