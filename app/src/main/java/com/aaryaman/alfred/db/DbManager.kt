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
    val dbVersion= 6

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
    val colDate = "Date"

    val sqlCreateTable= "Create Table if not exists $dbTable (" +
            "$colID INTEGER PRIMARY KEY, "+
            "$colName TEXT, " +
            "$colTime TEXT, " +
            "$colDate TEXT, " +
            "$colSnooze TEXT, " +
            "$colStartDate Text, " +
            "$colCompleted Integer, " +
            "$colMissed Integer, " +
            "$colToday Integer);"


//special task table
    val dbSTable = "SpecialTasks"
    val colSID= "ID"
    val colSName= "Name"
    val colSDate = "Date"
    val colSTime= "Time"
    val colSSnooze= "Snooze"
    val colSToday= "Today"
    val colSRepeat= "Repeat"

    val sqlCreateSpecialTable= "Create Table if not exists $dbSTable (" +
            "$colSID INTEGER PRIMARY KEY, " +
            "$colSName TEXT, " +
            "$colSDate TEXT, " +
            "$colSTime TEXT, " +
            "$colSSnooze TEXT, " +
            "$colSRepeat Integer, " +
            "$colSToday Integer);"


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
            db.execSQL(sqlCreateSpecialTable)
            Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//            db!!.execSQL("Drop table if exists $dbTable")
//            db!!.execSQL(sqlCreateSpecialTable)
            db!!.execSQL("Alter table $dbTable add Date Text")
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

    fun UpdateSpecialTaskTick(values: ContentValues, name: String): Int{
        val ID= sqlDB!!.update(dbSTable, values, "Name = ?", arrayOf(name))
        return ID
    }

    fun QueryToday(): Cursor{
        val qb= SQLiteQueryBuilder()
        qb.tables= dbTable
        return qb.query(sqlDB, null, null, null, null, null, null)
    }

    fun InsertSpecialTask(values: ContentValues): Long{
        val ID= sqlDB!!.insert(dbSTable, null, values)
        return ID
    }

    fun QuerySpecialToday(): Cursor{
        val qb= SQLiteQueryBuilder()
        qb.tables= dbSTable
        return qb.query(sqlDB, null, null, null, null, null, null)
    }
}