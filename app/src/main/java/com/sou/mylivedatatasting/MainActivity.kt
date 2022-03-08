package com.sou.mylivedatatasting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sou.mylivedatatasting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG = "로그"

    }

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextview.text = it.toString()
        })

        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)

    }

    override fun onClick(view: View?) = with(binding) {
        //버튼을 클릭하면 뷰모델에 있는 업데이트밸류 메소드를 실행시킨다.
        val userInput = numberInputEdittext.text.toString().toInt()

        when(view){
            plusBtn ->
                myNumberViewModel.updateValue(MyNumberViewModel.ActionType.PLUS, userInput)
            minusBtn ->
                myNumberViewModel.updateValue(MyNumberViewModel.ActionType.MINUS, userInput)
        }
    }
}