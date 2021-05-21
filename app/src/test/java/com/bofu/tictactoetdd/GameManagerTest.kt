package com.bofu.tictactoetdd

import com.bofu.tictactoetdd.models.Coordinate
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

        val coordinate01 = Coordinate(0,0)
        val coordinate02 = Coordinate(0,1)
        val coordinate03 = Coordinate(0,2)
        gameManager.play(coordinate01)
        gameManager.play(coordinate02)
        gameManager.play(coordinate03)

        val detected = gameManager.detectWinCase()
        Truth.assertThat(detected).isEqualTo(true)

        val picture = gameManager.stateManager.picture
        val typeLine = picture[0].all { type -> type == Type.HORIZONTAL }
        Truth.assertThat(typeLine).isEqualTo(true)
    }

    @Test
    fun detectVerticalWinCaseTest(){

        val coordinate01 = Coordinate(0,0)
        val coordinate02 = Coordinate(1,0)
        val coordinate03 = Coordinate(2,0)
        gameManager.play(coordinate01)
        gameManager.play(coordinate02)
        gameManager.play(coordinate03)

        val detected = gameManager.detectWinCase()
        Truth.assertThat(detected).isEqualTo(true)

        val picture = gameManager.stateManager.picture
        val intArray = IntArray(column)
        for (row in picture.indices){
            for (column in picture[row].indices){
                if(column == 0){
                    intArray[row] = picture[row][column]
                }
            }
        }
        println(Gson().toJson(intArray))
        val typeLine = intArray.all { type -> type == Type.VERTICAL }
        Truth.assertThat(typeLine).isEqualTo(true)
    }

    @Test
    fun detectLeftDiagonalWinCaseTest(){

        val coordinate01 = Coordinate(0,0)
        val coordinate02 = Coordinate(1,1)
        val coordinate03 = Coordinate(2,2)
        gameManager.play(coordinate01)
        gameManager.play(coordinate02)
        gameManager.play(coordinate03)

        val detected = gameManager.detectWinCase()
        Truth.assertThat(detected).isEqualTo(true)

        val picture = gameManager.stateManager.picture
        val intArray = IntArray(column)

        for (row in picture.indices){
            for(column in picture[row].indices){
                if (column == row){
                    intArray[row]= picture[row][column]
                }
            }
        }
        val typeLine = intArray.all { type -> type == Type.DIAGONAL_LEFT }
        Truth.assertThat(typeLine).isEqualTo(true)
    }

    @Test
    fun detectRightDiagonalWinCaseTest(){

        val coordinate01 = Coordinate(0,2)
        val coordinate02 = Coordinate(1,1)
        val coordinate03 = Coordinate(2,0)
        gameManager.play(coordinate01)
        gameManager.play(coordinate02)
        gameManager.play(coordinate03)

        val detected = gameManager.detectWinCase()
        Truth.assertThat(detected).isEqualTo(true)

        val picture = gameManager.stateManager.picture
        val intArray = IntArray(column)

        for (row in picture.indices){
            for(column in picture[row].indices){
                if (row == picture[row].lastIndex - column){
                    intArray[row] = picture[row][column]
                }
            }
        }
        val typeLine = intArray.all { type -> type == Type.DIAGONAL_RIGHT }
        Truth.assertThat(typeLine).isEqualTo(true)
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