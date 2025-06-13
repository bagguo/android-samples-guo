package com.example.guo.communication.sharedvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val cResultLiveData = MutableLiveData<String>()
}