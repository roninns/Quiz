package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.DisneyLandBinding
import com.xmolnia.vicQuiz.models.Question

class DisneyLand : BaseActivity(),View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var disneyLand: DisneyLandBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disneyLand= DisneyLandBinding.inflate(layoutInflater)
        setContentView(disneyLand.root)

        /*Audio manager*/
        song= MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()



        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getDisneyQuestion()
        disneyLand.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        disneyLand.dl1optionOne.setOnClickListener(this)
        disneyLand.dl1optionTwo.setOnClickListener(this)
        disneyLand.dl1optionThree.setOnClickListener(this)
        disneyLand.dl1optionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        disneyLand.dl1optionOne.isClickable= true
        disneyLand.dl1optionTwo.isClickable= true
        disneyLand.dl1optionThree.isClickable=true
        disneyLand.dl1optionFour.isClickable=true
        disneyLand.dl1btnSubmit.isClickable = false
        disneyLand.dl1btnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))




        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()




        disneyLand.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        disneyLand.tvProgress.text = temp
        disneyLand.dl1btnSubmit.text = getString(R.string.submit)
        disneyLand.ivImage.setImageResource(question.image)
        disneyLand.dl1optionOne.text = getString(question.optionOne)
        disneyLand.dl1optionTwo.text = getString(question.optionTwo)
        disneyLand.dl1optionThree.text =getString(question.optionThree)
        disneyLand.dl1optionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,disneyLand.dl1optionOne)
        options.add(1,disneyLand.dl1optionTwo)
        options.add(2,disneyLand.dl1optionThree)
        options.add(3,disneyLand.dl1optionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))
            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/







    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.dl1option_one -> { selectedOptionView(disneyLand.dl1optionOne, 1);  btnSubmit() }
            R.id.dl1option_two -> { selectedOptionView(disneyLand.dl1optionTwo, 2); btnSubmit()}
            R.id.dl1option_three ->{selectedOptionView(disneyLand.dl1optionThree, 3) ; btnSubmit()}
            R.id.dl1option_four ->{ selectedOptionView(disneyLand.dl1optionFour, 4);btnSubmit()}


            R.id.dl1btn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val disneyLandResult = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()
                            /*shared_preferences*/

                            Base.putAnswer(DISNEYLAND_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()

                            intent.putExtra("results",disneyLandResult)

                            startActivity(intent)
                            finish()
                        }
                    }
                }


                /*...........его else....................*/
                else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        soundPlayWrong(this)


                        answerView(mSelectedOptionPosition, R.drawable.main_button_wrong_answer)


                    }
                    else{
                        soundPlayCorrect(this)
                        answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                        mCorrectAnswers++

                    }

                    answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                    disneyLand.dl1optionOne.isClickable = false
                    disneyLand.dl1optionTwo.isClickable = false
                    disneyLand.dl1optionThree.isClickable = false
                    disneyLand.dl1optionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        disneyLand.dl1btnSubmit.text= getString(R.string.finish)
                    } else {
                        disneyLand.dl1btnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                disneyLand.dl1optionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                disneyLand.dl1optionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                disneyLand.dl1optionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                disneyLand.dl1optionFour.background  = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }





    private fun selectedOptionView(tv: Button, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(getColor(R.color.colorPrimary))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }



    private fun btnSubmit(){
        disneyLand.dl1btnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        disneyLand.dl1btnSubmit.setOnClickListener(this)

    }



    fun backClick(view: View){
        val intents = Intent(applicationContext, MainActivity::class.java)
        startActivity(intents)
        finish()
    }



    override fun onPause() {
        super.onPause()
        song.pause()

    }


    override fun onRestart() {
        super.onRestart()
        song.start()
    }


}