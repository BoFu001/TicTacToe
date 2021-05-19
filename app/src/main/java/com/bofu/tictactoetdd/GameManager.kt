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

    fun alternatePlayer() {
        currentPlayer = 3 - currentPlayer
    }

    fun detectWinCase(): String? {

        // detect row
        for (row in state.indices) {
            val hasWon = state[row].all { player -> player == currentPlayer }
            if (hasWon) return "Horizontal"
        }

        // detect column
        val stateRotated = rotation(state)
        for (row in stateRotated.indices) {
            val hasWon = stateRotated[row].all { player -> player == currentPlayer }
            if (hasWon) return "Vertical"
        }

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
}
