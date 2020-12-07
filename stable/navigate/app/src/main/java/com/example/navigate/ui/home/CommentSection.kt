package com.example.navigate.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.ExampleItem
import com.example.navigate.R
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class CommentSection : AppCompatActivity() {
    /* Create all the variables necessary for the comment section */
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var subuid: String
    private lateinit var username: String
    private lateinit var post: String
    private lateinit var s_emoji : String

    private var mDatabase: FirebaseDatabase? = null

    var commentList: ArrayList<Comment> = arrayListOf<Comment>()
    var recycler_view: RecyclerView? = null

    /* This method will open up the comment section class, where it will display the post that
    * got clicked on and be able to comment on it */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        recycler_view = findViewById<View>(R.id.rv_comment) as RecyclerView
        buildRecyclerView(recycler_view!!)

        uid = intent.getStringExtra(USER_ID).toString()
        Log.i("this is the UID", uid)
        username = intent.getStringExtra(USER_EMAIL).toString()
        var tv1 = findViewById<TextView>(R.id.text_view_1)
        var tv2 = findViewById<TextView>(R.id.text_view_2)

        post = intent.getStringExtra("content").toString()
        s_emoji = intent.getStringExtra("emoji").toString()
        subuid = intent.getStringExtra("subid").toString()

        Log.i("POST HERE", post)

        tv1.text = username
        tv2.setText(post)

        database = FirebaseDatabase.getInstance().getReference("data")

        val commentText: EditText = findViewById(R.id.commentText)
        val button: Button = findViewById<Button>(R.id.commentButton)

        mDatabase = FirebaseDatabase.getInstance()

        /* This button will be the one adding the comments to the data base */
        button.setOnClickListener{
            val comment = commentText.text.toString()
            database.child(uid).child(subuid).child("comments").push().setValue(comment)

            showMessage("Comment added!")
            commentText.setText("")
        }

        iniView()

    }

    /* It initializes the recycler view of all the comments */
    private fun iniView() {
        recycler_view?.setLayoutManager(LinearLayoutManager(this))
        val commentRef = mDatabase?.getReference("data")!!.child(uid).child(subuid).child("comments")
        commentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val allComments = dataSnapshot.children

                allComments.forEach {
                    //comment ID
                    var cid = it.key.toString()
                    Log.i("Comment ID", it.key.toString())
                    Log.i("COMMENT", it.value.toString())
                    var comment = Comment(it.value.toString(), cid, "Anonymous said:")
                    commentList.add(comment)
                    buildRecyclerView(recycler_view!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    /* It builds the recycler view when creating a new CommentAdapter */
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