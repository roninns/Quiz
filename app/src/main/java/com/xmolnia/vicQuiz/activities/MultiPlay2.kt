package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.MULTI_PLAY
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.TITLE
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityMultiplay2Binding
import com.xmolnia.vicQuiz.models.Ques
import com.xmolnia.vicQuiz.models.ResultImage
import com.xmolnia.vicQuiz.models.ResultText

class MultiPlay2 : BaseActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private lateinit var mQuestionList: ArrayList<Ques>
    private lateinit var mResultTextList: ArrayList<ResultText>
    private lateinit var mResultImageList: ArrayList<ResultImage>
    private var mSelectedOptionPosition: Int = 0
    private var answerCount: Int = 0
    private lateinit var multiplay2: ActivityMultiplay2Binding
    private lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        multiplay2 = ActivityMultiplay2Binding.inflate(layoutInflater)
        setContentView(multiplay2.root)


        /*Audio manager*/
        song = MediaPlayer.create(this, R.raw.background)
        song.isLooping = true
        song.start()


        /*присваеваем содержимое обЬекта- que1, que2 и т.д*/
        mQuestionList = arrayListOf()
        mResultTextList = arrayListOf()
        mResultImageList = arrayListOf()
        val intent = intent.getStringExtra(TITLE)
        if (intent != null) {
            basicReadWrite(intent.toString())
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }


        /*слежка за нажатиями*/
        multiplay2.multi2optionOne.setOnClickListener(this)
        multiplay2.multi2optionTwo.setOnClickListener(this)
        multiplay2.multi2optionThree.setOnClickListener(this)
        multiplay2.multi2optionFour.setOnClickListener(this)

    }

    private fun basicReadWrite(intent: String) {

        mResultTextList.clear()
        mResultImageList.clear()
        mQuestionList.clear()
        val myRef: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Recycler").child(intent)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (d in dataSnapshot.children) {
                    d.getValue(Ques::class.java)?.let {

                        if (!it.image.isNullOrEmpty()) {
                            mQuestionList.add(
                                Ques(
                                    it.image,
                                    it.question,
                                    it.optionOne,
                                    it.optionTwo,
                                    it.optionThree,
                                    it.optionFour,
                                    it.optionFive
                                )
                            )

                        }
                    }

                    d.getValue(ResultText::class.java)?.let {

                        if (!it.res1.isNullOrEmpty()) {
                            mResultTextList.add(
                                ResultText(
                                    it.res1,
                                    it.res2,
                                    it.res3,
                                    it.res4,
                                    it.res5,
                                    it.res6
                                )
                            )
                        }
                    }



                    d.getValue(ResultImage::class.java)?.let {

                        if (!it.res1Image.isNullOrEmpty()) {
                            mResultImageList.add(
                                ResultImage(
                                    it.res1Image,
                                    it.res2Image,
                                    it.res3Image,
                                    it.res4Image,
                                    it.res5Image,
                                    it.res6Image
                                )
                            )
                        }
                    }

                }
                multiplay2.progressBar.max = mQuestionList.size
                setQuestion()


            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }


    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion() {
        multiplay2.multi2optionOne.isClickable = true
        multiplay2.multi2optionTwo.isClickable = true
        multiplay2.multi2optionThree.isClickable = true
        multiplay2.multi2optionFour.isClickable = true
        multiplay2.multi2btnSubmit.isClickable = false
        multiplay2.multi2btnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList[mCurrentPosition - 1]
        defaultOptionsView()




        multiplay2.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition" + "/" + mQuestionList.size.toString()
        multiplay2.tvProgress.text = temp
        multiplay2.multi2btnSubmit.text = getString(R.string.submit)
        multiplay2.tvQuestion.text = question.question
        Glide.with(this)
            .load(question.image)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(multiplay2.ivImage)
        multiplay2.multi2optionOne.text = question.optionOne
        multiplay2.multi2optionTwo.text = question.optionTwo
        multiplay2.multi2optionThree.text = question.optionThree
        multiplay2.multi2optionFour.text = question.optionFour
        multiplay2.multi2optionFive.text = question.optionFive


    }


    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, multiplay2.multi2optionOne)
        options.add(1, multiplay2.multi2optionTwo)
        options.add(2, multiplay2.multi2optionThree)
        options.add(3, multiplay2.multi2optionFour)

        for (option in options) {
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.multi2option_one -> {
                selectedOptionView(multiplay2.multi2optionOne, 1); btnSubmit()
            }
            R.id.multi2option_two -> {
                selectedOptionView(multiplay2.multi2optionTwo, 2); btnSubmit()
            }
            R.id.multi2option_three -> {
                selectedOptionView(multiplay2.multi2optionThree, 3); btnSubmit()
            }
            R.id.multi2option_four -> {
                selectedOptionView(multiplay2.multi2optionFour, 4);btnSubmit()
            }

            R.id.multi2option_five -> {
                selectedOptionView(multiplay2.multi2optionFive, 5);btnSubmit()
            }


            R.id.multibtn_submit -> {

                when (mSelectedOptionPosition) {
                    1 -> {
                        answerCount += 10
                    }
                    2 -> {
                        answerCount += 20

                    }
                    3 -> {
                        answerCount += 30

                    }
                    4 -> {
                        answerCount += 40

                    }

                    5 -> {
                        answerCount += 40

                    }
                }
                /*..........первый блок if начало*........*/
                mCurrentPosition++
                when {
                    mCurrentPosition <= mQuestionList.size -> {
                        mSelectedOptionPosition = 0
                        setQuestion()
                    }

                    else -> {
                        val intent = Intent(this, ResultActivity::class.java)

                        when (answerCount) {
                            in 80..140 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res1Image.toString(), mResultTextList[0].res1.toString())
                                )
                            }
                            in 141..200 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res2Image.toString(), mResultTextList[0].res2.toString())
                                )

                            }
                            in 201..260 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res3Image.toString(), mResultTextList[0].res3.toString())
                                )

                            }
                            in 261..320 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(
                                        mResultImageList[0].res4Image.toString(),
                                        mResultTextList[0].res4.toString()
                                    )
                                )

                            }
                            in 321..400 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res5Image,toString(), mResultTextList[0].res5.toString())
                                )
                            }
                            in 401..500 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res6Image,toString(), mResultTextList[0].res6.toString())
                                )
                            }
                            else -> {
                                intent.putExtra(
                                    MULTI_PLAY, arrayOf("https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot." +
                                        "com/o/ic_launcher-playstore.png?alt=media&token=24e9a421-2715-402a-a47f-b7640707bcb0",
                                    "Мы не смогли определить"))
                            }
                        }


                        startActivity(intent)
                        finish()
                    }
                }


                if (mCurrentPosition == mQuestionList.size) {
                    multiplay2.multi2btnSubmit.text = getString(R.string.finish)
                }

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
        multiplay2.multi2btnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        multiplay2.multi2btnSubmit.setOnClickListener(this)

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