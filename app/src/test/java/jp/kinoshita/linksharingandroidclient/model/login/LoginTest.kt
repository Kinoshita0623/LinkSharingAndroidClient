package jp.kinoshita.linksharingandroidclient.model.login

import jp.kinoshita.linksharingandroidclient.model.APIFactory
import org.junit.Assert.*
import org.junit.Test


class LoginTest{


    @Test
    fun testLogin(){
        val api = APIFactory(null).create()
        val get = api.login(Login(
            email = "panta@test.jp",
            password = "testtest",
            deviceName = "Test"
        )).blockingGet()
        assertNotNull(get.body())

        assertNotNull(get.body()?.token)

    }


}