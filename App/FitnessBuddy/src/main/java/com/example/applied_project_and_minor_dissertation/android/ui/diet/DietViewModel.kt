package com.example.applied_project_and_minor_dissertation.android.ui.calories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DietViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is diet Fragment"
    }
    val text: LiveData<String> = _text
}