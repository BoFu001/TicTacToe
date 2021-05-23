package com.bofu.tictactoetdd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bofu.tictactoetdd.GameManager
import com.bofu.tictactoetdd.models.Coordinate


class MainViewModel(row: Int, column: Int) : ViewModel() {

    private var gameManager: GameManager = GameManager(row, column)
    val liveData = MutableLiveData<ArrayList<Int>>()
    val showNewGameBtn = MutableLiveData(false)
    val message = MutableLiveData("")
    val linePicture = MutableLiveData<ArrayList<Int>>()

    init {
        synchronizeData()
    }

    private fun synchronizeData(){
        val state = gameManager.stateManager.state
        val picture = gameManager.stateManager.picture

        liveData.value = transformData(state)
        linePicture.value = transformData(picture)
    }

    fun transformData(state: Array<IntArray>): ArrayList<Int>{
        val data = ArrayList<Int>()
        state.forEach { r ->
            r.forEach { c ->
                data.add(c)
            }
        }
        return data
    }

    private fun getCoordinate(position: Int): Coordinate {
        val row = gameManager.row
        val column = gameManager.column
        val x = position / row
        val y = position % column
        return Coordinate(x,y)
    }

    fun play(position: Int){
        val coordinate = getCoordinate(position)
        if(gameManager.play(coordinate)){
            synchronizeData()
            val detected = gameManager.detectWinCase()
            if(detected){
                linePicture.value = transformData(gameManager.stateManager.picture)
                message.value = "${gameManager.currentPlayerMark} Win!"
                showNewGameBtn.value = true
            } else {
                gameManager.alternatePlayer()
                if(!gameManager.isInProgress()) {
                    message.value = "Draw!"
                    showNewGameBtn.value = true
                }
            }
        }
    }

    fun reset(){
        gameManager.reset()
        synchronizeData()
        message.value = ""
        showNewGameBtn.value = false
    }
}