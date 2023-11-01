package com.xmolnia.vicQuiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.xmolnia.vicQuiz.R

/**
 * Created by Osman Boy on 07.04.2021.
 **/
class CustomListViewAdapter(val context: Context, private val Title : ArrayList<String>, private val Description : ArrayList<String> ):
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
        val view = LayoutInflater.from(context).inflate(R.layout.activity_listview,parent,false)

        val title : TextView = view.findViewById(R.id.textView)
        val description : TextView = view.findViewById(R.id.icon)
        title.text = Title[position]
        description.text = Description[position]

        return view

    }


}



