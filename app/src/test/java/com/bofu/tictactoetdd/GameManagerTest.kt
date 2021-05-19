package com.bofu.tictactoetdd

import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

class GameManagerTest {

    private lateinit var gameManager: GameManager

    @Before
    fun initializeGameManager(){
        gameManager = GameManager()
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
    fun getGameState(){
        val state = gameManager.state
        state.forEach{row -> row
            row.forEach { column -> column
                Truth.assertThat(column).isEqualTo(0)
            }
        }
    }

    @Test
    fun resetTest(){
        gameManager.reset()
        val state = gameManager.state
        state.forEach{row -> row
            row.forEach { column -> column
                Truth.assertThat(column).isEqualTo(0)
            }
        }
    }

    @Test
    fun detectHorizontalWinCaseTest(){
        for (column in gameManager.state[0].indices){
            gameManager.state[0][column] = gameManager.currentPlayer
        }
        val case1 = gameManager.detectWinCase()
        Truth.assertThat(case1).isEqualTo(RedLine.HORIZONTAL_UP)

        gameManager.reset()

        for (column in gameManager.state[1].indices){
            gameManager.state[1][column] = gameManager.currentPlayer
        }
        val case2 = gameManager.detectWinCase()
        Truth.assertThat(case2).isEqualTo(RedLine.HORIZONTAL_MIDDLE)

        gameManager.reset()

        for (column in gameManager.state[2].indices){
            gameManager.state[2][column] = gameManager.currentPlayer
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.HORIZONTAL_DOWN)
    }

    @Test
    fun detectVerticalWinCaseTest(){
        for (row in gameManager.state.indices){
            for(column in gameManager.state[row].indices){
                if (column == 0){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case1 = gameManager.detectWinCase()
        Truth.assertThat(case1).isEqualTo(RedLine.VERTICAL_LEFT)

        gameManager.reset()

        for (row in gameManager.state.indices){
            for(column in gameManager.state[row].indices){
                if (column == 1){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case2 = gameManager.detectWinCase()
        Truth.assertThat(case2).isEqualTo(RedLine.VERTICAL_MIDDLE)

        gameManager.reset()

        for (row in gameManager.state.indices){
            for(column in gameManager.state[row].indices){
                if (column == 2){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.VERTICAL_RIGHT)

    }

    @Test
    fun detectLeftDiagonalWinCaseTest(){
        for (row in gameManager.state.indices){
            for(column in gameManager.state[row].indices){
                if (column == row){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case3 = gameManager.detectWinCase()
        Truth.assertThat(case3).isEqualTo(RedLine.DIAGONAL_LEFT)
    }

    @Test
    fun detectRightDiagonalWinCaseTest(){
        for (row in gameManager.state.indices){
            for(column in gameManager.state[row].indices){
                if (row == gameManager.state[row].lastIndex - column){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case4 = gameManager.detectWinCase()
        Truth.assertThat(case4).isEqualTo(RedLine.DIAGONAL_RIGHT)
    }

    @Test
    fun playTest(){
        val coordinate = Coordinate(0,0)
        gameManager.play(coordinate)

        val sign = gameManager.state[coordinate.x][coordinate.y]
        Truth.assertThat(sign).isEqualTo(gameManager.currentPlayer)

    }

}