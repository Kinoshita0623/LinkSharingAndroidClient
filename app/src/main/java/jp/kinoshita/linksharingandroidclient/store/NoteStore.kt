package jp.kinoshita.linksharingandroidclient.store

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import jp.kinoshita.linksharingandroidclient.model.AuthRequiredLinkSharingAPI
import jp.kinoshita.linksharingandroidclient.model.LinkSharingAPI
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import java.util.concurrent.ConcurrentHashMap


class NoteStore(
    val api: AuthRequiredLinkSharingAPI
) {

    private val notes = ConcurrentHashMap<Long, Note>()

    private val dispatcher = PublishSubject.create<NoteDispatcherType>()

    private val eventSubject = PublishSubject.create<NoteEvent>()
    val noteEvent: Observable<NoteEvent> = eventSubject

    private var disposable: Disposable? = null

    private val compositeDisposable = CompositeDisposable()


    fun subscribe(){
        disposable = dispatcher.subscribe{
            it?.let{
                when(it){
                    is NoteDispatcherType.Add ->{
                        notes[it.note.id] = it.note
                        eventSubject.onNext(NoteEvent.Added(it.note))
                    }
                    is NoteDispatcherType.Delete ->{
                        notes.remove(it.noteId)
                        eventSubject.onNext(NoteEvent.Deleted(it.noteId))
                        return@let
                    }
                    is NoteDispatcherType.Favorite ->{
                        val updated = it.note.copy(isFavorite = true)
                        notes[it.note.id] = updated
                        eventSubject.onNext(NoteEvent.Added(it.note))
                    }
                    is NoteDispatcherType.UnFavorited ->{
                        val updated = it.note.copy(isFavorite = false)
                        notes[it.note.id] = updated
                        eventSubject.onNext(NoteEvent.Added(it.note))
                    }
                }

            }

        }
    }

    fun dispose(){
        disposable?.dispose()
        compositeDisposable.dispose()
    }




    fun actionDelete(noteId: Long){
        val disposable = api.deleteNote(noteId).subscribe { t1, t2 ->
            if(t1.code() in 200 until 300){
                dispatcher.onNext(NoteDispatcherType.Delete(noteId))
            }
        }
        compositeDisposable.add(disposable)

    }

    fun toggleFavorite(note: Note){
        val disposable = if(note.isFavorite){
            api.unFavorite(note.id).subscribe { t1, t2 ->
                if(t1.code() in 200 until 300 && t1.body()?.user != null){
                    dispatcher.onNext(NoteDispatcherType.UnFavorited(note, t1.body()?.user!!))
                }
            }
        }else{
            api.favorite(note.id).subscribe { t1, t2 ->
                if(t1.code() in 200 until 300){
                    dispatcher.onNext(NoteDispatcherType.Favorite(note, t1.body()!!.user))
                }
            }

        }
        compositeDisposable.add(disposable)

    }

    fun find(noteId: Long){
        val disposable = api.getNote(noteId).subscribe { t1, t2 ->
            if(t1.code() in 200 until 300){
                dispatcher.onNext(NoteDispatcherType.Add(t1.body()!!))
            }else if(t1.code() == 404){
                dispatcher.onNext(NoteDispatcherType.Delete(noteId))
            }
        }
        compositeDisposable.add(disposable)
    }


    fun get(noteId: Long): Note?{
        return notes[noteId]
    }



}