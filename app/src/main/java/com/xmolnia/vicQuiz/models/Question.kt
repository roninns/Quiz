package com.xmolnia.vicQuiz.models

/**
 * Created by Osman Boy on 11.03.2021.
 **/
data class Question(
    val id:Int,
    val optionOne:Int,
    val optionTwo:Int,
    val optionThree:Int,
    val optionFour: Int,
    val correctAnswer: Int){



    var question:Int = 0
    var imageShow:Int = 0
    var image: Int = 0
    var imageURI:String = ""

    constructor(id:Int,
                image:Int,
                optionOne:Int,
                optionTwo:Int,
                optionThree:Int,
                optionFour: Int,
                correctAnswer: Int): this (id, optionOne, optionTwo, optionThree, optionFour, correctAnswer){
                    this.image = image

    }

    constructor(id:Int,
                question:Int,
                image:Int,
                optionOne:Int,
                optionTwo:Int,
                optionThree:Int,
                optionFour: Int,
                correctAnswer: Int): this(id, optionOne, optionTwo, optionThree, optionFour, correctAnswer){
        this.question =question
        this.image = image}

    constructor(id:Int,
                imageURI:String,
                optionOne:Int,
                optionTwo:Int,
                optionThree:Int,
                optionFour: Int,
                correctAnswer: Int): this(id, optionOne, optionTwo, optionThree, optionFour, correctAnswer){
        this.imageURI = imageURI
    }


    constructor(id:Int,
                question:Int,
                image:Int,
                imageShow: Int,
                optionOne:Int,
                optionTwo:Int,
                optionThree:Int,
                optionFour: Int,
                correctAnswer: Int): this(id, optionOne, optionTwo, optionThree, optionFour, correctAnswer){
                this.image = image
                this.question = question
                this.imageShow=imageShow

                }


}

class ImageU(
        val manGirl1:String,
        val manAnswer:String,
        val girlAnswer:String,
        val manGirl2: String,
)



