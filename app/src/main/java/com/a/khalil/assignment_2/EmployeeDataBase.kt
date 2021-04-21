package com.a.khalil.assignment_2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.a.khalil.assignment_2.classes.Employee
import com.a.khalil.assignment_2.classes.Employee.CREATOR.NAME

class EmployeeDataBase(context: Context) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {



    override fun onCreate(p0: SQLiteDatabase?) {
        Log.e("anas","asdasdasdasda")
        val create = "CREATE TABLE $NAME (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Employee.USERNAME_COLUMN} TEXT NOT NULL," +
                "${Employee.PASSWORD_COLUMN} TEXT NOT NULL," +
                "${Employee.EMAIL_COLUMN} TEXT NOT NULL," +
                "${Employee.PHONENUMBER_COLUMN} LONG NOT NULL );"

        p0!!.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val command = "DROP TABLE IF EXISTS $NAME"
        p0!!.execSQL(command)
        this.onCreate(p0)
    }

    fun insertEmployee(employee: Employee): Boolean? {
        val database = this.writableDatabase
        val cursor = getAllEmployees()
        cursor.moveToFirst()

        if (cursor.moveToNext()) {
            val username = cursor.getString(cursor.getColumnIndex(Employee.USERNAME_COLUMN))
            if (employee.username == username) {
                return null // here when the name is used before..
            }
        }
        val contentValues = ContentValues()
        contentValues.put("username", employee.username)
        contentValues.put("password", employee.password)
        contentValues.put("email", employee.email)
        contentValues.put("phoneNumber", employee.phoneNumber)

        return database.insert(NAME, null, contentValues) > 0
    }

    fun getAllEmployees(): Cursor {
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from $NAME", null)
        return cursor
    }

    fun getEmployeeData(username: String?): Employee? {

        if (username == null) {
            return null
        } else {
            val database = this.readableDatabase

            val cursor = getAllEmployees()
            var employee: Employee? = null
            cursor.moveToFirst()

            while (cursor.moveToNext()) {
                val employeeUsername =
                    cursor.getString(cursor.getColumnIndex(Employee.USERNAME_COLUMN))
                if (username.equals(employeeUsername)) {
                    employee = Employee(
                        username,
                        cursor.getString(cursor.getColumnIndex(Employee.PASSWORD_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(Employee.EMAIL_COLUMN)),
                        cursor.getLong(cursor.getColumnIndex(Employee.PHONENUMBER_COLUMN))
                    )
                }
            }
            cursor.close()
            return employee
        }
    }


    companion object {
        var VERSION = 1
    }
}