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
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.PART2_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.PartTwoBinding
import com.xmolnia.vicQuiz.models.Question
import com.xmolnia.vicQuiz.shimmerDrawable
import com.xmolnia.vicQuiz.soundPlayCorrect
import com.xmolnia.vicQuiz.soundPlayWrong

class PartTwo : BaseActivity(),View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var partTwo: PartTwoBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        partTwo= PartTwoBinding.inflate(layoutInflater)
        setContentView(partTwo.root)

        /*Audio manager*/
        song= MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()



        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getTrend2Question()
        partTwo.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
        partTwo.ptoptionOne.setOnClickListener(this)
        partTwo.ptoptionTwo.setOnClickListener(this)
        partTwo.ptoptionThree.setOnClickListener(this)
        partTwo.ptoptionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        partTwo.ptoptionOne.isClickable= true
        partTwo.ptoptionTwo.isClickable= true
        partTwo.ptoptionThree.isClickable=true
        partTwo.ptoptionFour.isClickable=true
        partTwo.ptbtnSubmit.isClickable = false
        partTwo.ptbtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))




        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()


        Glide.with(this)

            .load(Constants.part2+question.imageURI)
                .placeholder(shimmerDrawable(R.color.tertiary_text, R.color.shimmer_bacground))
                .error(R.drawable.no_connection)
                .into(partTwo.ivImage)
        partTwo.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        partTwo.tvProgress.text = temp
        partTwo.ptbtnSubmit.text = getString(R.string.submit)
        partTwo.ptoptionOne.text = getString(question.optionOne)
        partTwo.ptoptionTwo.text = getString(question.optionTwo)
        partTwo.ptoptionThree.text =getString(question.optionThree)
        partTwo.ptoptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,partTwo.ptoptionOne)
        options.add(1,partTwo.ptoptionTwo)
        options.add(2,partTwo.ptoptionThree)
        options.add(3,partTwo.ptoptionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/







    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ptoption_one -> { selectedOptionView(partTwo.ptoptionOne, 1);  btnSubmit() }
            R.id.ptoption_two -> { selectedOptionView(partTwo.ptoptionTwo, 2); btnSubmit()}
            R.id.ptoption_three ->{selectedOptionView(partTwo.ptoptionThree, 3) ; btnSubmit()}
            R.id.ptoption_four ->{ selectedOptionView(partTwo.ptoptionFour, 4);btnSubmit()}

            R.id.ptbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val partTwoResult = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()
                            /*shared_preferences*/
                            Base.putAnswer(PART2_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()
                            intent.putExtra("results",partTwoResult)
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
                    partTwo.ptoptionOne.isClickable = false
                    partTwo.ptoptionTwo.isClickable = false
                    partTwo.ptoptionThree.isClickable = false
                    partTwo.ptoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        partTwo.ptbtnSubmit.text= getString(R.string.finish)
                    } else {
                        partTwo.ptbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                partTwo.ptoptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                partTwo.ptoptionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                partTwo.ptoptionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                partTwo.ptoptionFour.background  = ContextCompat.getDrawable(this,drawableView)
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
        partTwo.ptbtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        partTwo.ptbtnSubmit.setOnClickListener(this)

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
