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


}