package com.example.lib

class PhoneAddress(var city: String, var postcode: Int, var number: String) {
    override fun toString(): String {
        return "$city $postcode $number"
    }
}