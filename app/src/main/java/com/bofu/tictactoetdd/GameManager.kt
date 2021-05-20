package com.bofu.tictactoetdd

class GameManager(val row: Int, val column: Int) {

    var currentPlayer = 1

    val currentPlayerMark: String
        get() = if (currentPlayer == 1) "X" else "O"

    var stateManager = StateManager(row, column)

    fun alternatePlayer() {
        currentPlayer = 3 - currentPlayer
    }

    fun detectWinCase(): RedLine? {

        // detect row
        for (row in stateManager.state.indices) {
            val hasDetected = stateManager.state[row].all { player -> player == currentPlayer }
            if (hasDetected) return RedLine.values()[row]
        }

        // detect column
        val stateRotated = rotation(stateManager.state)
        for (row in stateRotated.indices) {
            val hasDetected = stateRotated[row].all { player -> player == currentPlayer }
            if (hasDetected) return RedLine.values()[row + 3]
        }

        // detect diagonal left and right
        val diagonalLeft = IntArray(column)
        val diagonalRight = IntArray(column)
        for (row in stateManager.state.indices) {
            for (column in stateManager.state[row].indices) {
                if (row == column){ diagonalLeft[row] = stateManager.state[row][column] }
                if (row == (stateManager.state[row].lastIndex - column)){ diagonalRight[row] = stateManager.state[row][column] }
            }
        }

        val hasDetectedLeft = diagonalLeft.all { player -> player == currentPlayer }
        if (hasDetectedLeft) return RedLine.DIAGONAL_LEFT
        val hasDetectedRight = diagonalRight.all { player -> player == currentPlayer }
        if (hasDetectedRight) return RedLine.DIAGONAL_RIGHT

        return null
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
    }

    fun play(coordinate: Coordinate): RedLine? {

        val (x, y) = coordinate
        stateManager.state[x][y] = currentPlayer
        val redLine = detectWinCase()
        if(redLine == null){
            alternatePlayer()
        }
        return redLine
    }

    fun isInProgress(): Boolean{
        return stateManager.state.any { row -> row.any { column -> column == 0 } }
    }
}
