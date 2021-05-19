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
    fun detectHorizontalWinCaseTest(){
        for (column in gameManager.state[0].indices){
            gameManager.state[0][column] = gameManager.currentPlayer
        }
        val case1 = gameManager.detectWinCase()
        Truth.assertThat(case1).isEqualTo("Horizontal")

        //gameManager.reset()
    }

    @Test
    fun detectVerticalWinCaseTest(){
        for (row in gameManager.state.indices){
            for(column in gameManager.state[row]){
                if (column == 0){
                    gameManager.state[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case2 = gameManager.detectWinCase()
        Truth.assertThat(case2).isEqualTo("Vertical")

        //gameManager.reset()
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
        Truth.assertThat(case3).isEqualTo("DiagonalLeft")
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
        Truth.assertThat(case4).isEqualTo("DiagonalRight")
    }

}