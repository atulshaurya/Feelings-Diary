package com.example.navigate.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.navigate.ExampleItem
import com.example.navigate.R
import com.example.navigate.ui.home.HomeFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DashboardFragment : Fragment() {
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
    private lateinit var post: String

    var imageView2: ImageView? = null
    var imageView3: ImageView? = null
    var imageView5: ImageView? = null
    var imageView6: ImageView? = null
    var imageView7: ImageView? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =  inflater.inflate(R.layout.fragment_dashboard, container, false)

        // ADDED
        uid = activity?.intent?.getStringExtra(USER_ID).toString()
        Log.i("IDIDIDIDIDIDID", uid.toString())

        database = FirebaseDatabase.getInstance().getReference("data")
        uid = activity?.intent?.getStringExtra(USER_ID)!!
        username = activity?.intent?.getStringExtra(USER_EMAIL)!!

        var txt = view.findViewById<TextView>(R.id.textView2)
        var str = "Hi " + username

        txt.text = str

        time = System.currentTimeMillis().toString()

        Log.i(TAG, "after database")

        imageView2= view.findViewById<View>(R.id.imageView2) as ImageView
        imageView3= view.findViewById<View>(R.id.imageView3) as ImageView
        imageView5= view.findViewById<View>(R.id.imageView5) as ImageView
        imageView6= view.findViewById<View>(R.id.imageView6) as ImageView
        imageView7= view.findViewById<View>(R.id.imageView7) as ImageView
        val button1: Button = view.findViewById<View>(R.id.button) as Button


        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                var mEdit: EditText = view!!.findViewById(R.id.editTextTextMultiLine2)
                post = mEdit.text.toString()
                Log.i("POOOOOOOSSSSSSTTTTT", post)
                //Log.i(TAG, "about to open public feed")
                openPublicFeed(s_emoji, post)
            }
        })

        compute()
        return view

    }

    private fun compute(){
        imageView2!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v2++;
                if (selected_emoji_v2 % 2 == 0) {
                    makeSmaller(imageView2!!)
                } else {
                    makeBigger(imageView2!!)
                    s_emoji = R.drawable.first_mood
                    if (selected_emoji_v3 % 2 == 1) {
                        selected_emoji_v3++
                        makeSmaller(imageView3!!)
                    }
                    if (selected_emoji_v5 % 2 == 1) {
                        selected_emoji_v5++
                        makeSmaller(imageView5!!)
                    }
                    if (selected_emoji_v6 % 2 == 1) {
                        selected_emoji_v6++
                        makeSmaller(imageView6!!)
                    }
                    if (selected_emoji_v7 % 2 == 1) {
                        selected_emoji_v7++
                        makeSmaller(imageView7!!)
                    }

                }

            }
        })

        imageView3!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v3++;
                if (selected_emoji_v3 % 2 == 0) {
                    makeSmaller(imageView3!!)
                } else {
                    makeBigger(imageView3!!)
                    s_emoji = R.drawable.second_mood

                    if (selected_emoji_v2 % 2 == 1) {
                        selected_emoji_v2++
                        makeSmaller(imageView2!!)
                    }
                    if (selected_emoji_v5 % 2 == 1) {
                        selected_emoji_v5++
                        makeSmaller(imageView5!!)
                    }
                    if (selected_emoji_v6 % 2 == 1) {
                        selected_emoji_v6++
                        makeSmaller(imageView6!!)
                    }
                    if (selected_emoji_v7 % 2 == 1) {
                        selected_emoji_v7++
                        makeSmaller(imageView7!!)
                    }
                }

            }
        })

        imageView5!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v5++;
                if (selected_emoji_v5 % 2 == 0) {
                    makeSmaller(imageView5!!)
                } else {
                    makeBigger(imageView5!!)
                    s_emoji = R.drawable.fourth_mood
                }

                if (selected_emoji_v3 % 2 == 1) {
                    selected_emoji_v3++
                    makeSmaller(imageView3!!)
                }
                if (selected_emoji_v2 % 2 == 1) {
                    selected_emoji_v2++
                    makeSmaller(imageView2!!)
                }
                if (selected_emoji_v6 % 2 == 1) {
                    selected_emoji_v6++
                    makeSmaller(imageView6!!)
                }
                if (selected_emoji_v7 % 2 == 1) {
                    selected_emoji_v7++
                    makeSmaller(imageView7!!)
                }
            }
        })

        imageView6!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v6++;
                if (selected_emoji_v6 % 2 == 0) {
                    makeSmaller(imageView6!!)
                } else {
                    makeBigger(imageView6!!)
                    s_emoji = R.drawable.fifth_mood

                    if (selected_emoji_v3 % 2 == 1) {
                        selected_emoji_v3++
                        makeSmaller(imageView3!!)
                    }
                    if (selected_emoji_v5 % 2 == 1) {
                        selected_emoji_v5++
                        makeSmaller(imageView5!!)
                    }
                    if (selected_emoji_v2 % 2 == 1) {
                        selected_emoji_v2++
                        makeSmaller(imageView2!!)
                    }
                    if (selected_emoji_v7 % 2 == 1) {
                        selected_emoji_v7++
                        makeSmaller(imageView7!!)
                    }
                }

            }
        })

        imageView7!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v7++;
                if (selected_emoji_v7 % 2 == 0) {
                    makeSmaller(imageView7!!)
                } else {
                    makeBigger(imageView7!!)
                    s_emoji = R.drawable.third_mood

                    if (selected_emoji_v3 % 2 == 1) {
                        selected_emoji_v3++
                        makeSmaller(imageView3!!)
                    }
                    if (selected_emoji_v5 % 2 == 1) {
                        selected_emoji_v5++
                        makeSmaller(imageView5!!)
                    }
                    if (selected_emoji_v6 % 2 == 1) {
                        selected_emoji_v6++
                        makeSmaller(imageView6!!)
                    }
                    if (selected_emoji_v2 % 2 == 1) {
                        selected_emoji_v2++
                        makeSmaller(imageView2!!)
                    }
                }

            }
        })
    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_dashboard)
        Log.i(TAG, "atleast this should show")

        *//* Navigation *//*
        *//*val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*//*
        *//* Navigation *//*



        // ADDED
        uid = intent.getStringExtra(USER_ID).toString()
        Log.i("IDIDIDIDIDIDID", uid.toString())

        database = FirebaseDatabase.getInstance().getReference("data")
        uid = intent.getStringExtra(USER_ID)!!
        username = intent.getStringExtra(USER_EMAIL)!!

        time = System.currentTimeMillis().toString()

        Log.i(TAG, "after database")

        val imageView2: ImageView = findViewById<View>(R.id.imageView1) as ImageView
        val imageView3: ImageView = findViewById<View>(R.id.imageView2) as ImageView
        val imageView5: ImageView = findViewById<View>(R.id.imageView3) as ImageView
        val imageView6: ImageView = findViewById<View>(R.id.imageView4) as ImageView
        val imageView7: ImageView = findViewById<View>(R.id.imageView5) as ImageView
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

    }*/

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
        post: String,
    ): HomeFragment {



        val bundle = Bundle().apply {
            putInt("emoji", emoji)
            putString(USER_ID, uid)
            putString(USER_EMAIL, username)
            putString("content", post)
        }
        val pubfeed = HomeFragment()
        //pubfeed.arguments = bundle


        val intent = Intent(context, HomeFragment::class.java)
        intent.putExtra("emoji", s_emoji)
        intent.putExtra("user", "USER")
        intent.putExtra("content", post)
        intent.putExtra(USER_ID, uid)
        intent.putExtra(USER_EMAIL, username)
        Log.i(TAG, "put extras")
        //startActivity(intent)
        Log.i(TAG, "started intent")

        // ADDED
        val id = database.child(uid).push().key
        if (id != null) {
            Log.i("Generated ID", id)
        }

        database.child(uid).child(id!!).push().key
        val newItem = ExampleItem(R.drawable.emptyavatar.toLong(), s_emoji.toLong(), username, post)

        database.child(uid).child(id!!).setValue(newItem)
        Log.i(TAG, "opened public feed")
        /*val fragment2 = HomeFragment()
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_main, fragment2, "tag")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()*/

        Toast.makeText(context, "Saved to diary!", Toast.LENGTH_LONG).show()

        return pubfeed

    }

    companion object {
        const val USER_ID = "com.example.navigate.userid"
        const val USER_EMAIL = "com.example.navigate.useremail"
        const val TAG = "DASHBOARD DASHBOARD FRAGMENT"

    }


}