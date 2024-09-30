package com.example.phonebookapp

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonebookapp.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.phoneUserContacts.layoutManager = LinearLayoutManager(this)
        val adapter = ContactsAdapter(app.data)

        val editData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            adapter.notifyDataSetChanged()
        }
        adapter.onClickObject = object:ContactsAdapter.MyOnClick {
            override fun onClick(p0: View?, position:Int) {
                val intent = Intent(this@UserActivity, AddActivity::class.java)
                intent.putExtra("SELECTED_ID", app.data.phoneContacts[position].id)
                intent.putExtra("NUMBER", app.data.phoneContacts[position].number)
                intent.putExtra("USERNAME", app.data.phoneContacts[position].username)
                intent.putExtra("CITY", app.data.phoneContacts[position].address.city)
                intent.putExtra("POSTCODE", app.data.phoneContacts[position].address.postcode.toString())
                intent.putExtra("HOUSE_NUMBER", app.data.phoneContacts[position].address.number)
                setResult(RESULT_OK, intent)
                editData.launch(intent)
            }

            override fun onLongClick(p0: View?, position: Int) {
                val builder = AlertDialog.Builder(this@UserActivity) //access context from inner class
                //set title for alert dialog
                builder.setTitle("Delete")
                builder.setMessage("User ${app.data.phoneContacts[position]}")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes"){dialogInterface, which -> //performing positive action
                    Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
                    app.data.phoneContacts.removeAt(position)
                    adapter.notifyDataSetChanged()
                    app.saveToFile()
                }
                builder.setNeutralButton("Cancel"){dialogInterface , which -> //performing cancel action
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                }
                builder.setNegativeButton("No"){dialogInterface, which -> //performing negative action
                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

            }
        }
        binding.phoneUserContacts.adapter = adapter
        //adapter.notifyDataSetChanged();
        binding.HomeButton.setOnClickListener {
            finish()
        }
    }

}
