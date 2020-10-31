package jp.kinoshita.linksharingandroidclient.model.notes

import com.google.gson.annotations.SerializedName
import jp.kinoshita.linksharingandroidclient.model.users.User

data class Note(
    val id: Long,
    val summary: Summary,
    val author: User,
    val text: String,
    val tags: List<Tag>,

    @SerializedName("is_favorite")
    val isFavorite: Boolean

)