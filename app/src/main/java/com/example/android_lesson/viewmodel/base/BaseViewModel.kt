package com.example.android_lesson.viewmodel.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

open class BaseViewModel : ViewModel() {


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit): Job {
        return launchOnUI(block, failedException = null)
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit, failedException: (() -> Unit)?): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}