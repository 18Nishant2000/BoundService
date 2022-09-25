package com.nishant.app2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class DBService : Service() {

    private val TAG = "DBService"
    private var serviceBinder = DBServiceBinder()

    inner class DBServiceBinder : Binder() {
        fun getDBService() = this@DBService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return serviceBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    fun insert(callback: AsyncCallback) {
        DBHelper(this).insert("Nishant", "123456789", callback)
    }

    fun update(callback: AsyncCallback) {
        DBHelper(this).update("Nishant", "8506045489", callback)
    }

    fun delete(callback: AsyncCallback) {
        DBHelper(this).delete("Nishant", callback)
    }

    fun create() {
        Toast.makeText(applicationContext, "Create() called", Toast.LENGTH_SHORT).show()
    }

    fun read() {
        Toast.makeText(applicationContext, "Read() called", Toast.LENGTH_SHORT).show()
    }
}