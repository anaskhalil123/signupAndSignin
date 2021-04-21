package com.a.khalil.assignment_2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a.khalil.assignment_2.EmployeeDataBase
import com.a.khalil.assignment_2.R
import com.a.khalil.assignment_2.classes.Employee
import kotlinx.android.synthetic.main.activity_sign_in.textPassword
import kotlinx.android.synthetic.main.activity_sign_in.textUsername
import kotlinx.android.synthetic.main.activity_sign_up.*

@Suppress("SENSELESS_COMPARISON")
class Sign_up : AppCompatActivity() {

    lateinit var employeeDataBase: EmployeeDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        employeeDataBase = EmployeeDataBase(this)

        btnSign_up.setOnClickListener {
            val employee = Employee(
                textUsername.text.toString(),
                textPassword.text.toString(),
                textEmail.text.toString(),
                textPhoneNumber.text.toString().toLong()
            )

            if (textUsername.text.toString() != null && (textPassword.text.toString() != null) &&
                textEmail.text.toString() != null && textPhoneNumber.text.toString()
                    .toLong() != null
            ) {
                val b = employeeDataBase.insertEmployee(employee)

                if (b != null && b == true) {
                    Toast.makeText(
                        this,
                        "the account created, you can sign in now",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this, Sign_In::class.java))
                } else if (b != null && b == false) {
                    Toast.makeText(
                        this,
                        "Create failed!",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (b == null) {
                    Toast.makeText(
                        this,
                        "This name used previously",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }
}