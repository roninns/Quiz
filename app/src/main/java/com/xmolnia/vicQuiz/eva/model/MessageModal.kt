package com.xmolnia.vicQuiz.eva.model

import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 11.07.2021.
 **/
data class MessageModal(
    var message: String,
    var sender: String,
    var responseType: Int = 0,
    val movieLoader: MovieLoader?= null,
)