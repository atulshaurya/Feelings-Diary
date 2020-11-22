package com.example.emoji436project

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    private lateinit var yourBitmap: Bitmap
    private var  selected_emoji_v2 = 0;
    private var  selected_emoji_v3 = 0;
    private var  selected_emoji_v5 = 0;
    private var  selected_emoji_v6 = 0;
    private var  selected_emoji_v7 = 0;
    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView2: ImageView = findViewById<View>(R.id.imageView2) as ImageView
        val imageView3: ImageView = findViewById<View>(R.id.imageView3) as ImageView
        val imageView5: ImageView = findViewById<View>(R.id.imageView5) as ImageView
        val imageView6: ImageView = findViewById<View>(R.id.imageView6) as ImageView
        val imageView7: ImageView = findViewById<View>(R.id.imageView7) as ImageView

        imageView2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                selected_emoji_v2++;
                Log.v(TAG, "num:" + selected_emoji_v2);
                if (selected_emoji_v2%2 == 0){
                    makeSmaller(imageView2)
                    Log.v(TAG, "even:" + selected_emoji_v2);
                }
                else {
                    makeBigger(imageView2)
                    Log.v(TAG, "even:" + selected_emoji_v2);
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
                Log.v(TAG, "num:" + selected_emoji_v3);
                if (selected_emoji_v3%2 == 0){
                    makeSmaller(imageView3)
                    Log.v(TAG, "even:" + selected_emoji_v3);
                }
                else {
                    makeBigger(imageView3)
                    Log.v(TAG, "odd:" + selected_emoji_v3);

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
                Log.v(TAG, "num:" + selected_emoji_v5);
                if (selected_emoji_v5%2 == 0){
                    makeSmaller(imageView5)
                    Log.v(TAG, "even:" + selected_emoji_v5);
                }
                else {
                    makeBigger(imageView5)
                    Log.v(TAG, "odd:" + selected_emoji_v5);
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
                Log.v(TAG, "num:" + selected_emoji_v6);
                if (selected_emoji_v6%2 == 0){
                    makeSmaller(imageView6)
                    Log.v(TAG, "even:" + selected_emoji_v6);
                }
                else {
                    makeBigger(imageView6)
                    Log.v(TAG, "odd:" + selected_emoji_v6);

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
                Log.v(TAG, "num:" + selected_emoji_v7);
                if (selected_emoji_v7%2 == 0){
                    makeSmaller(imageView7)
                    Log.v(TAG, "even:" + selected_emoji_v7);
                }
                else {
                    makeBigger(imageView7)
                    Log.v(TAG, "odd:" + selected_emoji_v7);

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


}
