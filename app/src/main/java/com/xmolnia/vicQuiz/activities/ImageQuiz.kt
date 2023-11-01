package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityImageQuizBinding
import com.xmolnia.vicQuiz.models.ImageU

class ImageQuiz : BaseActivity(), View.OnClickListener {
    private lateinit var imageQuiz: ActivityImageQuizBinding
    private var mQuestionList: ArrayList<ImageU>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCurrentPosition: Int = 1
    lateinit var song: MediaPlayer
    private var click: Int = 0
    private var height = 0
    private var mGirl = 0
    private var width = 0
    private var mMan = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageQuiz = ActivityImageQuizBinding.inflate(layoutInflater)
        setContentView(imageQuiz.root)
        Base.initial(this)

        height = imageQuiz.iqoptionOne.height
        width = imageQuiz.iqoptionOne.width


        /*Audio manager*/
        song = MediaPlayer.create(this, R.raw.background)
        song.isLooping = true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.imageQuestion()
        imageQuiz.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        imageQuiz.iqoptionOne.setOnClickListener(this)
        imageQuiz.iqoptionTwo.setOnClickListener(this)
        imageQuiz.iqoptionThree.setOnClickListener(this)
        imageQuiz.iqoptionFour.setOnClickListener(this)


    }


    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion() {

        imageQuiz.iqbtnSubmit.isClickable = false
        imageQuiz.iqbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))
        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()


        Glide.with(this).load(Constants.imageU + question.manGirl1)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(imageQuiz.iqoptionOne)

        Glide.with(this).load(Constants.imageU + question.manAnswer)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(imageQuiz.iqoptionTwo)

        Glide.with(this).load(Constants.imageU + question.girlAnswer)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(imageQuiz.iqoptionThree)

        Glide.with(this).load(Constants.imageU + question.manGirl2)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(imageQuiz.iqoptionFour)
       

        imageQuiz.iqbtnSubmit.text = getString(R.string.submit)
        imageQuiz.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition" + "/" + mQuestionList?.size.toString()
        imageQuiz.tvProgress.text = temp

    }


    private fun defaultOptionsView() {
        val options = ArrayList<ShapeableImageView>()

        options.add(0, imageQuiz.iqoptionOne)
        options.add(1, imageQuiz.iqoptionTwo)
        options.add(2, imageQuiz.iqoptionThree)
        options.add(3, imageQuiz.iqoptionFour)

        for (option in options) {

            option.isClickable = true
            option.layoutParams.height = height
            option.layoutParams.width = width


            option.strokeWidth = 3.5f
            option.requestLayout()
            option.strokeColor = ColorStateList.valueOf(getColor(R.color.colorPrimary))
        }
    }
/*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iqoption_one -> {
                selectedOptionView(imageQuiz.iqoptionOne, 1); btnSubmit(); }
            R.id.iqoption_two -> {
                selectedOptionView(imageQuiz.iqoptionTwo, 2); btnSubmit(); }
            R.id.iqoption_three -> {
                selectedOptionView(imageQuiz.iqoptionThree, 3); btnSubmit(); }
            R.id.iqoption_four -> {
                selectedOptionView(imageQuiz.iqoptionFour, 4);btnSubmit(); }

            R.id.iqbtn_submit -> {

                when (mSelectedOptionPosition) {
                    1 -> {
                        mMan += 1; mGirl += 1
                    }
                    2 -> {
                        mMan += 1
                    }
                    3 -> {
                        mGirl += 1
                    }
                    4 -> {
                        mMan += 1; mGirl += 1
                    }
                }
                /*..........первый блок if начало*........*/
                mCurrentPosition++
                when {
                    mCurrentPosition <= mQuestionList!!.size -> {
                        mSelectedOptionPosition = 0
                        setQuestion()
                    }

                    else -> {
                        val intent = Intent(this, ResultActivity::class.java)
                        var manGirl = ""
                        var answer = ""
                        when {
                            mMan > mGirl -> {
                                answer = getString(R.string.man_info)
                                manGirl = getString(R.string.man)
                                intent.putExtra("uri", Constants.manGif)

                            }
                            mMan < mGirl -> {
                                answer = getString(R.string.girl_info)
                                manGirl = getString(R.string.girl)
                                intent.putExtra("uri", Constants.girlGif)

                            }
                            mMan == mGirl -> {
                                answer = getString(R.string.wrong_answer)
                                manGirl = getString(R.string.wrong_pol)
                                intent.putExtra("uri", Constants.manGif)
                            }
                        }


                        /*shared_preferences*/
                        intent.putExtra("m/g", manGirl)
                        intent.putExtra("pol", answer)
                        startActivity(intent)
                        finish()
                    }
                }


                if (mCurrentPosition == mQuestionList!!.size) {
                    imageQuiz.iqbtnSubmit.text = getString(R.string.finish)
                }
            }
        }
    }


    private fun selectedOptionView(iv: ShapeableImageView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum



        if (click == 0) {
            iv.layoutParams.height = 10 + iv.height;iv.layoutParams.width =
                10 + iv.width; iv.requestLayout()
            iv.isClickable = false
        }



        iv.strokeWidth = 6f
        iv.strokeColor = ColorStateList.valueOf(Color.parseColor("#23B129"))

    }


    private fun btnSubmit() {
        imageQuiz.iqbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        imageQuiz.iqbtnSubmit.setOnClickListener(this)

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
