package com.bofu.tictactoetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName
    private val gameManager: GameManager by lazy { GameManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSetup()
    }

    private fun btnSetup(){
        btn_one.setOnClickListener{ onBtnClick(btn_one, Coordinate(0,0))}
        btn_two.setOnClickListener{ onBtnClick(btn_two, Coordinate(0,1))}
        btn_three.setOnClickListener{ onBtnClick(btn_three, Coordinate(0,2))}
        btn_four.setOnClickListener{ onBtnClick(btn_four, Coordinate(1,0))}
        btn_five.setOnClickListener{ onBtnClick(btn_five, Coordinate(1,1))}
        btn_six.setOnClickListener{ onBtnClick(btn_six, Coordinate(1,2))}
        btn_seven.setOnClickListener{ onBtnClick(btn_seven, Coordinate(2,0))}
        btn_eight.setOnClickListener{ onBtnClick(btn_eight, Coordinate(2,1))}
        btn_nine.setOnClickListener{ onBtnClick(btn_nine, Coordinate(2,2))}
    }

    private fun onBtnClick(btn: TextView, coordinate: Coordinate){
        if(btn.text.isEmpty()) {
            btn.text = gameManager.currentPlayerMark
            val redLine = gameManager.play(coordinate)
            if(redLine != null){
                Log.d(TAG, "redLine: " + redLine)
            }
        }
    }

    private fun showRedLine(redLine: RedLine) {

    }
}