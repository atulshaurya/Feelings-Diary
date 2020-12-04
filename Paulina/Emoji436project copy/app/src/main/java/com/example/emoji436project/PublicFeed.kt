package com.example.emoji436project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PublicFeed : AppCompatActivity() {
    val exampleList: ArrayList<ExampleItem> = arrayListOf<ExampleItem>()
    var adapter = MyAdapter(exampleList)
    var first_item = true
    var index = 0

    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_feed)

        val button4: Button = findViewById<View>(R.id.button4) as Button

        if (first_item) {
            val content: String? = intent.getStringExtra("content")
            val emoji = intent.getIntExtra("emoji", 0)


            val newItem = ExampleItem(R.drawable.emptyavatar, emoji,"User", content)
            exampleList.add(newItem)

            val recycler_view: RecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

            recycler_view.adapter = adapter

            recycler_view.layoutManager = LinearLayoutManager(this)

            recycler_view.setHasFixedSize(true)
            first_item = false
        }
        else{
            addToList()
        }

        button4.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                startMainActivity()

            }

        })

    }

    private fun startMainActivity() {
        val mainintent = Intent(this, MainActivity::class.java)
        startActivity(mainintent)
    }

    private fun addToList(){
        Log.i(TAG, "in generate list");

            val content: String? = intent.getStringExtra("content")
            val images = intent.getIntArrayExtra("images")
            val emoji = images?.get(1)

            val newItem = ExampleItem(R.drawable.emptyavatar, emoji,"User", content)

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
        index++

    }
}