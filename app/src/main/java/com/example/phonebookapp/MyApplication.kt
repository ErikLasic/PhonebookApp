package com.example.phonebookapp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FileUtils.writeStringToFile
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lib.PhoneAddress
import com.example.lib.PhoneUserContact
import com.example.lib.Phonebook
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.*
import io.github.serpro69.kfaker.Faker

const val MY_FILE_NAME = "mydata.json"
const val MY_SP_FILE_NAME = "myshared.data"

class MyApplication: Application() {
    lateinit var data:Phonebook
    private lateinit var gson: Gson
    private lateinit var file: File
    lateinit var sharedPref: SharedPreferences
    val faker = Faker()

    override fun onCreate() {
        super.onCreate()
        gson = Gson()
        file = File(filesDir, MY_FILE_NAME)
        initData()
        initShared()
        if (!containsID()) {
            saveID(UUID.randomUUID().toString().replace("-", ""))
        }
    }
    fun initShared() {
        sharedPref = getSharedPreferences( MY_SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveID(id:String) {
        with (sharedPref.edit()) {
            putString("ID", id)
            apply()
        }
    }
    fun containsID():Boolean {
        return sharedPref.contains("ID")
    }
    fun getID(): String? {
        return sharedPref.getString("ID","DefaultNoData")
    }

    fun saveToFile() {
        try {
            FileUtils.writeStringToFile(file, gson.toJson(data))
        } catch (e: IOException) {
            println("exception")
        }
    }

    private fun initData() {
        data = try { //www
            gson.fromJson(FileUtils.readFileToString(file), Phonebook::class.java)
        } catch (e: IOException) {
            Phonebook("Imenik")
        }
        if (data.size() < 100) {
            for (i in 1..100) {
                data.add(PhoneUserContact(faker.phoneNumber.phoneNumber(), faker.name.name(),
                    PhoneAddress(faker.address.city(), (1000..3000).random(), faker.address.buildingNumber())
                )
                )
                saveToFile()
            }
        }
    }
}