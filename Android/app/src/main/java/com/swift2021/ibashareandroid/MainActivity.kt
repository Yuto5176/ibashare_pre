package com.swift2021.ibashareandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 実行ボタンタップ時
    fun onButtonTapped(view: View?){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}