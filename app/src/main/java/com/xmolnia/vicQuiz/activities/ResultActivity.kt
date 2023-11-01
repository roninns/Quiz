package com.xmolnia.vicQuiz.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityResultBinding

@SuppressLint("SetTextI18n")
class ResultActivity : BaseActivity() {

    private lateinit var result: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result = ActivityResultBinding.inflate(layoutInflater)
        setContentView(result.root)


        Base.initial(this)
        val results = intent.getStringExtra("results")
        val pol = intent.getStringExtra("pol")
        val uri = intent.getStringExtra("uri")
        val multiPlay = intent.getStringArrayExtra(MULTI_PLAY)

        if(multiPlay!=null){
            result.tvShowResults.text = multiPlay[1]
            Glide
                .with(this)
                .load(multiPlay[0])
                .placeholder(R.drawable.placeholder_big).error(R.drawable.no_connection)
                .into(result.gif)
            result.gif.visibility = View.VISIBLE
            result.ivTrophy.visibility = View.GONE
        }





        result.userName.text = Base.getValues(GAMER_NAME)
        if (results != null) {
            result.tvShowResults.text = getString(R.string.your_result_is) + " " + results.toString()

        }

        if (pol != null) {
            Glide.with(this).load(uri).placeholder(R.drawable.placeholder_big).error(R.drawable.no_connection)
                .into(result.gif)
            result.tvShowResults.text = pol.toString()
            result.gif.visibility = View.VISIBLE
            result.ivTrophy.visibility = View.GONE
            result.info.text = intent?.getStringExtra("m/g")
            result.userName.visibility = View.GONE

        }





        result.finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}