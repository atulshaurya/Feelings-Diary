package com.example.diary.ui.home

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
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var listComment: MutableList<Comment>? = null
    private var COMMENT_KEY = "Comment"
    private var PostKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        val userIcon: ImageView = findViewById(R.id.imageView4)
        val emoji: ImageView = findViewById(R.id.imageView9)
        val username: TextView = findViewById(R.id.text_view_1)
        val feelings: TextView = findViewById(R.id.text_view_2)

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
}
