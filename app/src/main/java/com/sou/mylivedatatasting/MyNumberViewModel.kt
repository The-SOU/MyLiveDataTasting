package com.sou.mylivedatatasting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyNumberViewModel : ViewModel() {

    enum class ActionType {
        PLUS, MINUS
    }

    companion object {
        const val TAG = "로그"

    }


    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }

    }
}