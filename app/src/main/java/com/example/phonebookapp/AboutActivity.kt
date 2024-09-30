package com.example.phonebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phonebookapp.databinding.ActivityAboutBinding
import kotlin.system.exitProcess

class AboutActivity : AppCompatActivity() {
    private lateinit var app: MyApplication
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        setContentView(R.layout.activity_about)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.AddButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        binding.HomeButton.setOnClickListener {
            finish()
        }
        binding.ExitButton.setOnClickListener {
            finish()
        }
        binding.appID.text=app.getID()
    }
}