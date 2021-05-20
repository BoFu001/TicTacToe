package com.bofu.tictactoetdd

import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

class GameManagerTest {

    private lateinit var gameManager: GameManager
    private val row = 3
    private val column = 3

    @Before
    fun initializeGameManager(){
        gameManager = GameManager(row,column)
    }

    @Test
    fun gameManagerTest(){
        Truth.assertThat(gameManager.row).isEqualTo(row)
        Truth.assertThat(gameManager.column).isEqualTo(column)
    }

    @Test
    fun initialPlayerTest(){
        val currentPlayer = gameManager.currentPlayer
        Truth.assertThat(currentPlayer).isEqualTo(1)
    }

    @Test
    fun initialPlayerMarkTest(){
        val currentPlayerMark = gameManager.currentPlayerMark
        Truth.assertThat(currentPlayerMark).isEqualTo("X")
    }

    @Test
    fun playerMarkInProgressTest(){
        Truth.assertThat(gameManager.currentPlayerMark).isEqualTo("X")
        gameManager.alternatePlayer()
        Truth.assertThat(gameManager.currentPlayerMark).isEqualTo("O")
        gameManager.alternatePlayer()
        Truth.assertThat(gameManager.currentPlayerMark).isEqualTo("X")
    }

    @Test
    fun alternatePlayerTest(){
        Truth.assertThat(gameManager.currentPlayer).isEqualTo(1)
        gameManager.alternatePlayer()
        Truth.assertThat(gameManager.currentPlayer).isEqualTo(2)
        gameManager.alternatePlayer()
        Truth.assertThat(gameManager.currentPlayer).isEqualTo(1)
    }

    @Test
    fun gameStateManagerTest(){
        val state = gameManager.stateManager.state
        val bool = state.all { row -> row.all { column -> column == 0 }}
        Truth.assertThat(bool).isEqualTo(true)
    }

    @Test
    fun resetTest(){
        gameManager.reset()
        val state = gameManager.stateManager.state
        state.forEach{row -> row
            row.forEach { column -> column
                Truth.assertThat(column).isEqualTo(0)
            }
        }
    }

    @Test
    fun detectHorizontalWinCaseTest(){
        for (column in gameManager.stateManager.state[0].indices){
            gameManager.stateManager.state[0][column] = gameManager.currentPlayer
        }
        val case1 = gameManager.detectWinCase()
        Truth.assertThat(case1).isEqualTo(RedLine.HORIZONTAL_UP)

        gameManager.reset()

        for (column in gameManager.stateManager.state[1].indices){
            gameManager.stateManager.state[1][column] = gameManager.currentPlayer
        }
        val case2 = gameManager.detectWinCase()
        Truth.assertThat(case2).isEqualTo(RedLine.HORIZONTAL_MIDDLE)

        gameManager.reset()

        for (column in gameManager.stateManager.state[2].indices){
            gameManager.stateManager.state[2][column] = gameManager.currentPlayer
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.HORIZONTAL_DOWN)
    }

    @Test
    fun detectVerticalWinCaseTest(){
        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                if (column == 0){
                    gameManager.stateManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case1 = gameManager.detectWinCase()
        Truth.assertThat(case1).isEqualTo(RedLine.VERTICAL_LEFT)

        gameManager.reset()

        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                if (column == 1){
                    gameManager.stateManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case2 = gameManager.detectWinCase()
        Truth.assertThat(case2).isEqualTo(RedLine.VERTICAL_MIDDLE)

        gameManager.reset()

        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                if (column == 2){
                    gameManager.stateManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.VERTICAL_RIGHT)

    }

    @Test
    fun detectLeftDiagonalWinCaseTest(){
        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                if (column == row){
                    gameManager.stateManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.DIAGONAL_LEFT)
    }

    @Test
    fun detectRightDiagonalWinCaseTest(){
        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                if (row == gameManager.stateManager.state[row].lastIndex - column){
                    gameManager.stateManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case4 = gameManager.detectWinCase()
        Truth.assertThat(case4).isEqualTo(RedLine.DIAGONAL_RIGHT)
    }

    @Test
    fun playTest(){
        val coordinate1 = Coordinate(0,0)
        val detectedLine1 = gameManager.play(coordinate1)

        val sign1 = gameManager.stateManager.state[coordinate1.x][coordinate1.y]
        if (detectedLine1 != null){
            Truth.assertThat(sign1).isEqualTo(gameManager.currentPlayer)
        }


        val coordinate2 = Coordinate(1,1)
        val detectedLine2 = gameManager.play(coordinate2)

        val sign2 = gameManager.stateManager.state[coordinate2.x][coordinate2.y]
        if (detectedLine2 != null) {
            Truth.assertThat(sign2).isEqualTo(gameManager.currentPlayer)
        }
    }

    @Test
    fun isInProgressTest(){

        val isInProgress1 = gameManager.isInProgress()
        Truth.assertThat(isInProgress1).isEqualTo(true)


        for (row in gameManager.stateManager.state.indices){
            for(column in gameManager.stateManager.state[row].indices){
                gameManager.stateManager.state[row][column] = 1
            }
        }
        val isInProgress2 = gameManager.isInProgress()
        Truth.assertThat(isInProgress2).isEqualTo(false)

        gameManager.stateManager.state[0][0] = 0
        val isInProgress3 = gameManager.isInProgress()
        Truth.assertThat(isInProgress3).isEqualTo(true)
    }
}