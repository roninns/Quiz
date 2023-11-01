package com.xmolnia.vicQuiz.models

/**
 * Created by Osman Boy on 09.04.2021.
 **/
data class User(
    var name: String = "",
    var marvelTest: Int = 0,
    var marvelMovie: Int = 0,
    var marvelPixel: Int = 0,
    var freeFire: Int = 0,
    var zombieMovie: Int = 0,
    var disneyLand: Int = 0,
    var partOne: Int = 0,
    var partTwo: Int = 0,
    var disneyLand2: Int = 0,
)

//@Keep
data class User2(
    var name: String,
    var marvelTest: Int,
    var marvelMovie: Int,
    var marvelPixel: Int,
    var freeFire: Int,
    var zombieMovie: Int,
    var disneyLand: Int,
    var partOne: Int,
    var partTwo: Int,
    var disneyLand2: Int,
)

data class Pair(var name: String = "", var rating: Int = 0)

data class All(var name: String = "", var rating: ArrayList<Int> = arrayListOf())


data class Recyclers(var title: String = "", var detail: String = "", var imageUri: String = "")



data class Ques(
    var image: String?=null,
    var question: String?=null,
    var optionOne: String?=null,
    var optionTwo: String?=null,
    var optionThree: String?=null,
    var optionFour: String?=null,
    var optionFive: String?=null
)


data class ReadQues(
    var image: String?=null,
    var question: String?=null,
    var optionOne: String?=null,
    var optionTwo: String?=null,
    var optionThree: String?=null,
    var optionFour: String?=null,
    var optionFive: String?=null
){
//    fun image(): String? {
//        return image
//    }
//
//    fun question(): String?{
//        return question
//    }
//    fun optionOne(): String?{
//        return optionOne
//    }
//
//    fun optionTwo(): String?{
//        return optionTwo
//    }
//    fun optionThree(): String?{
//        return optionThree
//    }
//    fun optionFour(): String?{
//        return optionFour
//    }
//    fun optionFive(): String?{
//        return optionFive
//    }


}

data class ResultText(
    var res1: String? = null,
    var res2: String? = null,
    var res3: String? = null,
    var res4: String? = null,
    var res5: String? = null,
    var res6: String? = null
)

data class ResultImage(
    var res1Image: String? = null,
    var res2Image: String? = null,
    var res3Image: String? = null,
    var res4Image: String? = null,
    var res5Image: String? = null,
    var res6Image: String? = null,
)
