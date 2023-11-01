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
import com.bumptech.glide.Glide
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.PartOneBinding
import com.xmolnia.vicQuiz.models.Question

class PartOne : BaseActivity(),View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var partOne: PartOneBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        partOne= PartOneBinding.inflate(layoutInflater)
        setContentView(partOne.root)

        /*Audio manager*/
        song= MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()



        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getTrend1Question()
        partOne.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        partOne.pooptionOne.setOnClickListener(this)
        partOne.pooptionTwo.setOnClickListener(this)
        partOne.pooptionThree.setOnClickListener(this)
        partOne.pooptionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        partOne.pooptionOne.isClickable= true
        partOne.pooptionTwo.isClickable= true
        partOne.pooptionThree.isClickable=true
        partOne.pooptionFour.isClickable=true
        partOne.pobtnSubmit.isClickable = false
        partOne.pobtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))




        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()


        Glide.with(this)

            .load(Constants.part1+question.imageURI)
                .placeholder(R.drawable.placeholder_big)
                .error(R.drawable.no_connection)
                .into(partOne.ivImage)
        partOne.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        partOne.tvProgress.text = temp
        partOne.pobtnSubmit.text = getString(R.string.submit)
        partOne.pooptionOne.text = getString(question.optionOne)
        partOne.pooptionTwo.text = getString(question.optionTwo)
        partOne.pooptionThree.text =getString(question.optionThree)
        partOne.pooptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,partOne.pooptionOne)
        options.add(1,partOne.pooptionTwo)
        options.add(2,partOne.pooptionThree)
        options.add(3,partOne.pooptionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/







    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.pooption_one -> { selectedOptionView(partOne.pooptionOne, 1);  btnSubmit() }
            R.id.pooption_two -> { selectedOptionView(partOne.pooptionTwo, 2); btnSubmit()}
            R.id.pooption_three ->{selectedOptionView(partOne.pooptionThree, 3) ; btnSubmit()}
            R.id.pooption_four ->{ selectedOptionView(partOne.pooptionFour, 4);btnSubmit()}


            R.id.pobtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val partOneResult = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(PART1_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()
                            intent.putExtra("results",partOneResult)
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
                    partOne.pooptionOne.isClickable = false
                    partOne.pooptionTwo.isClickable = false
                    partOne.pooptionThree.isClickable = false
                    partOne.pooptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        partOne.pobtnSubmit.text= getString(R.string.finish)
                    } else {
                        partOne.pobtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                partOne.pooptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                partOne.pooptionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                partOne.pooptionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                partOne.pooptionFour.background  = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }





    private fun selectedOptionView(tv: Button, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#CF00FD"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }



    private fun btnSubmit(){
        partOne.pobtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        partOne.pobtnSubmit.setOnClickListener(this)

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