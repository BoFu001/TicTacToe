package com.bofu.tictactoetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameManager: GameManager by lazy { GameManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSetup()
    }

    private fun btnSetup(){
        btn_one.setOnClickListener {
            btn_one.text = gameManager.currentPlayerMark
            gameManager.alternatePlayer()
        }
        btn_two.setOnClickListener {
            btn_two.text = gameManager.currentPlayerMark
            gameManager.alternatePlayer()
        }
    }
}