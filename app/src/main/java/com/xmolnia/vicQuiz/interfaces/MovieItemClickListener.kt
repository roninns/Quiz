package com.xmolnia.vicQuiz.interfaces

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 19.07.2021.
 **/
interface MovieItemClickListener {

    fun onMovieClick( movie : MovieLoader, itemImage: CardView,itemName:TextView)
}