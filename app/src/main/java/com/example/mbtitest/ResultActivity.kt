package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // results = <1,2,1,1> 를 단어와 연결시켜서 mbti 결과 제목을 바꾸는 코드
        val results = intent.getIntegerArrayListExtra("results") ?: arrayListOf()

        val resultTypes = listOf(
            listOf("E","I"),
            listOf("N","S"),
            listOf("T","F"),
            listOf("J","P")
        )

        var resultString = ""

        // i = 0,1,2,3 ...
        for(i in results.indices) {
            resultString += resultTypes[i][results[i]-1]
        }

        val tv_resValue = findViewById<TextView>(R.id.tv_resValue)
        tv_resValue.text=resultString

        // mbti에 맞는 이미지뷰를 변경하는 코드
        val iv_resImg = findViewById<ImageView>(R.id.iv_resImg)
        val imageResource = resources.getIdentifier("ic_${resultString.toLowerCase(Locale.ROOT)}", "drawable", packageName) // 해당 코드가 이해하기가 어렵다.
        iv_resImg.setImageResource(imageResource)

        // 버튼을 눌렀을 때 처음 화면으로 이동하는 코드
        val buttonReset : Button = findViewById(R.id.btn_res_retry)
        buttonReset.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK // 이해하기 어려운 코드
            startActivity(intent)
        }
    }
}