package com.a.khalil.assignment_2.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a.khalil.assignment_2.MyService
import com.a.khalil.assignment_2.R
import com.a.khalil.assignment_2.classes.Employee
import kotlinx.android.synthetic.main.activity_sign_in.*

class Sign_In : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSign_in.setOnClickListener {
            val username = textUsername.text.toString()
            val password = textPassword.text.toString()

            val i = Intent(this, MyService::class.java)

            val e = Employee(username, password, "", 0)

            i.putExtra("employee", e)
            startService(i)
        }

// TODO  هنا أنا بأفضل أني أدخل المعلومات اللي بدخلها المستخدم، في امبلويي جديد ، وأنا أكمل باقي المدخلات بقيم افتراضية ولو خاطئة، ولما أنقلها بآخذ القيم الصحيح تبعت الاسم وكلمة المرور وبأكمل الشغل فيهم وما بستخدم
//          المتغير اللي أستخدمت في النقل، بالتالي بيكون أستخدام هادا المستغير فقط في النقل إن شاء الله


        textView2.setOnClickListener {
            val i = Intent(this, Sign_up::class.java)
            startActivity(i)
        }

    }
}