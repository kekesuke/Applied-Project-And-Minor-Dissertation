package com.example.applied_project_and_minor_dissertation.android.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Register Fragment"
    }
    val text: LiveData<String> = _text
}