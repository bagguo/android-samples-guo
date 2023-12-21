package com.example.android_lesson.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_lesson.async.coroutine.CoroutineSample
import com.example.android_lesson.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

class JetpackDemoViewModel : BaseViewModel() {

    var counter = MutableLiveData<Int>()

    fun plus() {
//        launchOnUI {
//            CoroutineSample().main()
//        }

        viewModelScope.launch {
            CoroutineSample().main()
        }
        counter.value = 3
    }
}