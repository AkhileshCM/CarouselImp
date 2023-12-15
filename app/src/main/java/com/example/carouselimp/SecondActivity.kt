package com.example.carouselimp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class SecondActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val viewmodel= ViewModelProvider(this)[SecondActivityViewModel::class.java]
//        viewmodel.Imagelinks.observe(this){
//            Log.d("Links","$it")
//
//        }
    }
}