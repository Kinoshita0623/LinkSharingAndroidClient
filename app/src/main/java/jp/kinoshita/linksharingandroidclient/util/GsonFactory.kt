package jp.kinoshita.linksharingandroidclient.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*

object GsonFactory {
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()


    fun create(): Gson{
        return gson
    }
}