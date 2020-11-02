package jp.kinoshita.linksharingandroidclient.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import jp.kinoshita.linksharingandroidclient.R
import jp.kinoshita.linksharingandroidclient.databinding.ItemNoteBinding
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.ui.tags.TagListAdapter

class NoteListAdapter(
    val lifecycleOwner: LifecycleOwner
) : ListAdapter<Note, NoteListAdapter.ViewHolder>(ItemDiffUtil){

    class ViewHolder(
        private val itemNoteBinding: ItemNoteBinding,
        private val lifecycleOwner: LifecycleOwner,
    ) : RecyclerView.ViewHolder(itemNoteBinding.root){
        fun onBind(note: Note){
            itemNoteBinding.lifecycleOwner = lifecycleOwner
            itemNoteBinding.note = note

            val tagLayoutManager = createLayoutManager()
            itemNoteBinding.tagsView.layoutManager = tagLayoutManager
            val adapter = TagListAdapter()
            adapter.submitList(note.tags)
            itemNoteBinding.tagsView.adapter = adapter
            itemNoteBinding.executePendingBindings()
        }

        private fun createLayoutManager(): RecyclerView.LayoutManager{

            val flexBoxLayoutManager = FlexboxLayoutManager(itemNoteBinding.root.context)
            flexBoxLayoutManager.flexDirection = FlexDirection.ROW
            flexBoxLayoutManager.flexWrap = FlexWrap.WRAP
            flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START
            flexBoxLayoutManager.alignItems = AlignItems.STRETCH
            return flexBoxLayoutManager
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
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNoteBinding>(inflater, R.layout.item_note, parent, false)
        return ViewHolder(binding, lifecycleOwner)
    }
}