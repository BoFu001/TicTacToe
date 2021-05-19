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
}