package com.bofu.tictactoetdd.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val row: Int, private val column: Int, private val application: Application) : ViewModelProvider.AndroidViewModelFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(row, column, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}