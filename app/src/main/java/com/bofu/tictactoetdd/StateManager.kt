package com.bofu.tictactoetdd

class StateManager(val row: Int, val column: Int){

    var state: Array<IntArray> = Array(row) { IntArray(column) }
        private set

    fun reset(){
        state = Array(row) { IntArray(column) }
    }

}