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
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.MULTI_PLAY
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.TITLE
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityMultiPlayBinding
import com.xmolnia.vicQuiz.models.Ques
import com.xmolnia.vicQuiz.models.ResultImage
import com.xmolnia.vicQuiz.models.ResultText

class MultiPlay : BaseActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    lateinit var mQuestionList: ArrayList<Ques>
    lateinit var mResultTextList: ArrayList<ResultText>
    lateinit var mResultImageList: ArrayList<ResultImage>
    private var mSelectedOptionPosition: Int = 0
    private var answerCount: Int = 0
    lateinit var multiplay: ActivityMultiPlayBinding
    lateinit var song: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        multiplay = ActivityMultiPlayBinding.inflate(layoutInflater)
        setContentView(multiplay.root)


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
        multiplay.multioptionOne.setOnClickListener(this)
        multiplay.multioptionTwo.setOnClickListener(this)
        multiplay.multioptionThree.setOnClickListener(this)
        multiplay.multioptionFour.setOnClickListener(this)

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
                                    it.optionFour
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
                                    it.res5
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
                                    it.res5Image
                                )
                            )
                        }
                    }

                }
                multiplay.progressBar.max = mQuestionList.size
                setQuestion()


            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }


    /* создает вопрос, и присваевает нужные ресурсы*/
    private fun setQuestion() {
        multiplay.multioptionOne.isClickable = true
        multiplay.multioptionTwo.isClickable = true
        multiplay.multioptionThree.isClickable = true
        multiplay.multioptionFour.isClickable = true
        multiplay.multibtnSubmit.isClickable = false
        multiplay.multibtnSubmit.setBackgroundColor(resources.getColor(R.color.btn_dont_sel))


        /*достаем первый обЬект из Contains, хронящщий data class Question на  позиций 0*/
        val question = mQuestionList[mCurrentPosition - 1]
        defaultOptionsView()




        multiplay.progressBar.progress = mCurrentPosition
        val temp = "$mCurrentPosition" + "/" + mQuestionList.size.toString()
        multiplay.tvProgress.text = temp
        multiplay.multibtnSubmit.text = getString(R.string.submit)
        multiplay.tvQuestion.text = question.question
        Glide.with(this)
            .load(question.image)
            .placeholder(R.drawable.placeholder_small)
            .error(R.drawable.oops_small_small)
            .into(multiplay.ivImage)
        multiplay.multioptionOne.text = question.optionOne
        multiplay.multioptionTwo.text = question.optionTwo
        multiplay.multioptionThree.text = question.optionThree
        multiplay.multioptionFour.text = question.optionFour


    }


    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, multiplay.multioptionOne)
        options.add(1, multiplay.multioptionTwo)
        options.add(2, multiplay.multioptionThree)
        options.add(3, multiplay.multioptionFour)

        for (option in options) {
            option.setTextColor(getColor(R.color.lightPrimaryColor))

            option.typeface = ResourcesCompat.getFont(this, R.font.graphiklcg_regular)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }
/*.....................END.........................*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.multioption_one -> {
                selectedOptionView(multiplay.multioptionOne, 1); btnSubmit()
            }
            R.id.multioption_two -> {
                selectedOptionView(multiplay.multioptionTwo, 2); btnSubmit()
            }
            R.id.multioption_three -> {
                selectedOptionView(multiplay.multioptionThree, 3); btnSubmit()
            }
            R.id.multioption_four -> {
                selectedOptionView(multiplay.multioptionFour, 4);btnSubmit()
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
                            in 60..130 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res1Image.toString(), mResultTextList[0].res1.toString())
                                )
                            }
                            in 131..200 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res2Image.toString(), mResultTextList[0].res2.toString())
                                )

                            }
                            in 201..270 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res3Image.toString(), mResultTextList[0].res3.toString())
                                )

                            }
                            in 271..340 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(
                                        mResultImageList[0].res4Image.toString(),
                                        mResultTextList[0].res4.toString()
                                    )
                                )

                            }
                            in 341..500 -> {
                                intent.putExtra(
                                    MULTI_PLAY,
                                    arrayOf(mResultImageList[0].res5Image,toString(), mResultTextList[0].res5.toString())
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
                    multiplay.multibtnSubmit.text = getString(R.string.finish)
                }

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
        multiplay.multibtnSubmit.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        multiplay.multibtnSubmit.setOnClickListener(this)

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