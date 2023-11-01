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
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityMarvelMovieBinding
import com.xmolnia.vicQuiz.models.Question


class MarvelMovie : BaseActivity(),View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var marvelmovie: ActivityMarvelMovieBinding
    lateinit var song: MediaPlayer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        marvelmovie= ActivityMarvelMovieBinding.inflate(layoutInflater)
        setContentView(marvelmovie.root)

        /*Audio manager*/
        song=MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getMarvelMovieQuestion()
        marvelmovie.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        marvelmovie.mmoptionOne.setOnClickListener(this)
        marvelmovie.mmoptionTwo.setOnClickListener(this)
        marvelmovie.mmoptionThree.setOnClickListener(this)
        marvelmovie.mmoptionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        marvelmovie.mmoptionOne.isClickable= true
        marvelmovie.mmoptionTwo.isClickable= true
        marvelmovie.mmoptionThree.isClickable=true
        marvelmovie.mmoptionFour.isClickable=true
        marvelmovie.mmbtnSubmit.isClickable = false
        marvelmovie.replace.visibility = View.GONE
        marvelmovie.mmbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()




        marvelmovie.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        marvelmovie.tvProgress.text = temp
        marvelmovie.mmbtnSubmit.text = getString(R.string.submit)
        marvelmovie.ivImage.setImageResource(question.image)
        marvelmovie.mmoptionOne.text = getString(question.optionOne)
        marvelmovie.mmoptionTwo.text = getString(question.optionTwo)
        marvelmovie.mmoptionThree.text =getString(question.optionThree)
        marvelmovie.mmoptionFour.text = getString(question.optionFour)

        var replace= true
        marvelmovie.replace.setOnClickListener {
            replace = when (replace) {

                true -> {
                    marvelmovie.ivImage.setImageResource(question.image)
                    slideAnimation()
                    false
                }

                false-> {
                    slideAnimation()
                    marvelmovie.ivImage.setImageResource(question.imageShow)
                    true
                }
            }
        }
    }



    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,marvelmovie.mmoptionOne)
        options.add(1,marvelmovie.mmoptionTwo)
        options.add(2,marvelmovie.mmoptionThree)
        options.add(3,marvelmovie.mmoptionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }








    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mmoption_one -> { selectedOptionView(marvelmovie.  mmoptionOne, 1);  btnSubmit() }
            R.id.mmoption_two -> { selectedOptionView(marvelmovie.  mmoptionTwo, 2); btnSubmit()}
            R.id.mmoption_three -> { selectedOptionView(marvelmovie.mmoptionThree, 3) ; btnSubmit()}
            R.id.mmoption_four ->  { selectedOptionView(marvelmovie.mmoptionFour, 4);btnSubmit()}


            R.id.mmbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val marvelMovieResult = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(MARVEL_MOVIE_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()


                            intent.putExtra("results",marvelMovieResult)
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
                    imageShow()
                    marvelmovie.mmoptionOne.isClickable = false
                    marvelmovie.mmoptionTwo.isClickable = false
                    marvelmovie.mmoptionThree.isClickable = false
                    marvelmovie.mmoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        marvelmovie.mmbtnSubmit.text= getString(R.string.finish)
                    } else {
                        marvelmovie.mmbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                marvelmovie.mmoptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                marvelmovie.mmoptionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                marvelmovie.mmoptionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                marvelmovie.mmoptionFour.background  = ContextCompat.getDrawable(this,drawableView)
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
        marvelmovie.mmbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        marvelmovie.mmbtnSubmit.setOnClickListener(this)

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

    private fun imageShow(){
        val question = mQuestionList!![mCurrentPosition -1]
        marvelmovie.ivImage.setImageResource(question.imageShow)
        slideAnimation()
        marvelmovie.replace.visibility = View.VISIBLE


    }

    /* анимация при изминений изоброжений*/
    private fun  slideAnimation(){
        //Bounce
        /*Fade Rollin Zoom Wobble Wave Flash
        FlipInY Hinge Landing Pulse RotateIn Shake
        RubberBand TakingOff DropOut Tada Swing StandUp*/
        YoYo.with(Techniques.FlipInY).duration(800).repeat(0).playOn(marvelmovie.ivImage)

    }


}