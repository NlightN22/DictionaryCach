package space.active.dictionaryapi.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.active.dictionaryapi.R

class AdapterRecyclerView (

        ): RecyclerView.Adapter<AdapterRecyclerView.ListHolder>() {

    var listItems = emptyList<String>()

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.textViewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.content.text = listItems[position]
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setList(list: List<String>) {
        listItems = list
        notifyDataSetChanged()
    }
}