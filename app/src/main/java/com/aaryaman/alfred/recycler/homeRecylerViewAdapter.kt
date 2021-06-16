package com.aaryaman.alfred.recycler

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.alfred.R
import com.aaryaman.alfred.models.Task
import com.aaryaman.alfred.todaysTaskList
import kotlinx.android.synthetic.main.regular_task_item.view.*
import com.aaryaman.alfred.db.DbManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

lateinit var cont: Context

class homeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RegularTaskListHolder(LayoutInflater.from(parent.context).inflate(R.layout.regular_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RegularTaskListHolder -> {
                holder.bind(todaysTaskList!![position])
            }
        }
    }

    override fun getItemCount(): Int {
        return todaysTaskList!!.size
    }
}


class RegularTaskListHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val box = itemView.regular_task_name
    val time = itemView.regular_task_time
    val tick= itemView.regular_task_tick


    fun bind(task: Task) {
        box.text= task.name
        time.text = task.time
        if (task.tick =="1" ){
            tick.setImageResource(R.drawable.tick1)
            tick.contentDescription= "1"
        }
        else {
            tick.setImageResource(R.drawable.remove_circle_24)
            tick.contentDescription= "0"
            if( if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) > task.time
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    })
                box.background = Color.parseColor("#4BFA0000").toDrawable()
        }
    }

    init {
        itemView.regular_task_tick.setOnClickListener {

            if (itemView.regular_task_tick.contentDescription== "0")   {
                itemView.regular_task_tick.setImageResource(R.drawable.tick1)
                itemView.regular_task_tick.contentDescription = "1"
                UpdateTick("1", itemView.regular_task_name.text.toString())
                box.background = Color.parseColor("#4BF4F1F1").toDrawable()
            }
            else{
                itemView.regular_task_tick.setImageResource(R.drawable.remove_circle_24)
                itemView.regular_task_tick.contentDescription = "0"
                UpdateTick("0", itemView.regular_task_name.text.toString())
                if( if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) >  itemView.regular_task_time.text.toString()
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    })
                    box.background = Color.parseColor("#4BFA0000").toDrawable()
            }
        }
    }

    fun UpdateTick(newState: String, name: String){
        val dbManager= DbManager(cont)

        val values= ContentValues()
        values.put("Today", newState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            values.put("Date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE))
        }

        val ID= dbManager.UpdateRegularTaskTick(values, name)

        if (ID>0)
            if (newState=="1"){
                Toast.makeText(cont, " ✅ $name", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(cont, " ❌ $name", Toast.LENGTH_SHORT).show()
            }
        else
            Toast.makeText(cont, "Error occurred ", Toast.LENGTH_SHORT).show()

    }
}