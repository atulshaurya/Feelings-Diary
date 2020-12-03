package com.example.diary.ui.dashboard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.diary.ExampleItem
import com.example.diary.R
import com.example.diary.ui.home.MyAdapter
import com.example.diary.ui.home.PublicFeed
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private var  selected_emoji_v2 = 0;
    private var  selected_emoji_v3 = 0;
    private var  selected_emoji_v5 = 0;
    private var  selected_emoji_v6 = 0;
    private var  selected_emoji_v7 = 0;
    var s_emoji:Int = 0


    /* ADDED */
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private lateinit var time: String
    private lateinit var username: String
    /* ADDED */


    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_main)
        Log.i(TAG, "atleast this should show")

        // ADDED
        database = FirebaseDatabase.getInstance().getReference("data")
//        uid = intent.getStringExtra(USER_ID)!!
//        username = intent.getStringExtra(USER_EMAIL)

        time = System.currentTimeMillis().toString()

        Log.i(TAG, "after database")

        val imageView2: ImageView = findViewById<View>(R.id.imageView2) as ImageView
        val imageView3: ImageView = findViewById<View>(R.id.imageView3) as ImageView
        val imageView5: ImageView = findViewById<View>(R.id.imageView5) as ImageView
        val imageView6: ImageView = findViewById<View>(R.id.imageView6) as ImageView
        val imageView7: ImageView = findViewById<View>(R.id.imageView7) as ImageView
        val button1: Button = findViewById<View>(R.id.button) as Button


        button1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {

                var mEdit: EditText = findViewById<EditText>(R.id.editTextTextMultiLine2) as EditText
                val post: String = mEdit.getText().toString()
                Log.i(TAG, "about to open public feed")
                openPublicFeed(s_emoji, post)
            }
        })
        imageView2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v2++;
                if (selected_emoji_v2%2 == 0){
                    makeSmaller(imageView2)
                }
                else {
                    makeBigger(imageView2)
                    s_emoji = R.drawable.first_mood
                    if (selected_emoji_v3%2 == 1){
                        selected_emoji_v3++
                        makeSmaller(imageView3)
                    }
                    if(selected_emoji_v5%2==1){
                        selected_emoji_v5++
                        makeSmaller(imageView5)
                    }
                    if(selected_emoji_v6%2==1){
                        selected_emoji_v6++
                        makeSmaller(imageView6)
                    }
                    if(selected_emoji_v7%2==1){
                        selected_emoji_v7++
                        makeSmaller(imageView7)
                    }

                }

            }
        })

        imageView3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v3++;
                if (selected_emoji_v3%2 == 0){
                    makeSmaller(imageView3)
                }
                else {
                    makeBigger(imageView3)
                    s_emoji = R.drawable.second_mood

                    if (selected_emoji_v2%2 == 1){
                        selected_emoji_v2++
                        makeSmaller(imageView2)
                    }
                    if(selected_emoji_v5%2==1){
                        selected_emoji_v5++
                        makeSmaller(imageView5)
                    }
                    if(selected_emoji_v6%2==1){
                        selected_emoji_v6++
                        makeSmaller(imageView6)
                    }
                    if(selected_emoji_v7%2==1){
                        selected_emoji_v7++
                        makeSmaller(imageView7)
                    }
                }

            }
        })

        imageView5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v5++;
                if (selected_emoji_v5%2 == 0){
                    makeSmaller(imageView5)
                }
                else {
                    makeBigger(imageView5)
                    s_emoji = R.drawable.fourth_mood
                }

                if (selected_emoji_v3%2 == 1){
                    selected_emoji_v3++
                    makeSmaller(imageView3)
                }
                if(selected_emoji_v2%2==1){
                    selected_emoji_v2++
                    makeSmaller(imageView2)
                }
                if(selected_emoji_v6%2==1){
                    selected_emoji_v6++
                    makeSmaller(imageView6)
                }
                if(selected_emoji_v7%2==1){
                    selected_emoji_v7++
                    makeSmaller(imageView7)
                }
            }
        })

        imageView6.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v6++;
                if (selected_emoji_v6%2 == 0){
                    makeSmaller(imageView6)
                }
                else {
                    makeBigger(imageView6)
                    s_emoji = R.drawable.fifth_mood

                    if (selected_emoji_v3%2 == 1){
                        selected_emoji_v3++
                        makeSmaller(imageView3)
                    }
                    if(selected_emoji_v5%2==1){
                        selected_emoji_v5++
                        makeSmaller(imageView5)
                    }
                    if(selected_emoji_v2%2==1){
                        selected_emoji_v2++
                        makeSmaller(imageView2)
                    }
                    if(selected_emoji_v7%2==1){
                        selected_emoji_v7++
                        makeSmaller(imageView7)
                    }
                }

            }
        })

        imageView7.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v7++;
                if (selected_emoji_v7%2 == 0){
                    makeSmaller(imageView7)
                }
                else {
                    makeBigger(imageView7)
                    s_emoji = R.drawable.third_mood

                    if (selected_emoji_v3%2 == 1){
                        selected_emoji_v3++
                        makeSmaller(imageView3)
                    }
                    if(selected_emoji_v5%2==1){
                        selected_emoji_v5++
                        makeSmaller(imageView5)
                    }
                    if(selected_emoji_v6%2==1){
                        selected_emoji_v6++
                        makeSmaller(imageView6)
                    }
                    if(selected_emoji_v2%2==1){
                        selected_emoji_v2++
                        makeSmaller(imageView2)
                    }
                }

            }
        })

    }

    private fun makeSmaller(imageView: ImageView) {
        val layout = imageView.layoutParams as ConstraintLayout.LayoutParams
        layout.height = 100
        layout.width = 100
        imageView.setLayoutParams(layout)
    }

    private fun makeBigger(imageView: ImageView) {
        val layout = imageView.layoutParams as ConstraintLayout.LayoutParams
        layout.height = 150
        layout.width = 150
        imageView.setLayoutParams(layout)
    }

    fun openPublicFeed(
        emoji: Int,
        post: String
    ) {
        Log.i(TAG, "opened public feed")
        val intent = Intent(this, PublicFeed::class.java)
        intent.putExtra("emoji", emoji)
//        intent.getStringExtra(USER_EMAIL)
        intent.putExtra("user", "USER" )
        intent.putExtra("content", post)
//        intent.putExtra(USER_ID, uid)
//        intent.putExtra(USER_EMAIL, username)
        Log.i(TAG, "put extras")
        startActivity(intent)
        Log.i(TAG, "started intent")

        // ADDED
//        val id = database.child(uid).push().key
//        Log.i("Generated ID", id)
//        database.child(uid).child(id!!).push()
//        val newItem = ExampleItem(time, R.drawable.emptyavatar,s_emoji, "USER", post)
//
//        database.child(uid).child(id!!).setValue(newItem)
    }

    companion object {
        const val USER_ID = "com.example.diary.userid"
        const val USER_EMAIL = "com.example.diary.useremail"
    }


}