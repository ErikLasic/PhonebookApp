package com.example.phonebookapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lib.PhoneAddress
import com.example.lib.PhoneUserContact
import com.example.lib.Phonebook
import com.example.phonebookapp.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var app: MyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        //val imenik = Phonebook("Ptujski imenik")
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                app.data.add(
                    PhoneUserContact(
                        data?.getStringExtra("phoneNumber").toString(),
                        data?.getStringExtra("nameSurname").toString(),
                        PhoneAddress(
                            data?.getStringExtra("city").toString(),
                            data?.getStringExtra("postcode").toString().trim().toIntOrNull()?: 0,
                            data?.getStringExtra("houseNumber").toString()
                        )
                    )
                )
                app.saveToFile()
            }
        }*/

        binding.AboutButton.setOnClickListener {
            app.data.sort()
            Log.i("Phonebook size: ", app.data.size().toString())
            val intent = Intent(this, AboutActivity::class.java)
            //getContent.launch(intent)
            startActivity(intent)
        }

        binding.AddButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            //getContent.launch(intent)
            startActivity(intent)
        }
        binding.UserButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        binding.ExitButton.setOnClickListener {
            exitProcess(0)
        }

    }
}