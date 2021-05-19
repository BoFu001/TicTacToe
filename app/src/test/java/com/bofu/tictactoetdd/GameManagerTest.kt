package com.bofu.tictactoetdd

import com.google.common.truth.Truth
import org.junit.Test

class GameManagerTest {

    @Test
    fun initialPlayerTest(){
        val gameManager = GameManager()
        val currentPlayer = gameManager.currentPlayer
        Truth.assertThat(currentPlayer).isEqualTo(1)
    }

    @Test
    fun initialPlayerMarkTest(){
        val gameManager = GameManager()
        val currentPlayerMark = gameManager.currentPlayerMark
        Truth.assertThat(currentPlayerMark).isEqualTo("X")
    }

    @Test
    fun PlayTest(){
        val gameManager = GameManager()
        gameManager.play()
        val currentPlayerMark = gameManager.currentPlayerMark
        Truth.assertThat(currentPlayerMark).isEqualTo("O")
    }

    @Test
    fun getGameState(){
        val gameManager = GameManager()
        val state = gameManager.state
        state.forEach{row -> row
            row.forEach { column -> column
                Truth.assertThat(column).isEqualTo("O")
            }
        }
    }
}