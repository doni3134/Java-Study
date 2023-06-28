package com.gd.myservice

import android.app.Notification.Action
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var action = intent?.action
        Log.d("StartedService","action = $action")
        return super.onStartCommand(intent, flags, startId)
    }
    companion object {
        val Action_START = "com.gd.myservice.myservice.START"
        val Action_RUN = "com.gd.myservice.myservice.RUN"
        val Action_STOP = "com.gd.myservice.myservice.STOP"
    }
    override fun onDestory(){
        Log.d("Service","서비스가 종료되었습니다.")
        super.onDestroy()
    }


}