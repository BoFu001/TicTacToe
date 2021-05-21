package com.bofu.tictactoetdd

enum class Mark(  // Assigning a value to each enum
    private val string: String
) {
    X("X"), O("O"), EMPTY("");

    // Overriding toString() method to return "" instead of "EMPTY"
    override fun toString(): String {
        return this.string
    }
}