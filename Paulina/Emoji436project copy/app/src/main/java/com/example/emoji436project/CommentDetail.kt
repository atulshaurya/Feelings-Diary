package com.example.emoji436project

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class CommentDetail : AppCompatActivity() {
    private val imgCurrentUser: ImageView? = null

    private val txtPostDesc: TextView? = null
    private val txtPostTitle: TextView? = null


    private val editTextComment: EditText? = null
    private val btnAddComment: Button? = null

    private var PostKey: String? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null
    private var firebaseDatabase: FirebaseDatabase? = null

    private var RvComment: RecyclerView? = null
    private var commentAdapter: CommentAdapter? = null
    private var listComment: MutableList<Comment>? = null
    private var COMMENT_KEY = "Comment"

    private var mDatabaseReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_section)

        // let's set the statue bar to transparent
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportActionBar!!.hide()

        // ini Views
        RvComment = findViewById(R.id.rv_comment)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth!!.currentUser
        firebaseDatabase = FirebaseDatabase.getInstance()

        // add Comment button click listner
        btnAddComment!!.setOnClickListener(View.OnClickListener {
            btnAddComment.visibility = View.INVISIBLE
            val commentReference: DatabaseReference =
                firebaseDatabase!!.getReference(COMMENT_KEY).child(PostKey!!).push()

            val comment_content = editTextComment?.text.toString()
            val uid: String = firebaseUser?.uid.toString()
            val uname: String? = firebaseUser?.displayName
            val uimg: String = firebaseUser?.photoUrl.toString()

            val comment = Comment(comment_content, uid, uimg, uname)

            commentReference.setValue(comment)
                .addOnSuccessListener {
                    showMessage("comment added")
                    editTextComment!!.setText("")
                    btnAddComment.visibility = View.VISIBLE

                }.addOnFailureListener { e -> showMessage("fail to add comment : " + e.message) }
        })


        // now we need to bind all data into those views
        // firt we need to get post data
        // we need to send post detail data to this activity first ...
        // now we can get post data

        val postTitle = intent.extras!!.getString("title")
        txtPostTitle!!.text = postTitle

        val postDescription = intent.extras!!.getString("description")
        txtPostDesc!!.text = postDescription

        // setcomment user image
        if (imgCurrentUser != null) {
            Glide.with(this).load(firebaseUser!!.photoUrl).into(imgCurrentUser)
        }

        // get post id
        PostKey = intent.extras!!.getString("postKey")


        // ini Recyclerview Comment
        iniRvComment()
    }

    private fun iniRvComment() {
        RvComment?.layoutManager = LinearLayoutManager(this)
        val commentRef: DatabaseReference =
            firebaseDatabase!!.getReference(COMMENT_KEY).child(PostKey!!)

        commentRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listComment = ArrayList()
                for (snap in dataSnapshot.children) {
                    val comment: Comment? = snap.getValue(Comment::class.java)
                    (listComment as ArrayList<Comment>).add(comment!!)
                }
                commentAdapter = CommentAdapter(applicationContext,
                    listComment as ArrayList<Comment>
                )
                RvComment!!.adapter = commentAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}