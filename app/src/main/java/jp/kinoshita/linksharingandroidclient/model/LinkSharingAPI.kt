package jp.kinoshita.linksharingandroidclient.model

import io.reactivex.Single
import jp.kinoshita.linksharingandroidclient.model.comments.Comment
import jp.kinoshita.linksharingandroidapp.model.comments.CreateComment
import jp.kinoshita.linksharingandroidclient.model.favorites.Favorite
import jp.kinoshita.linksharingandroidclient.model.notes.CreateNote
import jp.kinoshita.linksharingandroidclient.model.notes.IsFavorite
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.model.users.User
import retrofit2.Response
import retrofit2.http.*


interface LinkSharingAPI {



    @GET("notes/{noteId}")
    fun getNote(@Path("noteId") noteId: Long): Single<Response<Note>>

    @GET("users/{userId}/notes")
    fun getUserNotes(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("users/{userId}/favorites")
    fun favoriteNotes(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("users/{userId}/followers")
    fun followers(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("users/{userId}/followings")
    fun followings(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("tags")
    fun searchTag(@Query("name") name: String): Single<Response<Page<Tag>>>

    @POST("notes/search-by-tag")
    fun searchByTag(@Body conditions: List<List<String>>, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("notes/{noteId}/favorites")
    fun favoriteUsers(@Path("noteId") noteId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("notes/{noteId}/comments")
    fun findComments(@Path("noteId") noteId: Long, @Query("page") page: Int): Single<Response<Page<Comment>>>

    @GET("notes/{noteId}/comments/{commentId}")
    fun findComments(@Path("noteId") noteId: Long, @Path("commentId") commentId: Long, @Query("page") page: Int): Single<Response<Page<Comment>>>



}