package jp.kinoshita.linksharingandroidclient.model.users

import com.google.gson.annotations.SerializedName
import java.util.*


data class User(
    val id: Long,
    @SerializedName("user_name") val userName: String,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("followers_count") val followersCount: Int?,
    @SerializedName("followings_count") val followingsCount: Int?,
    @SerializedName("notes_count") val notesCount: Int?,
    @SerializedName("favorited_notes_count") val favoriteNotesCount: Int?

)