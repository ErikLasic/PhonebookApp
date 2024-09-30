package com.example.phonebookapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.Phonebook
import com.squareup.picasso.Picasso

class ContactsAdapter(private val data:Phonebook) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    lateinit var onClickObject:MyOnClick

    interface MyOnClick {
        fun onClick(p0: View?, position:Int)
        fun onLongClick(p0: View?, position:Int)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val name: TextView = itemView.findViewById(R.id.uName)
        val number: TextView = itemView.findViewById(R.id.pNumber)
        val line: CardView = itemView.findViewById(R.id.cvLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacts = data.phoneContacts[position]

        Picasso.get().load("https://icons.iconarchive.com/icons/igh0zt/ios7-style-metro-ui/128/MetroUI-Other-Phone-icon.png").into(holder.imageView);

        holder.name.text = contacts.username
        holder.number.text = contacts.number

        holder.line.setOnClickListener { p0 ->
            onClickObject.onClick(p0, holder.bindingAdapterPosition) //Action from Activity
        }
        holder.line.setOnLongClickListener { p0 ->
            onClickObject.onLongClick(p0, holder.bindingAdapterPosition)
            true
        }

    }
    override fun getItemCount(): Int {
            return data.size()
        }

}