package com.swift2021.ibashareandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toNextPage:Button = findViewById(R.id.toNextPage)
        toNextPage.setOnClickListener {
            // 起動する対象をクラスオブジェクトで指定する
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}