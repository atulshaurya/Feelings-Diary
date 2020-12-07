package com.example.navigate.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.R
import androidx.fragment.app.Fragment
import com.example.navigate.ui.dashboard.DashboardFragment
import com.example.navigate.ExampleItem
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HomeFragment : Fragment(), MyAdapter.ItemClickListener {
    var exampleList: ArrayList<ExampleItem> = arrayListOf<ExampleItem>()

    // ADDED //
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var username: String
    private lateinit var post: String
    private var emoji: Long = 0
    private val TAG = "Public"
    private lateinit var subid: String
    val recycler_view: RecyclerView? = null


    /* Creates the public feed fragment where it displays all the posts from the users */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(R.layout.fragment_home, container, false)
        var recycler_view: RecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView

        // Getting reference to the database
        database = FirebaseDatabase.getInstance().getReference("data")

        if(savedInstanceState != null) {
            uid = savedInstanceState!!.get(USER_ID) as String
            username = savedInstanceState!!.get(USER_EMAIL) as String
            post = savedInstanceState!!.get("content") as String
        }
        else{
            uid = ""
            username = ""
            post = ""
        }

        val queryRef = database.orderByPriority()

        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val childs = dataSnapshot.children

                childs.forEach {
                    uid = it.key.toString()
                    Log.i("USERID MAYBE", it.key.toString())
                    addToView(it, it.key)
                    buildRecyclerView(recycler_view)
                }

                Log.i("pfdbe", post.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

        buildRecyclerView(recycler_view)

        return view
    }

    /* This method will be adding each post to the view as a new item from the recycler view */
    private fun addToView(ds: DataSnapshot, key: String?) {
        ds.children.forEach {
            subid = it.key.toString()
            Log.i("SUB ID", subid)
            var username = it.child("username").value as String
            Log.i("CHEEEECKKK", username.toString())
            var avatar: Long = it.child("avatar").value as Long
            var emoji= it.child("emoji").value as Long
            this.emoji = emoji
            var feelings = it.child("feelings").value as String

            // The example item
            var egi = ExampleItem(uid, subid, (avatar), emoji, username, feelings)

            exampleList.add(0,egi)
        }
    }

    fun Long.toIntOrNull(): Int? {
        val i = this.toInt()
        return if (i.toLong() == this) i else null
    }

    private fun startMainActivity() {
        val mainintent = Intent(activity, DashboardFragment::class.java)
        mainintent.putExtra(USER_ID, uid)
        mainintent.putExtra(USER_EMAIL, username)
        startActivity(mainintent)

    }

    private fun saveData() {
        val sharedPreferences = activity?.getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val gson = Gson()
        Log.i(TAG, "json list in save data before erase " + sharedPreferences?.getString(e_List, null))
        val json: String = gson.toJson(exampleList)
        Log.i(TAG, "json list in save data " + json)
        if (editor != null) {
            editor.putString(e_List, json)
        }
        if (editor != null) {
            editor.apply()
        }
    }

    /* Builds the recycler view */
    private fun buildRecyclerView(recycler_view: RecyclerView){
        var adapter = MyAdapter(exampleList, this)
        recycler_view!!.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
    }

    /* This onClick method will open up each post with a comment section where the users
    * can comment anything they want */
    override fun onClick(position: Int) {
        val eg: ExampleItem = exampleList.get(position)
        val intent = Intent(activity, CommentSection::class.java)
        intent.putExtra("emoji", eg.emoji.toString())
        intent.putExtra("content", eg.feelings)
        intent.putExtra(USER_ID, eg.uid)
        intent.putExtra(USER_EMAIL, eg.username)
        intent.putExtra("subid", eg.subid)
        intent.putExtra("avatar", R.drawable.emptyavatar)
        startActivity(intent)
    }

    companion object {
        val e_List = "eList"
        const val USER_ID = "com.example.navigate.userid"
        const val USER_EMAIL = "com.example.navigate.useremail"
    }
}