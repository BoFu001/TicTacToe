package com.bofu.tictactoetdd

class StateManager(private val row: Int, private val column: Int){

    var state: Array<IntArray> = Array(row) { IntArray(column) }
        private set

    var picture: Array<IntArray> = Array(row) { IntArray(column) }
        private set

    fun reset(){
        state = Array(row) { IntArray(column) }
        picture = Array(row) { IntArray(column) }
    }

}