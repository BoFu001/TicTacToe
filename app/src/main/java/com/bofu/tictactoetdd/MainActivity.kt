package com.bofu.tictactoetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameManager: GameManager by lazy { GameManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSetup()
    }

    private fun btnSetup(){
        btn_one.setOnClickListener{ onBtnClick(btn_one)}
        btn_two.setOnClickListener{ onBtnClick(btn_two)}
        btn_three.setOnClickListener{ onBtnClick(btn_three)}
        btn_four.setOnClickListener{ onBtnClick(btn_four)}
        btn_five.setOnClickListener{ onBtnClick(btn_five)}
        btn_six.setOnClickListener{ onBtnClick(btn_six)}
        btn_seven.setOnClickListener{ onBtnClick(btn_seven)}
        btn_eight.setOnClickListener{ onBtnClick(btn_eight)}
        btn_nine.setOnClickListener{ onBtnClick(btn_nine)}
    }

    private fun onBtnClick(btn: TextView){
        if(btn.text.isEmpty()) {
            btn.text = gameManager.currentPlayerMark
            gameManager.alternatePlayer()
        }
    }
}