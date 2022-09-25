package com.nishant.app2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val DB_NAME = "App2.db"
private const val DB_VERSION = 1

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("create Table Demo1(name TEXT, cno TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("drop Table if exists Demo1")
    }

    fun insert(name: String, cno: String, callback: AsyncCallback) {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("cno", cno)
        val result = db.insert("Demo1", null, contentValues)
        if (result == -1L) callback.onFailed() else callback.onSuccess()
    }

    fun update(name: String, cno: String, callback: AsyncCallback) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("cno", cno)
        val result = db.update("Demo1", contentValues, "name=?", arrayOf(name))
        if (result == -1) callback.onFailed() else callback.onSuccess()
    }

    fun delete(name: String, callback: AsyncCallback) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        val result = db.delete("Demo1", "name=?", arrayOf(name))
        if (result == -1) callback.onFailed() else callback.onSuccess()
    }

}