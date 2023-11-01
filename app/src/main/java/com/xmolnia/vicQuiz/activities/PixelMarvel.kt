package com.xmolnia.vicQuiz.activities

import android.annotation.SuppressLint
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
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.PixelMarvelBinding
import com.xmolnia.vicQuiz.models.Question

class PixelMarvel : BaseActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private lateinit var marvelpixel: PixelMarvelBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        marvelpixel = PixelMarvelBinding.inflate(layoutInflater)
        setContentView(marvelpixel.root)


        /*Audio manager*/
        song = MediaPlayer.create(this, R.raw.background)
        song.isLooping = true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getMarvelPixelQuestion()
        marvelpixel.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        marvelpixel.mpoptionOne.setOnClickListener(this)
        marvelpixel.mpoptionTwo.setOnClickListener(this)
        marvelpixel.mpoptionThree.setOnClickListener(this)
        marvelpixel.mpoptionFour.setOnClickListener(this)
    }


    /*............управление кликами...............*/
    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        marvelpixel.mpoptionOne.isClickable = true
        marvelpixel.mpoptionTwo.isClickable = true
        marvelpixel.mpoptionThree.isClickable = true
        marvelpixel.mpoptionFour.isClickable = true
        marvelpixel.mpbtnSubmit.isClickable = false
        marvelpixel.replace.visibility = View.GONE
        marvelpixel.mpbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()


        /*........момент присвоений ресурсов..........*/
        marvelpixel.progressBar.progress = mCurrentPosition
        marvelpixel.tvProgress.text = "$mCurrentPosition" + "/" + mQuestionList?.size.toString()
        marvelpixel.mpbtnSubmit.text = getString(R.string.submit)
        marvelpixel.ivImage.setImageResource(question.image)
        marvelpixel.mpoptionOne.text = getString(question.optionOne)
        marvelpixel.mpoptionTwo.text = getString(question.optionTwo)
        marvelpixel.mpoptionThree.text = getString(question.optionThree)
        marvelpixel.mpoptionFour.text = getString(question.optionFour)


        /*показывает вторую картинку, а так же анимацию слайда*/
        var replace = true
        marvelpixel.replace.setOnClickListener {
            replace = when (replace) {

                true -> {
                    marvelpixel.ivImage.setImageResource(question.image)
                    slideAnimation()
                    false
                }

                false -> {
                    slideAnimation()
                    marvelpixel.ivImage.setImageResource(question.imageShow)
                    true
                }
            }
        }
    }


    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, marvelpixel.mpoptionOne)
        options.add(1, marvelpixel.mpoptionTwo)
        options.add(2, marvelpixel.mpoptionThree)
        options.add(3, marvelpixel.mpoptionFour)

        for (option in options) {
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mpoption_one -> {
                selectedOptionView(marvelpixel.mpoptionOne, 1); btnSubmit()
            }

            R.id.mpoption_two -> {
                selectedOptionView(marvelpixel.mpoptionTwo, 2); btnSubmit()
            }

            R.id.mpoption_three -> {
                selectedOptionView(marvelpixel.mpoptionThree, 3); btnSubmit()
            }

            R.id.mpoption_four -> {
                selectedOptionView(marvelpixel.mpoptionFour, 4);btnSubmit()
            }


            R.id.mpbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val marvelPixelResult = "$mCorrectAnswers" + "/" + mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(MARVEL_PIXEL_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()
                            intent.putExtra("results", marvelPixelResult)
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
                    else {
                        soundPlayCorrect(this)

                        answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer, R.drawable.main_button_rigth_answer)
                    imageShow()
                    marvelpixel.mpoptionOne.isClickable = false
                    marvelpixel.mpoptionTwo.isClickable = false
                    marvelpixel.mpoptionThree.isClickable = false
                    marvelpixel.mpoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        marvelpixel.mpbtnSubmit.text = getString(R.string.finish)
                    }
                    else {
                        marvelpixel.mpbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                marvelpixel.mpoptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                marvelpixel.mpoptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                marvelpixel.mpoptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                marvelpixel.mpoptionFour.background = ContextCompat.getDrawable(this, drawableView)
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
        marvelpixel.mpbtnSubmit.setOnClickListener(this)
        marvelpixel.mpbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))


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

    private fun imageShow() {
        val question = mQuestionList!![mCurrentPosition - 1]
        marvelpixel.ivImage.setImageResource(question.imageShow)
        slideAnimation()
        marvelpixel.replace.visibility = View.VISIBLE


    }

    /* анимация при изминений изоброжений*/
    private fun slideAnimation() {
        //Bounce
        /*Fade Rollin Zoom Wobble Wave Flash
        FlipInY Hinge Landing Pulse RotateIn Shake
        RubberBand TakingOff DropOut Tada Swing StandUp*/
        YoYo.with(Techniques.FadeIn).duration(800).repeat(0).playOn(marvelpixel.ivImage)

    }


}