package com.example.diary.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CommentSection : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mDatabase: FirebaseDatabase? = null
    private var listComment: MutableList<Comment>? = null
    private var COMMENT_KEY = "Comment"
    private var PostKey: String? = null

    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var username: String
    private lateinit var post: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        uid = intent.getStringExtra(USER_ID)
        username = intent.getStringExtra(USER_EMAIL)
        post = intent.getStringExtra("content")
        database = FirebaseDatabase.getInstance().getReference("data")

        val comment: EditText = findViewById(R.id.commentText)
        val button: Button = findViewById<Button>(R.id.commentButton)


        mAuth = FirebaseAuth.getInstance()
        val firebaseUser = mAuth!!.currentUser
        mDatabase = FirebaseDatabase.getInstance()

        button.setOnClickListener{
            button.visibility = View.INVISIBLE
            val commentReference = mDatabase!!.getReference(COMMENT_KEY).child(PostKey!!).push()
            val content = comment.text.toString()
            val name = firebaseUser?.displayName
            val uid = firebaseUser?.uid.toString()

            val newComment = Comment(content, uid, name)

            commentReference.setValue(comment)
                    .addOnSuccessListener {
                        showMessage("comment added")
                        comment!!.setText("")
                        button.visibility = View.VISIBLE

                    }.addOnFailureListener { e -> showMessage("fail to add comment : " + e.message) }

        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        val e_List = "eList"
        const val USER_ID = "com.example.diary.userid"
        const val USER_EMAIL = "com.example.diary.useremail"
    }
}
