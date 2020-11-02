package jp.kinoshita.linksharingandroidclient.model.login

import com.google.gson.annotations.SerializedName

data class Login(
    val email: String,
    val password: String,
    @SerializedName("device_name")
    val deviceName: String
)