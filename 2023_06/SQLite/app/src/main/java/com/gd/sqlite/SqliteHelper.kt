package com.gd.sqlite

import android.database.sqlite.SQLiteDatabase

class SqliteHelper {context: Context, name: String, version: Int):SQLiteOpenHelper(context, name, null, version){
    override fun onCreate(db:SQLiteDatabase?){
        val create = "create table memo ("+" no integer primary key, " + " content text, " + " datetime integer" + ")"
        db?.execSQL(create)
    }
    override fun onUpgrade(db:SQLiteDatabase?, oldVersion: Int, newVersion: Int){

    }
}
}