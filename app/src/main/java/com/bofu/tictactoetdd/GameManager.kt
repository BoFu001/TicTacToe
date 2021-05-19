package com.bofu.tictactoetdd

class GameManager {

    var currentPlayer = 1

    val currentPlayerMark: String
        get() = if (currentPlayer == 1) "X" else "O"

    var state = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun play() {
        currentPlayer++
    }
}
