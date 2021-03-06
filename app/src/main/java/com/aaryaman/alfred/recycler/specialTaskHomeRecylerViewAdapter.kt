package com.aaryaman.alfred.recycler

import android.content.ContentValues
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.alfred.R
import com.aaryaman.alfred.models.Task
import kotlinx.android.synthetic.main.regular_task_item.view.*
import com.aaryaman.alfred.db.DbManager
import com.aaryaman.alfred.todaysSpecialTaskList


class specialTaskHomeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SpecialTaskListHolder(LayoutInflater.from(parent.context).inflate(R.layout.regular_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SpecialTaskListHolder -> {
                holder.bind(todaysSpecialTaskList!![position])
            }
        }
    }

    override fun getItemCount(): Int {
        return todaysSpecialTaskList!!.size
    }
}


class SpecialTaskListHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val box = itemView.regular_task_name
    val tick= itemView.regular_task_tick


    fun bind(task: Task) {
        box.text= task.name
        if (task.tick =="1"){
            tick.setImageResource(R.drawable.tick1)
            tick.contentDescription= "1"
        }
        else {
            tick.setImageResource(R.drawable.remove_circle_24)
            tick.contentDescription= "0"

        }
    }

    init {
        itemView.regular_task_tick.setOnClickListener {

            if (itemView.regular_task_tick.contentDescription== "0")   {
                itemView.regular_task_tick.setImageResource(R.drawable.tick1)
                itemView.regular_task_tick.contentDescription = "1"
                UpdateTick("1", itemView.regular_task_name.text.toString())
            }
            else{
                itemView.regular_task_tick.setImageResource(R.drawable.remove_circle_24)
                itemView.regular_task_tick.contentDescription = "0"
                UpdateTick("0", itemView.regular_task_name.text.toString())
            }
        }
    }

    fun UpdateTick(newState: String, name: String){
        val dbManager= DbManager(contMainActivity)

        val values= ContentValues()
        values.put("Today", newState)

        val ID= dbManager.UpdateSpecialTaskTick(values, name)

        if (ID>0)
            if (newState=="1")
                Toast.makeText(contMainActivity, " ??? $name", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(contMainActivity, " ??? $name", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(contMainActivity, "Error occurred ", Toast.LENGTH_SHORT).show()

    }
}