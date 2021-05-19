package com.bofu.tictactoetdd

class GameManager {

    var currentPlayer = 1

    val currentPlayerMark: String
        get() = if (currentPlayer == 1) "X" else "O"


    fun play() {
        currentPlayer++
    }
}
