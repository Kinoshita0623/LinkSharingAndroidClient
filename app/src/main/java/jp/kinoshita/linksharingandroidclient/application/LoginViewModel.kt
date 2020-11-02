package jp.kinoshita.linksharingandroidclient.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.kinoshita.linksharingandroidclient.model.LinkSharingAPI

class LoginViewModel(
    val api: LinkSharingAPI
) : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()


    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading



    fun login(){

    }


}
