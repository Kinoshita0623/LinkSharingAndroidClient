package jp.kinoshita.linksharingandroidclient.model

import com.google.gson.annotations.SerializedName

data class Page<T>(
    @SerializedName("current_page")
    val currentPage: Int,

    val data: List<T>,

    @SerializedName("first_page_url")
    val firstPageUrl: String,

    val from: Int?,

    @SerializedName("last_page")
    val lastPage: Int?,

    @SerializedName("last_page_url")
    val lastPageUrl: String?,

    @SerializedName("next_page_url")
    val nextPageUrl: String?,

    val path: String,

    @SerializedName("per_page")
    val perPage: Int,

    val to: Int?,
    val total: Int?
)