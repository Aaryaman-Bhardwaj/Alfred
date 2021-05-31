package com.aaryaman.alfred.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.alfred.R
import com.aaryaman.alfred.models.ListBaseString
import com.aaryaman.alfred.dateList
import kotlinx.android.synthetic.main.recycler_view_date.view.*

class dateRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateListHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_date, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DateListHolder -> {
                holder.bind(dateList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}


class DateListHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val box = itemView.date_view


    fun bind(str: ListBaseString) {
        box.text= str.str
    }

}