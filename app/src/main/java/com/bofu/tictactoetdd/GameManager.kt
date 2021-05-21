package com.bofu.tictactoetdd

import com.bofu.tictactoetdd.models.Coordinate
import com.google.gson.Gson

class GameManager(val row: Int, val column: Int) {

    var currentPlayer = 1

    val currentPlayerMark: String
        get() = if (currentPlayer == 1) Mark.X.toString() else Mark.O.toString()

    var stateManager = StateManager(row, column)

    fun alternatePlayer() {
        currentPlayer = 3 - currentPlayer
    }

    fun detectWinCase(): Boolean {

        // detect row
        for (row in stateManager.state.indices) {
            val hasDetected = stateManager.state[row].all { player -> player == currentPlayer }
            if (hasDetected) {
                for (column in stateManager.picture[row].indices){
                    stateManager.picture[row][column] = Type.HORIZONTAL
                }
                return true
            }
        }

        // detect column
        val stateRotated = rotation(stateManager.state)
        for (row in stateRotated.indices) {
            val hasDetected = stateRotated[row].all { player -> player == currentPlayer }
            if (hasDetected) {

                for(r in stateManager.picture.indices){
                    for(c in stateManager.picture[r].indices){
                        if(c == row){
                            stateManager.picture[r][c] = Type.VERTICAL
                        }
                    }
                }
                return true
            }
        }

        // detect diagonal left and right
        val diagonalLeft = IntArray(column)
        val diagonalRight = IntArray(column)
        for (row in stateManager.state.indices) {
            for (column in stateManager.state[row].indices) {
                if (row == column){
                    diagonalLeft[row] = stateManager.state[row][column]
                }
                if (row == (stateManager.state[row].lastIndex - column)){
                    diagonalRight[row] = stateManager.state[row][column]
                }
            }
        }

        val hasDetectedLeft = diagonalLeft.all { player -> player == currentPlayer }
        if (hasDetectedLeft) {
            for(r in stateManager.picture.indices){
                for(c in stateManager.picture[r].indices){
                    if(c == r){
                        stateManager.picture[r][c] = Type.DIAGONAL_LEFT
                    }
                }
            }
            return true
        }
        val hasDetectedRight = diagonalRight.all { player -> player == currentPlayer }
        if (hasDetectedRight) {
            for(r in stateManager.picture.indices){
                for(c in stateManager.picture[r].indices){
                    if((stateManager.picture[r].lastIndex - c) == r){
                        stateManager.picture[r][c] = Type.DIAGONAL_RIGHT
                    }
                }
            }
            return true
        }

        return false
    }

    private fun rotation(state: Array<IntArray>): Array<IntArray>{

        val stateRotated = StateManager(row, column).state

        for (row in state.indices) {
            for (column in state[row].indices) {
                stateRotated[column][stateRotated.lastIndex - row] = state[row][column]
            }
        }
        return stateRotated
    }

    fun reset() {
        stateManager.reset()
        currentPlayer = 1
    }

    fun play(coordinate: Coordinate): Boolean {
        val (x, y) = coordinate
        if (stateManager.state[x][y] == 0){
            stateManager.state[x][y] = currentPlayer

            println("play: " + Gson().toJson(stateManager.state))
            return true
        } else {

            println("play: " + Gson().toJson(stateManager.state))
            return false
        }
    }

    fun isInProgress(): Boolean{
        return stateManager.state.any { row -> row.any { column -> column == 0 } }
    }
}