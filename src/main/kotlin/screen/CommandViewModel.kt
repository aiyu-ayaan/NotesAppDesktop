package screen

import Note
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class CommandViewModel {
    var currentClickNote: Note? = null

    private var _list = mutableStateOf(listOf<Note>())
    val noteList: State<List<Note>> = _list


    fun onEvent(event: SharedEvents) {
        when (event) {
            is SharedEvents.ClickNote ->
                currentClickNote = event.note


            is SharedEvents.SaveNote -> {
                if (event.note.created == 0L) {
                    add(
                        event.note.copy(
                            created = System.currentTimeMillis()
                        )
                    )
                } else {
                    update(event.note)
                }
            }

            is SharedEvents.DeleteNote ->
                remove(event.note)


            SharedEvents.OnBack ->
                currentClickNote = null

        }
    }


    private fun add(note: Note) {
        _list.value += note
    }

    private fun remove(note: Note) {
        _list.value -= note
    }

    private fun update(note: Note) {
        _list.value = _list.value.map {
            if (it.created == note.created) {
                note
            } else {
                it
            }
        }
    }
}