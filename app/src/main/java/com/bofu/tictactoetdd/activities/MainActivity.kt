package com.bofu.tictactoetdd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bofu.tictactoetdd.viewModels.MainViewModel
import com.bofu.tictactoetdd.viewModels.MainViewModelFactory
import com.bofu.tictactoetdd.R
import com.bofu.tictactoetdd.adapters.MainAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var row = 4
    private var column = 4
    private lateinit var mainViewModel: MainViewModel
    private var mainAdapter = MainAdapter(column, ArrayList(), ArrayList(), this::play)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModelSetup()
        mainRecyclerViewSetup()
        btnSetup()
    }


    private fun mainViewModelSetup(){
        val viewModelFactory = MainViewModelFactory(row,column)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.liveData.observe(this, {
            mainAdaptorUpdate()
        })
        mainViewModel.showNewGameBtn.observe(this, {
            showNewGameBtn(it)
        })
        mainViewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mainViewModel.linePicture.observe(this, {
            println("picture: " + Gson().toJson(it))
            mainAdaptorUpdate()
        })
    }

    private fun mainAdaptorUpdate(){
        mainAdapter.update(mainViewModel.liveData.value!!, mainViewModel.linePicture.value!!)
    }

    private fun mainRecyclerViewSetup(){
        main_recycler_view.apply {
            layoutManager = GridLayoutManager(context, column)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    private fun play(position: Int){
        mainViewModel.play(position)
    }

    private fun btnSetup(){
        btn_new_game.setOnClickListener {
            showNewGameBtn(false)
            mainViewModel.reset()
        }
    }

    private fun showNewGameBtn(bool: Boolean) {
        when (bool){
            true -> {
                btn_new_game.visibility = View.VISIBLE
                mainAdapter.isClickable = false
            }
            false -> {
                btn_new_game.visibility = View.INVISIBLE
                mainAdapter.isClickable = true
            }
        }
    }
}