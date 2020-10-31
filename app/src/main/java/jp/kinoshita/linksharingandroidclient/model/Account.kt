package jp.kinoshita.linksharingandroidclient.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val userId: Long,
    val token: String,
    //@Embedded val config: ConnectionConfig?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0

)