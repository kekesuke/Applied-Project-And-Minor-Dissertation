package com.example.applied_project_and_minor_dissertation.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Maps"
        }

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}