package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemRv = findViewById<RecyclerView>(R.id.itemRv)
        val nameEt = findViewById<EditText>(R.id.nameEt)
        val priceEt = findViewById<EditText>(R.id.priceEt)
        val urlEt = findViewById<EditText>(R.id.urlEt)

        val adapter = ItemAdapter(items, this)
        itemRv.adapter = adapter
        itemRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            val newItemName = nameEt.text.toString()
            val newItemPrice = priceEt.text.toString()
            val newItemUrl = urlEt.text.toString()
            val newItem = Item(newItemName, newItemPrice, newItemUrl)
            nameEt.text.clear()
            priceEt.text.clear()
            urlEt.text.clear()

            if (newItemName.isNotEmpty()) {
                items.add(newItem)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClick(item: TextView) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.text.toString()))
            ContextCompat.startActivity(this, browserIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Invalid URL for " + item.text, Toast.LENGTH_LONG).show()
        }
    }
}