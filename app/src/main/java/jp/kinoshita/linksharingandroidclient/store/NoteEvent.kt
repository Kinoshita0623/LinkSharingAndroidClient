package jp.kinoshita.linksharingandroidclient.store

import jp.kinoshita.linksharingandroidclient.model.notes.Note

sealed class NoteEvent{

    data class Added(
        val note: Note
    ) : NoteEvent()

    data class Deleted(
        val noteId: Long
    ) : NoteEvent()


}