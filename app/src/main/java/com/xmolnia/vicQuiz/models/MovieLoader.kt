package com.xmolnia.vicQuiz.models

import java.io.Serializable

/**
 * Created by Osman Boy on 08.07.2021.
 **/
data class MovieLoader(

    //YouTube trailer or movie URL
    val youtubeURL: String? = null,
    //Telegram movie download URL
    val telegramURL: String? = null,
    val bannerURL: String? = null,
    val movieImage:String?=null,
    val movieName:String?=null,
    val moviePublicKey: Int?=null,
    val movieDescription: String? = null,
    val actors: ArrayList<Actor>? = null,
    val countriesName: String? = null,
    val releaseData: String? = null,
    val movieGenres: String? = null,
    val imdbRating: String? = null,
    val kinopoiskRating: String? = null,
    val ageRestriction: Int? = null,
    val movieTime:String? = null) : Serializable