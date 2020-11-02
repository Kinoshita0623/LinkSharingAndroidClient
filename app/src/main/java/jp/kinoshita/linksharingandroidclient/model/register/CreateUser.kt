package jp.kinoshita.linksharingandroidclient.model.register

import com.google.gson.annotations.SerializedName

data class CreateUser(
    @SerializedName("user_name")
    val userName: String,

    val email: String,
    val password: String,

    @SerializedName("password_confirmation")
    val passwordConfirmation: String,

    @SerializedName("user_name")
    val deviceName: String
)
