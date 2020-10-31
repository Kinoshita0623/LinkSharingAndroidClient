package jp.kinoshita.linksharingandroidclient.model.favorites

import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.model.users.User


data class Favorite(
    val note: Note,
    val user: User
)