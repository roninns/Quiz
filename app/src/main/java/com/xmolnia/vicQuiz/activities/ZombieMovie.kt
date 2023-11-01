package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.graphics.Color
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
import com.xmolnia.vicQuiz.databinding.ActivityZombieMovieBinding
import com.xmolnia.vicQuiz.models.Question

class ZombieMovie : BaseActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private lateinit var zombieTest: ActivityZombieMovieBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zombieTest = ActivityZombieMovieBinding.inflate(layoutInflater)
        setContentView(zombieTest.root)

        /*Audio manager*/
        song = MediaPlayer.create(this, R.raw.background)
        song.isLooping = true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getZombieQuestion()
        zombieTest.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        zombieTest.zmoptionOne.setOnClickListener(this)
        zombieTest.zmoptionTwo.setOnClickListener(this)
        zombieTest.zmoptionThree.setOnClickListener(this)
        zombieTest.zmoptionFour.setOnClickListener(this)

    }


    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion() {
        zombieTest.zmoptionOne.isClickable = true
        zombieTest.zmoptionTwo.isClickable = true
        zombieTest.zmoptionThree.isClickable = true
        zombieTest.zmoptionFour.isClickable = true
        zombieTest.zmbtnSubmit.isClickable = false
        zombieTest.zmbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()



        zombieTest.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        zombieTest.tvProgress.text = temp
        zombieTest.zmbtnSubmit.text = getString(R.string.submit)

        zombieTest.ivImage.setImageResource(question.image)
        zombieTest.zmoptionOne.text = getString(question.optionOne)
        zombieTest.zmoptionTwo.text = getString(question.optionTwo)
        zombieTest.zmoptionThree.text = getString(question.optionThree)
        zombieTest.zmoptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, zombieTest.zmoptionOne)
        options.add(1, zombieTest.zmoptionTwo)
        options.add(2, zombieTest.zmoptionThree)
        options.add(3, zombieTest.zmoptionFour)

        for (option in options) {
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.zmoption_one -> {
                selectedOptionView(zombieTest.zmoptionOne, 1); btnSubmit()
            }
            R.id.zmoption_two -> {
                selectedOptionView(zombieTest.zmoptionTwo, 2); btnSubmit()
            }
            R.id.zmoption_three -> {
                selectedOptionView(zombieTest.zmoptionThree, 3); btnSubmit()
            }
            R.id.zmoption_four -> {
                selectedOptionView(zombieTest.zmoptionFour, 4);btnSubmit()
            }


            R.id.zmbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val zombiesMovieResult = "$mCorrectAnswers" + "/" + mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(ZOMBIE_TEST_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()

                            intent.putExtra("results", zombiesMovieResult)
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
                    zombieTest.zmoptionOne.isClickable = false
                    zombieTest.zmoptionTwo.isClickable = false
                    zombieTest.zmoptionThree.isClickable = false
                    zombieTest.zmoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        zombieTest.zmbtnSubmit.text = getString(R.string.finish)
                    } else {
                        zombieTest.zmbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                zombieTest.zmoptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                zombieTest.zmoptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                zombieTest.zmoptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                zombieTest.zmoptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }


    private fun selectedOptionView(tv: Button, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#CF00FD"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }


    private fun btnSubmit() {
        zombieTest.zmbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        zombieTest.zmbtnSubmit.setOnClickListener(this)

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