package com.example.phonebookapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.PhoneAddress
import com.example.lib.PhoneUserContact
import com.example.phonebookapp.databinding.ActivityAddBinding
import faker.com.ibm.icu.text.UTF16.newString


class AddActivity : AppCompatActivity() {
    private lateinit var app: MyApplication
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        setContentView(R.layout.activity_main)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.extras?.getString("SELECTED_ID") != null) {
            binding.PhoneNumberInput.setText(intent.extras?.getString("NUMBER"))
            binding.NameSurnameInput.setText(intent.extras?.getString("USERNAME"))
            binding.CityInput.setText(intent.extras?.getString("CITY"))
            binding.PostcodeInput.setText(intent.extras?.getString("POSTCODE"))
            binding.HouseNumberInput.setText(intent.extras?.getString("HOUSE_NUMBER"))
        }

        binding.ExitButton.setOnClickListener {
            finish()
        }
        binding.AddButton.setOnClickListener {
            if (intent.hasExtra("SELECTED_ID")) {
                val id = intent.getStringExtra("SELECTED_ID")
                for (contact in app.data.phoneContacts) {
                    if (id == contact.id) {
                        contact.number = binding.PhoneNumberInput.text.toString()
                        contact.username = binding.NameSurnameInput.text.toString()
                        contact.address.city = binding.CityInput.text.toString()
                        contact.address.postcode = binding.PostcodeInput.text.toString().trim().toIntOrNull()?:0
                        contact.address.number = binding.HouseNumberInput.text.toString()
                        break
                    }
                }
            } else {
                app.data.add(PhoneUserContact(
                    binding.PhoneNumberInput.text.toString(), binding.NameSurnameInput.text.toString(),
                    PhoneAddress(binding.CityInput.text.toString(), binding.PostcodeInput.text.toString().trim().toIntOrNull()?: 0, binding.HouseNumberInput.text.toString())
                ))
            }
            app.saveToFile()
            binding.PhoneNumberInput.text?.clear()
            binding.NameSurnameInput.text?.clear()
            binding.CityInput.text?.clear()
            binding.PostcodeInput.text?.clear()
            binding.HouseNumberInput.text?.clear()
            finish()
            //add()
        }

        binding.AboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        binding.HomeButton.setOnClickListener {
            finish()
        }
    }
    /*private fun add() {
        val data = Intent()
        data.putExtra("phoneNumber", binding.PhoneNumberInput.text.toString())
        data.putExtra("nameSurname", binding.NameSurnameInput.text.toString())
        data.putExtra("city", binding.CityInput.text.toString())
        data.putExtra("postcode", binding.PostcodeInput.text.toString())
        data.putExtra("houseNumber", binding.HouseNumberInput.text.toString())
        setResult(RESULT_OK, data)
        finish()
    }*/
}