package jp.kinoshita.linksharingandroidclient.application.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import jp.kinoshita.linksharingandroidclient.model.AuthenticationException
import jp.kinoshita.linksharingandroidclient.model.Page
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import retrofit2.Response

class TimelineViewModel(
    val adapter: Adapter
) : ViewModel(){

    interface Adapter{
        fun onInitialLoad(): Single<Response<Page<Note>>>
        fun onLoadNext(pageNumber: Int): Single<Response<Page<Note>>>
    }

    val notesLiveData = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = notesLiveData

    val mIsLoadingLiveData = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = mIsLoadingLiveData

    private val mExceptionSubject = PublishSubject.create<Exception>()
    val exceptions: Observable<Exception> = mExceptionSubject

    private var mNotes: List<Note> = emptyList()
    private var mIsLoading: Boolean = false
        set(value) {
            mIsLoadingLiveData.postValue(value)
            field = value
        }

    private var mCurrentPageNumber = 0
    private var mNextPageNumber = 0

    private val mCompositeDisposable = CompositeDisposable()

    fun loadInitial(){
        if(isLockedAndLock()){
            return
        }
        val disposable = adapter.onInitialLoad().subscribeOn(Schedulers.io()).subscribe { t1, t2 ->
            val body = t1.body()

            if(t1 == null || t2 != null || body == null){
                if(t2 is Exception){
                    onError(t2)
                }
            }else if(t1.code() in 200 until 300){
                mCurrentPageNumber = body.currentPage
                mNextPageNumber = mCurrentPageNumber + 1

                mNotes = body.data
            }else if(t1.code() == 403){
                onError(AuthenticationException())
            }

            publishNotes()

        }
        mCompositeDisposable.add(disposable)

    }

    fun loadNext(){
        if(isLockedAndLock()){
            return
        }

        val disposable = adapter.onLoadNext(mNextPageNumber)
            .subscribeOn(Schedulers.io())
            .subscribe { res, error ->
                val body = res.body()
                if(res == null || body == null || error != null){
                    // on error
                    if(error is Exception){
                        onError(error)
                    }
                }else if(res.code() in 200 until 300){
                    mCurrentPageNumber = body.currentPage
                    mNextPageNumber = mCurrentPageNumber + 1

                    this.mNotes = mNotes.toArrayList()
                }else if(res.code() == 403){
                    onError(AuthenticationException())
                }
                publishNotes()
            }
        mCompositeDisposable.add(disposable)

    }


    fun dispose(){

    }


    private fun onError(e: Throwable){
        if(e is Exception){
            mExceptionSubject.onNext(e)
        }else{
            throw e
        }
    }
    private fun isLockedAndLock(): Boolean{
        synchronized(mIsLoading){
            if(mIsLoading){
                return true
            }
            mIsLoading = true
        }
        return false
    }

    private fun publishNotes(){
        notesLiveData.postValue(mNotes)
        synchronized(mIsLoading){
            mIsLoading = false
        }
    }


    private fun List<Note>.toArrayList(): ArrayList<Note>{
        return ArrayList(this)
    }

}