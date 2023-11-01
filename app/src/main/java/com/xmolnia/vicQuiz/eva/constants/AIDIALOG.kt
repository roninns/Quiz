package com.xmolnia.vicQuiz.eva.constants

import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.R

/**
 * Created by Osman Boy on 12.07.2021.
 **/
object AIDIALOG {

    var a = -1

    fun messageGenerator(getMSG: String,): String {
        return when (getMSG) {
            "Привет" -> {
                arrayListOf("Hello.", "Привет.", "Салам.", "Hi.").random()
            }
            "Можно вопрос?"   -> {
                arrayListOf("Конечно можно.", "Да,можно.", "Задавай").random()
            }
            "FILM"-> {
                "<b>"+ "John Wick"+"</b>"+"Is very cool movie. From Usa "
            }

            "Давай с начала?"->{
                arrayListOf("Ну ок.","давай.","ладно.").random()
            }

            "Ты ошиблась"->{
                arrayListOf("Не ошиблась.","Правда?","Ясно.").random()
            }





            else -> {
                BaseActivity.getAppResources().getString(R.string.movie_details)

            }
        }
    }
}