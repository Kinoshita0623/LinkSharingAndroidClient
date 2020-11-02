package jp.kinoshita.linksharingandroidclient.ui.notes

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.kinoshita.linksharingandroidclient.databinding.ItemNoteBinding
import jp.kinoshita.linksharingandroidclient.model.notes.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.ViewHolder>(ItemDiffUtil){

    class ViewHolder(
        private val itemNoteBinding: ItemNoteBinding,
        private val lifecycleOwner: LifecycleOwner,
    ) : RecyclerView.ViewHolder(itemNoteBinding.root){
        fun onBind(note: Note){
            itemNoteBinding.lifecycleOwner = lifecycleOwner
            itemNoteBinding.note = note

            itemNoteBinding.executePendingBindings()
        }
    }

    object ItemDiffUtil : DiffUtil.ItemCallback<Note>(){
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }
}