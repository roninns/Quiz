package com.xmolnia.vicQuiz.cleanarch

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.activities.ResultActivity
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.databinding.ActivityMarvelBinding
import com.xmolnia.vicQuiz.models.Question

@SuppressLint("SetTextI8n")
class MarvelTest : BaseActivity(),View.OnClickListener {


    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private lateinit var marveltest:ActivityMarvelBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        marveltest= ActivityMarvelBinding.inflate(layoutInflater)
        setContentView(marveltest.root)



        /*Audio manager*/
        song=MediaPlayer.create(this, R.raw.background)
        song.isLooping=true
        song.start()



        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = Constants.getMarvelQuestion()
        marveltest.progressBar.max = mQuestionList!!.size
        setQuestion()


        /*слежка за нажатиями*/
       marveltest.mnoptionOne.setOnClickListener(this)
       marveltest.mnoptionTwo.setOnClickListener(this)
       marveltest.mnoptionThree.setOnClickListener(this)
       marveltest.mnoptionFour.setOnClickListener(this)

    }



    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion(){
        marveltest.mnoptionOne.isClickable= true
        marveltest.mnoptionTwo.isClickable= true
        marveltest.mnoptionThree.isClickable=true
        marveltest.mnoptionFour.isClickable=true
        marveltest.mnbtnSubmit.isClickable = false
        marveltest.mnbtnSubmit.backgroundTintList =AppCompatResources.getColorStateList(this,
            R.color.btn_dont_sel
        )




        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()




        marveltest.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition"+"/"+ mQuestionList?.size.toString()
        marveltest.tvProgress.text = temp
        marveltest.mnbtnSubmit.text = getString(R.string.submit)
        marveltest.tvQuestion.text = getString(question.question)
        marveltest.ivImage.setImageResource(question.image)
        marveltest.mnoptionOne.text = getString(question.optionOne)
        marveltest.mnoptionTwo.text = getString(question.optionTwo)
        marveltest.mnoptionThree.text =getString(question.optionThree)
        marveltest.mnoptionFour.text = getString(question.optionFour)


    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,marveltest.mnoptionOne)
        options.add(1,marveltest.mnoptionTwo)
        options.add(2,marveltest.mnoptionThree)
        options.add(3,marveltest.mnoptionFour)

        for (option in options){
            option.setTextColor(getColor(R.color.lightPrimaryColor))
            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/







    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mnoption_one -> { selectedOptionView(marveltest.mnoptionOne, 1);  btnSubmit() }
            R.id.mnoption_two -> { selectedOptionView(marveltest.mnoptionTwo, 2); btnSubmit()}
            R.id.mnoption_three ->{ selectedOptionView(marveltest.mnoptionThree, 3) ; btnSubmit()}
            R.id.mnoption_four ->{ selectedOptionView(marveltest.mnoptionFour, 4);btnSubmit()}


            R.id.mnbtn_submit -> {


                /*..........первый блок if начало*........*/
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            val marvelTestResult = "$mCorrectAnswers"+"/"+ mQuestionList?.size.toString()


                            /*shared_preferences*/
                            /* под уникальным именем*/
                            Base.putAnswer(MARVEL_TEST_CORRECT_ANSWERS, mCorrectAnswers)
                            Base.putRating()
                            intent.putExtra("results",marvelTestResult)
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
                    marveltest.mnoptionOne.isClickable = false
                    marveltest.mnoptionTwo.isClickable = false
                    marveltest.mnoptionThree.isClickable = false
                    marveltest.mnoptionFour.isClickable = false


                    if (mCurrentPosition == mQuestionList!!.size) {
                        marveltest.mnbtnSubmit.text= getString(R.string.finish)
                    } else {
                        marveltest.mnbtnSubmit.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }




    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1->{
                marveltest.mnoptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }

            2->{
                marveltest.mnoptionTwo.background  = ContextCompat.getDrawable(this,drawableView)
            }

            3->{
                marveltest.mnoptionThree.background  = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                marveltest.mnoptionFour.background  = ContextCompat.getDrawable(this,drawableView)
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
        marveltest.mnbtnSubmit.backgroundTintList = context.resources.getColorStateList(R.color.colorPrimary)
        marveltest.mnbtnSubmit.setOnClickListener(this)

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