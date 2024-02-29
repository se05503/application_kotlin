package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start = findViewById<ImageView>(R.id.iv_start) //변수 선언
        btn_start.setOnClickListener {
            //다른 화면으로 넘어가기 위해서는 인텐트 선언
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

    }
}