package com.bofu.tictactoetdd

import com.google.gson.Gson

class GameManager {

    var currentPlayer = 1

    val currentPlayerMark: String
        get() = if (currentPlayer == 1) "X" else "O"

    var state = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun alternatePlayer() {
        currentPlayer = 3 - currentPlayer
    }

    fun detectWinCase(): RedLine? {

        // detect row
        for (row in state.indices) {
            val hasDetected = state[row].all { player -> player == currentPlayer }
            if (hasDetected) return RedLine.values()[row]
        }

        // detect column
        val stateRotated = rotation(state)
        for (row in stateRotated.indices) {
            val hasDetected = stateRotated[row].all { player -> player == currentPlayer }
            if (hasDetected) return RedLine.values()[row + 3]
        }

        // detect diagonal left and right
        val diagonalLeft = intArrayOf(0, 0, 0)
        val diagonalRight = intArrayOf(0, 0, 0)
        for (row in state.indices) {
            for (column in state[row].indices) {
                if (row == column){ diagonalLeft[row] = state[row][column] }
                if (row == (state[row].lastIndex - column)){ diagonalRight[row] = state[row][column] }
            }
        }

        val hasDetectedLeft = diagonalLeft.all { player -> player == currentPlayer }
        if (hasDetectedLeft) return RedLine.DIAGONAL_LEFT
        val hasDetectedRight = diagonalRight.all { player -> player == currentPlayer }
        if (hasDetectedRight) return RedLine.DIAGONAL_RIGHT

        return null
    }

    private fun rotation(state: Array<IntArray>): Array<IntArray>{
        val stateRotated = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )

        for (row in state.indices) {
            for (column in state[row].indices) {
                stateRotated[column][stateRotated.lastIndex - row] = state[row][column]
            }
        }
        return stateRotated
    }

    fun reset() {
        state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
    }

    fun play(coordinate: Coordinate): RedLine? {

        val (x, y) = coordinate
        state[x][y] = currentPlayer
        val redLine = detectWinCase()
        if(redLine == null){
            alternatePlayer()
        }
        return redLine
    }

    fun isInProgress(): Boolean{
        println("kkk: " + state.any { row -> row.any { column -> column == 0 } })
        return state.any { row -> row.any { column -> column == 0 } }
    }
}
