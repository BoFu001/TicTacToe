package com.bofu.tictactoetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
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
                enableBtn(false)
                showRedLine(redLine)
                Toast.makeText(this, "${gameManager.currentPlayerMark} win!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showRedLine(redLine: RedLine) {
        val (rows, background) = when (redLine) {
            RedLine.HORIZONTAL_UP -> Pair(listOf(btn_one, btn_two, btn_three), R.drawable.ic_horizontal)
            RedLine.HORIZONTAL_MIDDLE -> Pair(listOf(btn_four, btn_five, btn_six), R.drawable.ic_horizontal)
            RedLine.HORIZONTAL_DOWN -> Pair(listOf(btn_seven, btn_eight, btn_nine), R.drawable.ic_horizontal)
            RedLine.VERTICAL_LEFT -> Pair(listOf(btn_one, btn_four, btn_seven), R.drawable.ic_vertical)
            RedLine.VERTICAL_MIDDLE -> Pair(listOf(btn_two, btn_five, btn_eight), R.drawable.ic_vertical)
            RedLine.VERTICAL_RIGHT -> Pair(listOf(btn_three, btn_six, btn_nine), R.drawable.ic_vertical)
            RedLine.DIAGONAL_LEFT -> Pair(listOf(btn_one, btn_five, btn_nine), R.drawable.ic_diagonal_left)
            RedLine.DIAGONAL_RIGHT -> Pair(listOf(btn_three, btn_five, btn_seven), R.drawable.ic_diagonal_right)
        }

        rows.forEach {
            it.background = ContextCompat.getDrawable(this, background)
        }
    }

    private fun enableBtn(bool: Boolean) {
        btn_one.isEnabled = bool
        btn_two.isEnabled = bool
        btn_three.isEnabled = bool
        btn_four.isEnabled = bool
        btn_five.isEnabled = bool
        btn_six.isEnabled = bool
        btn_seven.isEnabled = bool
        btn_eight.isEnabled = bool
        btn_nine.isEnabled = bool
    }
}