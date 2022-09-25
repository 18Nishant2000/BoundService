package com.nishant.app2

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var serviceBound = false
    lateinit var dbService: DBService

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as DBService.DBServiceBinder
            dbService = binder.getDBService()
            serviceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            serviceBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindDBService()

        findViewById<Button>(R.id.create).setOnClickListener {
            dbService.create()
        }

        findViewById<Button>(R.id.delete).setOnClickListener {
            dbService.delete(object : AsyncCallback {
                override fun onSuccess() {
                    Toast.makeText(applicationContext, "Deleted successfully", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailed() {
                    Toast.makeText(applicationContext, "Deleted Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }

        findViewById<Button>(R.id.insert).setOnClickListener {
            dbService.insert(object : AsyncCallback {
                override fun onSuccess() {
                    Toast.makeText(applicationContext, "Inserted successfully", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailed() {
                    Toast.makeText(applicationContext, "Insertion Failed", Toast.LENGTH_SHORT)
                        .show()
                }

            })
        }

        findViewById<Button>(R.id.read).setOnClickListener {
            dbService.read()
        }

        findViewById<Button>(R.id.update).setOnClickListener {
            dbService.update(object : AsyncCallback {
                override fun onSuccess() {
                    Toast.makeText(applicationContext, "Update successfully", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailed() {
                    Toast.makeText(applicationContext, "Update Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
        serviceBound = false
    }

    private fun bindDBService() {
        Intent(this, DBService::class.java).also {
            bindService(it, serviceConnection, BIND_AUTO_CREATE)
        }
    }
}