package com.xmolnia.vicQuiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.xmolnia.vicQuiz.R

/**
 * Created by Osman Boy on 28.05.2021.
 **/
class RatingAdapter(val context: Context, private val Title : ArrayList<String>, private val Description : ArrayList<String> ):
    BaseAdapter() {


    /*получаем количество итемов*/
    override fun getCount(): Int {
        return Title.size
    }


    /*получаем итем*/
    override fun getItem(position: Int): Any {
        return Title[position]
    }

    /*получаем id итемов*/
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.rating_listview,parent,false)

        val image : ImageView = view.findViewById(R.id.video)
        val name : TextView = view.findViewById(R.id.name)
        val rating : TextView = view.findViewById(R.id.rating)
        when(position+1){
            1-> image.setImageResource(R.drawable.ic_golden)
            2-> image.setImageResource(R.drawable.ic_silver)
            3-> image.setImageResource(R.drawable.ic_bronse)
            else->{
                image.setImageResource(R.drawable.ic_purple)
            }
        }
        name.text = Title[position]
        rating.text = Description[position]

        return view

    }


}