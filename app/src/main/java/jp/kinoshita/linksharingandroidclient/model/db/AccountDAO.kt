package jp.kinoshita.linksharingandroidclient.model.db

import androidx.room.*
import jp.kinoshita.linksharingandroidclient.model.Account

interface AccountDAO {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    suspend fun insert(account: Account): Long

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("select * from account where id=:accountId")
    suspend fun findByAccountId(accountId: Long): Account?
}