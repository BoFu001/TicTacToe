package com.bofu.tictactoetdd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bofu.tictactoetdd.Mark
import com.bofu.tictactoetdd.R
import com.bofu.tictactoetdd.Type
import kotlinx.android.synthetic.main.row_main.view.*

class MainAdapter(val column: Int, var item: ArrayList<Int>, var picture: ArrayList<Int>, val onClickListener: (Int) -> Unit): RecyclerView.Adapter<MainAdapter.MainHolder>() {


    var isClickable = true

    fun update(newData: ArrayList<Int>, newPicture: ArrayList<Int>) {
        item.clear()
        item.addAll(newData)
        picture.clear()
        picture.addAll(newPicture)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_main,parent,false)
        val size = parent.measuredWidth / column
        view.layoutParams.width = size
        view.layoutParams.height = size
        return MainHolder(view)
    }

    override fun getItemCount() =  item.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        when(item[position]){
            0 -> holder.mark.text = Mark.EMPTY.toString()
            1 -> holder.mark.text = Mark.X.toString()
            2 -> holder.mark.text = Mark.O.toString()
        }

        holder.background.setImageDrawable(null)
        when(picture[position]){
            Type.HORIZONTAL -> holder.background.setImageResource(R.drawable.ic_horizontal)
            Type.VERTICAL -> { holder.background.setImageResource(R.drawable.ic_vertical) }
            Type.DIAGONAL_LEFT -> holder.background.setImageResource(R.drawable.ic_diagonal_left)
            Type.DIAGONAL_RIGHT -> holder.background.setImageResource(R.drawable.ic_diagonal_right)
            else -> holder.background.setBackgroundResource(R.color.white)
        }

        holder.mask.setOnClickListener {
            if (isClickable){
                onClickListener(position)
            }
        }
    }

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mark: TextView = itemView.row_main_mark
        var background: ImageView = itemView.row_main_background
        var mask: ImageView = itemView.row_main_mask
    }
}
