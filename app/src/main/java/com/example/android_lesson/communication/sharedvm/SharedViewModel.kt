package com.example.android_lesson.communication.sharedvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val cResultLiveData = MutableLiveData<String>()
}