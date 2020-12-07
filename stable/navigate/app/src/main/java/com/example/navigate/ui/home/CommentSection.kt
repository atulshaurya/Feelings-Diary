package com.example.navigate.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.ExampleItem
import com.example.navigate.R
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class CommentSection : AppCompatActivity() {
    private var mDatabase: FirebaseDatabase? = null
    private var listComment: MutableList<Comment>? = null
    private var PostKey: String? = null

    var commentList: ArrayList<Comment> = arrayListOf<Comment>()

    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var subuid: String
    private lateinit var username: String
    private lateinit var post: String
    private lateinit var time: String
    private lateinit var s_emoji : String
    var recycler_view: RecyclerView? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        recycler_view = findViewById<View>(R.id.rv_comment) as RecyclerView


        uid = intent.getStringExtra(USER_ID).toString()
        Log.i("this is the UID", uid)
        username = intent.getStringExtra(USER_EMAIL).toString()
        post = intent.getStringExtra("content").toString()
        s_emoji = intent.getStringExtra("emoji").toString()
        subuid = intent.getStringExtra("subid").toString()


        database = FirebaseDatabase.getInstance().getReference("data")

        val commentText: EditText = findViewById(R.id.commentText)
        val button: Button = findViewById<Button>(R.id.commentButton)


        mDatabase = FirebaseDatabase.getInstance()

        button.setOnClickListener{
            val comment = commentText.text.toString()



            //database.child(uid).child(id!!).push().key
            //val newItem = ExampleItem(uid, subuid, R.drawable.emptyavatar.toLong(), s_emoji.toLong(), username, post)
            database.child(uid).child(subuid).child("comments").push().setValue(comment)

            showMessage("Comment added!")

            commentText.setText("")
        }

        iniView()

    }

    private fun iniView() {
        recycler_view?.setLayoutManager(LinearLayoutManager(this))
        val commentRef = mDatabase?.getReference("data")!!.child(uid).child(subuid).child("comments")

        /*var comment: String = commentRef.child("comment").toString()

        Log.i("COMMENT HERE -> ", comment)*/

        commentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val allComments = dataSnapshot.children

                allComments.forEach {
                    //comment ID
                    var cid = it.key.toString()
                    Log.i("Comment ID", it.key.toString())
                    Log.i("COMMENT", it.value.toString())
                    buildRecyclerView(recycler_view!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun buildRecyclerView(recycler_view: RecyclerView){
        var adapter = CommentAdapter(commentList)
        recycler_view!!.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    companion object {
        const val USER_ID = "com.example.navigate.userid"
        const val USER_EMAIL = "com.example.navigate.useremail"
    }
}