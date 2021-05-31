package com.aaryaman.alfred.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DbManager {

    val dbName = "Tasks"
//regular task table
    val dbTable= "RegularTasks"
    val colID= "ID"
    val colName= "Name"
    val colTime= "Time"
    val colSnooze= "Snooze"
    val colStartDate= "StartDate"
    val colCompleted= "Completed"
    val colMissed= "Missed"
    val colToday= "Today"
    val dbVersion= 4

    val sqlCreateTable= "Create Table if not exists $dbTable (" +
            "$colID INTEGER PRIMARY KEY, " +
            "$colName TEXT, " +
            "$colTime TEXT, " +
            "$colSnooze TEXT, " +
            "$colStartDate Text, " +
            "$colCompleted Integer, " +
            "$colMissed Integer, " +
            "$colToday Integer);"


//special task table

    var sqlDB : SQLiteDatabase? = null



    constructor(context: Context){
        var db= DatabaseHelperTasks(context)
        sqlDB= db.writableDatabase
    }

    inner class DatabaseHelperTasks: SQLiteOpenHelper {

        var context: Context? = null

        constructor(context: Context):super(context,dbName, null, dbVersion){
            this.context= context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table if exists $dbTable")
            onCreate(sqlDB)
        }

    }

    fun InsertRegularTask(values: ContentValues): Long{
        val ID= sqlDB!!.insert(dbTable, "", values)
        return ID
    }
    fun UpdateRegularTaskTick(values: ContentValues, name: String): Int{
        val ID= sqlDB!!.update(dbTable, values, "Name = ?", arrayOf(name))
        return ID
    }

    fun QueryToday(): Cursor{ //projection: Array<String>, selection: String, selectionArgs: Array<String>, SortOrder: String): Cursor{
        val qb= SQLiteQueryBuilder()
        qb.tables= dbTable
        return qb.query(sqlDB, null, null, null, null, null, null)
    }
}