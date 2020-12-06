package com.example.diary.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.ExampleItem
import com.example.diary.ui.home.MyAdapter
import com.example.diary.R
import com.example.diary.ui.dashboard.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PublicFeed : AppCompatActivity() {
    var exampleList: ArrayList<ExampleItem> = arrayListOf<ExampleItem>()

    // ADDED //
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var username: String
    private lateinit var post: String
    private val TAG = "Public"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_feed)

        // DATABASE SETUP
        database = FirebaseDatabase.getInstance().getReference("data")
        uid = intent.getStringExtra(USER_ID)
        username = intent.getStringExtra(USER_EMAIL)
        post = intent.getStringExtra("content")


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
        val mainintent = Intent(this, com.example.diary.ui.dashboard.MainActivity::class.java)
        startActivity(mainintent)

        mainintent.putExtra(USER_ID, uid)
        mainintent.putExtra(USER_EMAIL, username)
    }

    private fun addToList(){
        val content: String? = intent.getStringExtra("content")
        val emoji = intent.getIntExtra("emoji", 0)
        val time = System.currentTimeMillis().toString()
        val  username = intent.getStringExtra(MainActivity.USER_EMAIL)
        val newItem = ExampleItem(time, R.drawable.emptyavatar, emoji,"USER", content)
        exampleList.add(0, newItem)
        Log.i(TAG, "size after addToList" + exampleList.size.toString())
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        Log.i(TAG, "json list in save data before erase " + sharedPreferences.getString(e_List, null))
        val json: String = gson.toJson(exampleList)
        Log.i(TAG, "json list in save data " + json)
        editor.putString(e_List, json)
        editor.apply()
    }
    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(e_List, null)
        Log.i(TAG, "json list in load data " + json)
        val type = object : TypeToken<ArrayList<ExampleItem?>?>() {}.type
        Log.i(TAG, "after type " + json)
        if (json == null) {
            val newItem = ExampleItem(0.toString(), R.drawable.emptyavatar, R.drawable.first_mood,"Feelings Diary", "Welcome to Feelings Diary! Write how you feel in a post!")
            exampleList.add(newItem)
        } else{
            exampleList = gson.fromJson(json, type) as ArrayList<ExampleItem>
        }
        Log.i(TAG, "after assignment " + json)
    }
    private fun buildRecyclerView(){
        val recycler_view: RecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        var adapter = MyAdapter(exampleList)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }



    companion object {
        val e_List = "eList"
        const val USER_ID = "com.example.diary.userid"
        const val USER_EMAIL = "com.example.diary.useremail"
    }
}