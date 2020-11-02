package jp.kinoshita.linksharingandroidclient.ui.tags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import jp.kinoshita.linksharingandroidclient.R
import jp.kinoshita.linksharingandroidclient.model.notes.Tag
import kotlinx.android.synthetic.main.item_tag.view.*

class TagListAdapter : ListAdapter<Tag, TagListAdapter.ViewHolder>(TagCallback){


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val tagView = view.findViewById<Chip>(R.id.tag_view)
        fun onBind(tag: Tag){
            tagView.text = tag.name

        }
    }

    object TagCallback : DiffUtil.ItemCallback<Tag>(){
        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_tag, parent, false)
        return ViewHolder(view)
    }
}