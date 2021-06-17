//package com.aaryaman.alfred.recycler
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.aaryaman.alfred.MainActivity
//import com.aaryaman.alfred.R
//import com.aaryaman.alfred.models.ListBaseString
//import com.aaryaman.alfred.dateList
//import com.aaryaman.alfred.tickList
//import kotlinx.android.synthetic.main.recycler_view_date.view.*
//import kotlinx.android.synthetic.main.recycler_view_tick.view.*
//
//class tickRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return TickListHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_tick, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when(holder){
//            is TickListHolder -> {
//                holder.bind(tickList[position])
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return tickList.size
//    }
//}
//
//
//class TickListHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {
//
//    val box = itemView.checkBox
//
//
//    fun bind(str: ListBaseString) {
//        box.isChecked = str.str == "1"
//
//    }
//
//}
//
////
////if( str.str == "1")
////box.isChecked = true
////else
////box.isChecked = false