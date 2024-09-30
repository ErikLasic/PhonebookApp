package com.example.lib

import java.util.*

class PhoneUserContact(var number: String, var username: String, var address: PhoneAddress):Comparable<PhoneUserContact> {
    var id:String = UUID.randomUUID().toString().replace("-", "")

    override fun compareTo(other: PhoneUserContact): Int {
        return username.compareTo(other.username)
    }

    override fun toString(): String {
        return "$id $username $number $address"
    }
}