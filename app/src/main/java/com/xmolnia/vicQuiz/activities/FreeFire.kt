package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.FREE_FIRE_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityFreeFireBinding
import com.xmolnia.vicQuiz.models.Question
import com.xmolnia.vicQuiz.soundPlayCorrect
import com.xmolnia.vicQuiz.soundPlayWrong

class FreeFire : BaseActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private lateinit var freeFire: ActivityFreeFireBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MONSTER", "onCreate: ")
        freeFire = ActivityFreeFireBinding.inflate(layoutInflater)
        setContentView(freeFire.root)

        /*Audio manager*/
        song = MediaPlayer.create(this, R.raw.background)
        song.isLooping = true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getFreeFireQuestion()
        freeFire.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        freeFire.ffoptionOne.setOnClickListener(this)
        freeFire.ffoptionTwo.setOnClickListener(this)
        freeFire.ffoptionThree.setOnClickListener(this)
        freeFire.ffoptionFour.setOnClickListener(this)

    }


    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion() {
        freeFire.ffoptionOne.isClickable = true
        freeFire.ffoptionTwo.isClickable = true
        freeFire.ffoptionThree.isClickable = true
        freeFire.ffoptionFour.isClickable = true
        freeFire.ffbtnSubmit.isClickable = false
        freeFire.ffbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()




        freeFire.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition" + "/" + mQuestionList?.size.toString()
        freeFire.tvProgress.text = temp
        freeFire.ffbtnSubmit.text = getString(R.string.submit)
        freeFire.ivImage.setImageResource(question.image)
        freeFire.ffoptionOne.text = getString(question.optionOne)
        freeFire.ffoptionTwo.text = getString(question.optionTwo)
        freeFire.ffoptionThree.text = getString(question.optionThree)
        freeFire.ffoptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, freeFire.ffoptionOne)
        options.add(1, freeFire.ffoptionTwo)
        options.add(2, freeFire.ffoptionThree)
        options.add(3, freeFire.ffoptionFour)

        for (option in options) {
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
    /*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ffoption_one -> {
                selectedOptionView(freeFire.ffoptionOne, 1); btnSubmit()
            }

            R.id.ffoption_two -> {
                selectedOptionView(freeFire.ffoptionTwo, 2); btnSubmit()
            }

            R.id.ffoption_three -> {
                selectedOptionView(freeFire.ffoptionThree, 3); btnSubmit()
            }

            R.id.ffoption_four -> {
                selectedOptionView(freeFire.ffoptionFour, 4);btnSubmit()
            }


            R.id.ffbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val freeFireResult =
                                "$mCorrectAnswers" + "/" + mQuestionList?.size.toString()
                            /*shared_preferences*/
//                            Base.putValues(FREE_FIRE_KEY,freeFireResult)
                            /* под уникальным именем*/
                            Base.putAnswer(FREE_FIRE_CORRECT_ANSWERS, mCorrectAnswers)

                            Base.putRating()
                            intent.putExtra("results", freeFireResult)

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


                    } else {
                        soundPlayCorrect(this)
                        answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                        mCorrectAnswers++

                    }

                    answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                    freeFire.ffoptionOne.isClickable = false
                    freeFire.ffoptionTwo.isClickable = false
                    freeFire.ffoptionThree.isClickable = false
                    freeFire.ffoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        freeFire.ffbtnSubmit.text = getString(R.string.finish)
                    } else {
                        freeFire.ffbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                freeFire.ffoptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                freeFire.ffoptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                freeFire.ffoptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                freeFire.ffoptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }


    private fun selectedOptionView(tv: Button, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(getColor(R.color.colorPrimary))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }


    private fun btnSubmit() {
        freeFire.ffbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        freeFire.ffbtnSubmit.setOnClickListener(this)

    }


    fun backClick(view: View) {
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