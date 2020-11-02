package jp.kinoshita.linksharingandroidclient.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import jp.kinoshita.linksharingandroidclient.model.LinkSharingAPI
import jp.kinoshita.linksharingandroidclient.model.login.Login
import jp.kinoshita.linksharingandroidclient.model.login.Token
import jp.kinoshita.linksharingandroidclient.model.users.User

class LoginViewModel(
    private val api: LinkSharingAPI,
    private val deviceName: String = "LinkSharing4Android"

) : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()



    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading

    private val mCompositeDisposable = CompositeDisposable()

    private val mLoggedInTokenSubject = PublishSubject.create<Token>()
    val loggedInToken: Observable<Token> = mLoggedInTokenSubject



    fun login(){
        val email = this.email.value?: ""
        val password = this.password.value?: ""


        mIsLoading.value = true


        val disposable = api.login(
            Login(email = email, password = password, deviceName = this.deviceName)).subscribeOn(Schedulers.io())
            .subscribe { t1, t2 ->
                val body = t1.body()
                if(body == null|| t2 != null){
                    mLoggedInTokenSubject.onError(t2)

                }else if(t1.code() in 200 until 300){
                    mLoggedInTokenSubject.onNext(body)
                }
                mIsLoading.postValue(false)
            }

        mCompositeDisposable.add(disposable)

    }

    fun dispose(){
        mCompositeDisposable.clear()
    }




}
