package com.bofu.tictactoetdd

import com.google.common.truth.Truth
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
    fun detectWinCaseTest(){
        val state1 = gameManager.state
        for (column in state1[0].indices){
            state1[0][column] = gameManager.currentPlayer
        }
        val case1 = gameManager.detectWinCase(state1)
        Truth.assertThat(case1).isEqualTo("Horizontal")

        gameManager.reset()

        val state2 = gameManager.state
        for (row in state2.indices){
            for(column in state2[row]){
                if (column == 0){
                    state2[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case2 = gameManager.detectWinCase(state2)
        Truth.assertThat(case2).isEqualTo("vertical")

        gameManager.reset()

        val state3 = gameManager.state
        for (row in state3.indices){
            for(column in state3[row]){
                if (column == row){
                    state2[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case3 = gameManager.detectWinCase(state3)
        Truth.assertThat(case3).isEqualTo("diagonalLeft")

        gameManager.reset()

        val state4 = gameManager.state
        for (row in state4.indices){
            for(column in state4[row]){
                if (column == state4[row].lastIndex - column){
                    state4[row][column] = gameManager.currentPlayer
                }
            }
        }
        val case4 = gameManager.detectWinCase(state4)
        Truth.assertThat(case4).isEqualTo("diagonalRight")
    }
}