package jp.kinoshita.linksharingandroidclient.model.notes

import com.google.gson.annotations.SerializedName

data class IsFavorite(
    @SerializedName("is_favorite") val isFavorite: Boolean
)