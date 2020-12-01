package com.example.diary.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diary.MainActivity
import com.example.diary.MainActivity.Companion.TAG
import com.example.diary.R

open class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.dashboard_main, container, false)
/*
        val imageView2: ImageView = root.findViewById<View>(R.id.imageView2) as ImageView
        val imageView3: ImageView = root.findViewById<View>(R.id.imageView3) as ImageView
        val imageView5: ImageView = root.findViewById<View>(R.id.imageView5) as ImageView
        val imageView6: ImageView = root.findViewById<View>(R.id.imageView6) as ImageView
        val imageView7: ImageView = root.findViewById<View>(R.id.imageView7) as ImageView*/
        /*val button1: Button = root.findViewById<View>(R.id.button) as Button
        button1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                Log.i(TAG, "clicked button");
                Log.i(TAG, "opened public feed")

                var mEdit: EditText = root.findViewById<EditText>(R.id.editTextTextMultiLine2) as EditText
                val post: String = mEdit.getText().toString()
                Log.i(TAG, post)
            }
        })*/

        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        val it = Intent(this.context, com.example.diary.ui.dashboard.MainActivity::class.java)
        startActivity(it)
        return root
    }
}