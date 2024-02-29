package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    val questionaireResults = QuestionaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false // 화면을 터치로 좌우로 움직일때 새로운 페이지가 나오는 것을 막음

    }

    //다음 버튼을 눌렀을 때 다음 페이지로 넘기는 함수
    fun moveToNextQuestion() {
        // 계속 다음 페이지로 넘길 수 없음. 마지막 질문 페이지가 넘어가면 결과창으로 넘어가야 함
        if(viewPager.currentItem==3) {
            //마지막 페이지인 경우 → 결과 화면으로 이동 + 결과 값 데이터 전송
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionaireResults.results))
            startActivity(intent)
        }else {
            //마지막 페이지가 아닌 경우 → 다음 페이지로 넘어가야 함
            //currentItem : 현재 페이지 번호
            val nextItem = viewPager.currentItem+1 // nextItem : 다음 페이지
            if(nextItem < viewPager.adapter?.itemCount ?:0) {
                viewPager.setCurrentItem(nextItem, true) // 무슨 말인지 잘 모르겠음
            }
        }
    }

}

// 또 다른 클래스 생성
// 질문지에 대한 응답을 저장하는 공간
class QuestionaireResults {
    val results = mutableListOf<Int>() // results 에는 1번이냐 2번이냐 숫자 값만 넣어도 됨 | 1,1,2 중 대답이 많은 1이 저장됨

    fun addResponses(response:List<Int>) { // 사용자가 만약 첫번쨰 질문지에서 1,1,2 를 선택하였을 경우 response → [1, 1, 2]
        // response 리스트에 들어가있는 값을 group 으로 묶고, 그룹 내에서 빈도수를 계산하고, 빈도수가 가장 높은 그룹의 key 값을 반환 → mostFrequent = 1
        val mostFrequent = response.groupingBy {it}.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let{results.add(it)} //해당 1 값을 results 에다가 더함
    }
}