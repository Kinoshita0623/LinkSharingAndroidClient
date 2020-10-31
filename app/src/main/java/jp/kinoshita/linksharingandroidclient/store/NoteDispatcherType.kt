package jp.kinoshita.linksharingandroidclient.store

import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.model.users.User

sealed class NoteDispatcherType{

    data class Add(
        val note: Note
    ) : NoteDispatcherType()

    data class Delete(
        val noteId: Long
    ) : NoteDispatcherType()

    data class Favorite(
        val note: Note,
        val user: User
    ) : NoteDispatcherType()

    data class UnFavorited(
        val note: Note,
        val user: User
    ) : NoteDispatcherType()




}