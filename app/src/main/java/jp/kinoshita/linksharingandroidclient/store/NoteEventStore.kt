package jp.kinoshita.linksharingandroidclient.store

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.util.FIFOMap
import java.util.*

class NoteEventStore {

    sealed class Event(open val eventAt: Date, open val noteId: Long){
        data class Add(val note: Note, override val eventAt: Date = Date()) : Event(eventAt, note.id)
        data class Delete(override val noteId: Long, override val eventAt: Date = Date()): Event(eventAt, noteId)
    }


    private val eventDispatcher = PublishSubject.create<Event>()
    private val eventStream = PublishSubject.create<Event>()
    val eventObserver: Observable<Event> = eventStream

    private val eventQueue = FIFOMap<Long, Event>(1000)

    private val compositeDisposable = CompositeDisposable()

    fun subscribe(){
        compositeDisposable.dispose()

        val disposable = eventDispatcher.subscribe {
            synchronized(eventQueue){
                eventQueue[it.noteId] = it
            }
        }
        compositeDisposable.add(disposable)
    }

    fun dispose(){
        compositeDisposable.clear()
    }

    fun getEventBySinceDate(date: Date): List<Event>{
        return eventQueue.values.filter{
            it.eventAt >= date
        }
    }


    fun add(note: Note){
        eventDispatcher.onNext(Event.Add(note))
    }

    fun delete(note: Note){
        eventDispatcher.onNext(Event.Delete(note.id))
    }

    fun delete(noteId: Long){
        eventDispatcher.onNext(Event.Delete(noteId))
    }

    fun get(noteId: Long): Event?{
        synchronized(eventQueue){
            return eventQueue[noteId]
        }
    }
}