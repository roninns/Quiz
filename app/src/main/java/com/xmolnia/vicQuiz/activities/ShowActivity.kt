package com.xmolnia.vicQuiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.xmolnia.vicQuiz.DESCRIPTION
import com.xmolnia.vicQuiz.GAMER_NAME
import com.xmolnia.vicQuiz.RATING
import com.xmolnia.vicQuiz.TITLE
import com.xmolnia.vicQuiz.adapters.CustomListViewAdapter
import com.xmolnia.vicQuiz.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    private lateinit var showBinding: ActivityShowBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(showBinding.root)


        val title = intent?.getStringArrayListExtra(TITLE)
        val description = intent?.getStringArrayListExtra(DESCRIPTION)
        val gamerName = intent?.getStringExtra(GAMER_NAME)
        val rating = intent?.getIntExtra(RATING,0)
        if(title !=null && description != null) {
            val customAdapter = CustomListViewAdapter(this, title, description)
            showBinding.ratingDialog.adapter = customAdapter
        }
        val temp ="Рейтинг игрока - N=$rating"
        showBinding.rating.text = temp
        showBinding.gamerName.text = gamerName!!
        YoYo.with(Techniques.BounceIn).duration(900).repeat(0).playOn(showBinding.ratingAlertDialog)
    }

}