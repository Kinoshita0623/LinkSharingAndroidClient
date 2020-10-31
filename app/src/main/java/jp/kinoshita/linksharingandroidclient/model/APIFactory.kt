package jp.kinoshita.linksharingandroidclient.model

import jp.kinoshita.linksharingandroidclient.model.Account
import okhttp3.OkHttpClient

class APIFactory(
    val account: Account?,
    //val emitter: Subscriber<ConnectionConfig>
) {

    fun create(){
        val builder = OkHttpClient.Builder()

        if(account != null){
            builder.addInterceptor { chain ->
                val newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + account.token)
                    .build()
                return@addInterceptor chain.proceed(newRequest);

            }
        }
        val okHttpClient = builder.build()


    }

}
