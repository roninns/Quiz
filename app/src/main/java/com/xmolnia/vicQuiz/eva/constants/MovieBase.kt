package com.xmolnia.vicQuiz.eva.constants

import com.xmolnia.vicQuiz.MOVIE_PUB_TRON
import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 11.08.2021.
 **/
class MovieBase private constructor() {

    private val movieData = arrayListOf<MovieLoader>()

    companion object {

        private var db: MovieBase? = null

        fun getInstance(): MovieBase {
            db?.let { return it }
            val instance = MovieBase()
            db= instance
            return instance
        }
    }

    fun inputData(array:ArrayList<MovieLoader>){
        movieData.addAll(array)
    }

    fun getData():ArrayList<MovieLoader>{
        return movieData
    }

    fun getDataRandom():MovieLoader{
        val sortedArray = movieData.filter { it.moviePublicKey == MOVIE_PUB_TRON }

        return sortedArray.random()

    }


    fun clearData(){
        movieData.clear()
    }
}