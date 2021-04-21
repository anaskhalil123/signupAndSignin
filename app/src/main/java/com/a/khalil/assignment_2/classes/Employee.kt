package com.a.khalil.assignment_2.classes

import android.os.Parcel
import android.os.Parcelable

data class Employee(
    val username: String?,
    val password: String?,
    val email: String?,
    val phoneNumber: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(email)
        parcel.writeLong(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        val NAME = "Employee"
        val USERNAME_COLUMN = "username"
        val PASSWORD_COLUMN = "password"
        val EMAIL_COLUMN = "email"
        val PHONENUMBER_COLUMN = "phoneNumber"

        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }

}