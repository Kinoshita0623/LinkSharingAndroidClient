package jp.kinoshita.linksharingandroidclient.model

import java.lang.IllegalStateException

class AuthenticationException(msg: String = "認証エラー") : IllegalStateException(msg)