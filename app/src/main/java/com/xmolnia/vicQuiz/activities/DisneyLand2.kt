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
import com.bumptech.glide.Glide
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityDisneyLand2Binding
import com.xmolnia.vicQuiz.models.Question

class DisneyLand2 : BaseActivity(),View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var disneylan2: ActivityDisneyLand2Binding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disneylan2= ActivityDisneyLand2Binding.inflate(layoutInflater)
        setContentView(disneylan2.root)

        /*Audio manager*/
        song= MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()



        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getDisneyLand2Question()
        disneylan2.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        disneylan2.dl2OptionOne.setOnClickListener(this)
        disneylan2.dl2OptionTwo.setOnClickListener(this)
        disneylan2.dl2OptionThree.setOnClickListener(this)
        disneylan2.dl2OptionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        disneylan2.dl2OptionOne.isClickable= true
        disneylan2.dl2OptionTwo.isClickable= true
        disneylan2.dl2OptionThree.isClickable=true
        disneylan2.dl2OptionFour.isClickable=true
        disneylan2.dl2BtnSubmit.isClickable = false
        disneylan2.dl2BtnSubmit.setBackgroundColor(getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()


        Glide.with(this)
                .load(Constants.disney1+question.imageURI)
                .placeholder(R.drawable.placeholder_big)
                .error(R.drawable.no_connection)
                .into(disneylan2.ivImage)
        disneylan2.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        disneylan2.tvProgress.text = temp
        disneylan2.dl2BtnSubmit.text = getString(R.string.submit)
        disneylan2.dl2OptionOne.text = getString(question.optionOne)
        disneylan2.dl2OptionTwo.text = getString(question.optionTwo)
        disneylan2.dl2OptionThree.text =getString(question.optionThree)
        disneylan2.dl2OptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,disneylan2.dl2OptionOne)
        options.add(1,disneylan2.dl2OptionTwo)
        options.add(2,disneylan2.dl2OptionThree)
        options.add(3,disneylan2.dl2OptionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/







    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.dl2_option_one -> { selectedOptionView(disneylan2.dl2OptionOne, 1);  btnSubmit() }
            R.id.dl2_option_two -> { selectedOptionView(disneylan2.dl2OptionTwo, 2); btnSubmit()}
            R.id.dl2_option_three ->{selectedOptionView(disneylan2.dl2OptionThree, 3) ; btnSubmit()}
            R.id.dl2_option_four ->{ selectedOptionView(disneylan2.dl2OptionFour, 4);btnSubmit()}


            R.id.dl2_btn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val disneyLand2Result = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(DISNEYLAND2_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()
                            intent.putExtra("results",disneyLand2Result)
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
                    disneylan2.dl2OptionOne.isClickable = false
                    disneylan2.dl2OptionTwo.isClickable = false
                    disneylan2.dl2OptionThree.isClickable = false
                    disneylan2.dl2OptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        disneylan2.dl2BtnSubmit.text= getString(R.string.finish)
                    } else {
                        disneylan2.dl2BtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                disneylan2.dl2OptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                disneylan2.dl2OptionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                disneylan2.dl2OptionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                disneylan2.dl2OptionFour.background  = ContextCompat.getDrawable(this,drawableView)
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
        disneylan2.dl2BtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        disneylan2.dl2BtnSubmit.setOnClickListener(this)

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
