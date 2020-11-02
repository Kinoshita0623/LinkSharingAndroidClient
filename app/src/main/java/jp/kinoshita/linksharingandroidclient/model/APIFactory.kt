package jp.kinoshita.linksharingandroidclient.model

import com.google.gson.Gson
import jp.kinoshita.linksharingandroidclient.util.GsonFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIFactory(
    val account: Account?,
    val baseURL: String = "http://localhost:8000/api/",
    val gson: Gson = GsonFactory.create()
    //val emitter: Subscriber<ConnectionConfig>
) {

    fun create(): API{
        val builder = OkHttpClient.Builder()

        if(account != null){
            builder.addInterceptor { chain ->
                val newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + account.token)
                    .build()
                return@addInterceptor chain.proceed(newRequest)

            }
        }
        val okHttpClient = builder.build()


        val retrofitBuilder  = Retrofit.Builder()
        val retrofit = retrofitBuilder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(API::class.java)

    }

}
