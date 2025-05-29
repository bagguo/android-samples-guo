package com.example.guo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.guo.language.kotlin.coroutine.CoroutineSample
import com.example.guo.viewmodel.base.BaseViewModel
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