package jp.kinoshita.linksharingandroidclient.model.comments

import com.google.gson.annotations.SerializedName

data class Comment(
    val id: Long,
    @SerializedName("commentable_id") val commentableId: Long,
    val type: String,
    val text: String
)