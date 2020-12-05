package com.example.diary.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.R

class CommentSection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.diary.R.layout.comment_section)
    }
}