package com.example.navigate.ui.home

import android.os.Bundle
import android.util.Log
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
    private var COMMENT_KEY = "Comment"
    private var PostKey: String? = null

    var commentList: ArrayList<Comment> = arrayListOf<Comment>()

    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var username: String
    private lateinit var post: String
    private lateinit var time: String
    private lateinit var s_emoji : String


    val recycler_view: RecyclerView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        uid = intent.getStringExtra(USER_ID).toString()
        Log.i("this is the UID", uid)
        username = intent.getStringExtra(USER_EMAIL).toString()
        post = intent.getStringExtra("content").toString()
        s_emoji = intent.getStringExtra("emoji").toString()


        database = FirebaseDatabase.getInstance().getReference("data")

        val commentText: EditText = findViewById(R.id.commentText)
        val button: Button = findViewById<Button>(R.id.commentButton)


        mDatabase = FirebaseDatabase.getInstance()

        button.setOnClickListener{
            val comment = commentText.text.toString()

            val id = database.child(uid).push().key
            if (id != null) {
                Log.i("Generated ID", id)
            }

            database.child(uid).child(id!!).push().key
            val newItem = ExampleItem(R.drawable.emptyavatar.toLong(), s_emoji.toLong(), username, post, comment)

            database.child(uid).child(id!!).setValue(newItem)

            showMessage("comment added!")

            commentText.setText("")
        }

        iniView()

    }

    private fun iniView() {
        recycler_view?.setLayoutManager(LinearLayoutManager(this))
        val commentRef = mDatabase?.getReference(COMMENT_KEY)?.child(PostKey!!)

        commentRef?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listComment = ArrayList<Comment>()
                for (snap in snapshot.children) {
                    val comment = snap.getValue(Comment::class.java)
                    (listComment as ArrayList<Comment>).add(comment!!)
                }
                val commentAdapter = CommentAdapter(applicationContext, listComment as ArrayList<Comment>)
                recycler_view?.adapter = commentAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    companion object {
        const val USER_ID = "com.example.navigate.userid"
        const val USER_EMAIL = "com.example.navigate.useremail"
    }
}