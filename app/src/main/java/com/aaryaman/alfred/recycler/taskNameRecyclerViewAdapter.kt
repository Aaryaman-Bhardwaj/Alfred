package com.aaryaman.alfred.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.alfred.R
import com.aaryaman.alfred.models.ListBaseString
import com.aaryaman.alfred.nameList
import kotlinx.android.synthetic.main.recycler_view_name.view.*

class taskNameRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskNameListHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_name, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TaskNameListHolder -> {
                holder.bind(nameList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}


class TaskNameListHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val box = itemView.task_name_view


    fun bind(str: ListBaseString) {
        box.text= str.str
    }

}