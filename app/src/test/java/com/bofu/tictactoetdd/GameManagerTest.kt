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
    fun playGameTest(){
        val currentPlayerMark = gameManager.currentPlayerMark
        gameManager.play()
        Truth.assertThat(currentPlayerMark).isEqualTo("O")
        gameManager.play()
        Truth.assertThat(currentPlayerMark).isEqualTo("X")
        gameManager.play()
        Truth.assertThat(currentPlayerMark).isEqualTo("O")
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
}