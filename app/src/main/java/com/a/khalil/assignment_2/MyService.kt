package com.a.khalil.assignment_2

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.a.khalil.assignment_2.classes.Employee
import com.a.khalil.assignment_2.classes.MyNotificationManager

class MyService : Service() {

    var n = 1
    private var employeeDataBase = EmployeeDataBase(this)
    private var notificationManager: MyNotificationManager? = null

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val employee = intent!!.getParcelableExtra<Employee>("employee")
        val password = employee!!.password
        val employee2 = employeeDataBase.getEmployeeData(employee.username)
        if (employee2 != null && employee2.password == password) {
            notificationManager = MyNotificationManager(this)
            val i = Intent(this, MyService::class.java)

            Handler().postDelayed({
                notificationManager!!.showSmallNotification(
                    n,
                    "Login successfully",
                    "Well done! You can always log in",
                    i
                )
            }, 15000)
            n++
        } else {
            notificationManager = MyNotificationManager(this)
            val i = Intent(this, MyService::class.java)

            Handler().postDelayed(
                {
                    notificationManager!!.showSmallNotification(
                        n,
                        "Login Failed",
                        "Sorry, you have to try to log in again",
                        i
                    )
                },
                15000
            )
            n++
        }

        return super.onStartCommand(intent, flags, startId)
    }

}