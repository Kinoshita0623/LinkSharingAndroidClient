package jp.kinoshita.linksharingandroidclient.model.notes

import jp.kinoshita.linksharingandroidclient.model.APIFactory
import jp.kinoshita.linksharingandroidclient.model.Account
import jp.kinoshita.linksharingandroidclient.model.API
import jp.kinoshita.linksharingandroidclient.model.login.Login
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteTest{


    lateinit var api: API

    @Before
    fun setUpAccount(){
        val api = APIFactory(null).create()
        val login = api.login(
            Login("panta@test.jp", "testtest", "Test")
        ).blockingGet()?.body()
        val account = Account(
            token = login!!.token,
            userId = 114514,
        )

        this.api = APIFactory(account).create()

    }

    @Test
    fun testTimeline()
    {
        val timeline = this.api.timeline(0).blockingGet()?.body()
        assertNotNull(timeline)
        assertTrue(timeline?.data?.isNotEmpty() == true)
    }


}