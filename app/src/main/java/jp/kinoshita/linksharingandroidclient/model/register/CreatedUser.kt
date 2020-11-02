package jp.kinoshita.linksharingandroidclient.model.register

import com.google.gson.annotations.SerializedName
import jp.kinoshita.linksharingandroidclient.model.users.User
import java.util.*

/*
{
    "token":{
        "accessToken":{
            "name":"Postman",
            "abilities":["*"],
            "tokenable_id":106,
            "tokenable_type":"App\\Models\\User",
            "updated_at":"2020-11-02T06:48:28.000000Z",
            "created_at":"2020-11-02T06:48:28.000000Z","id":4},
            "plainTextToken":"4|4DDipkr7OFSFbrxOz5jxczb1CzlHnIvdMdLnjwMc"
         },
    "user":{"user_name":"hogepi23","updated_at":"2020-11-02T06:48:28.000000Z","created_at":"2020-11-02T06:48:28.000000Z","id":106}}
 */
data class CreatedUser(
    val token: AccessToken,
    val user: User
)

data class AccessToken(
    val name: String,
    val abilities: List<String>,

    @SerializedName("tokenable_id")
    val tokenableId: String,

    @SerializedName("tokenable_type")
    val tokenType: String,

    @SerializedName("updated_at")
    val updatedAt: Date,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("plainTextToken")
    val token: String

)