package jp.kinoshita.linksharingandroidclient.model.register

import jp.kinoshita.linksharingandroidclient.model.APIFactory
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CreateUserTest{

    @Test
    fun testCreateUser(){
        val name = UUID.randomUUID().toString().substring(7).replace("-", "")
        val password = UUID.randomUUID().toString().substring(10).replace("-", "")
        val deviceName = "DeviceTest"
        val email = "$name@hogehoge.jp"

        val createUser = CreateUser(
            userName = name,
            email = email,
            password = password,
            passwordConfirmation = password,
            deviceName = deviceName
        )

        val api = APIFactory(null).create()
        val res = api.register(createUser).blockingGet()

        val body = res.body()
        assertNotNull(body)
        assertEquals(body!!.user.userName, name)





    }
}