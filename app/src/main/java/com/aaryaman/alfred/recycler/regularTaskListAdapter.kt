package com.aaryaman.alfred.recycler

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.alfred.*
import com.aaryaman.alfred.models.Task
import kotlinx.android.synthetic.main.regular_task_item.view.*
import kotlinx.android.synthetic.main.regular_task_list_item.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class regularTaskListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RegularTaskListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.regular_task_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegularTaskListViewHolder -> {
                holder.bind(todaysTaskList!![position])
            }
        }
    }

    override fun getItemCount(): Int {
        return todaysTaskList!!.size
    }

}

class RegularTaskListViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val box = itemView.task_name
    val time = itemView.task_time

    init {
        itemView.task_delete.setOnClickListener {
            showDialog(cont2, 1)

        }
    }

    fun bind(task: Task) {
        box.text= task.name
        time.text = task.time
        Log.e("TAG", todaysTaskList.size.toString())
    }



}
