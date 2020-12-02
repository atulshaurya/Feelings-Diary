package com.example.emoji436project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class PublicFeed : AppCompatActivity() {
    var exampleList: ArrayList<ExampleItem> = arrayListOf<ExampleItem>()
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_feed)

        loadData()
        buildRecyclerView()
        Log.i(TAG, "size before Add " + exampleList.size.toString())
        addToList()
        Log.i(TAG, "size after add " + exampleList.size.toString())
        saveData()
        Log.i(TAG, "size after save Data " + exampleList.size.toString())

        val button4: Button = findViewById<View>(R.id.button4) as Button
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
        val content: String? = intent.getStringExtra("content")
        val emoji = intent.getIntExtra("emoji", 0)

        val newItem = ExampleItem(R.drawable.emptyavatar, emoji,"User", content)
        exampleList.add(0, newItem)
        Log.i(TAG, "size after addToList" + exampleList.size.toString())
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        Log.i(TAG, "json list in save data before erase " + sharedPreferences.getString(eList, null))
        val json: String = gson.toJson(exampleList)
        Log.i(TAG, "json list in save data " + json)
        editor.putString(eList, json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(eList, null)
        Log.i(TAG, "json list in load data " + json)
        val type = object : TypeToken<ArrayList<ExampleItem?>?>() {}.type
        exampleList = gson.fromJson(json, type) as ArrayList<ExampleItem>
    }

    private fun buildRecyclerView(){
        val recycler_view: RecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        var adapter = MyAdapter(exampleList)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    companion object {
        val eList = "eList"
    }

}