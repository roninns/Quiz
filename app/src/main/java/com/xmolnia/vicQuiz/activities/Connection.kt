package com.xmolnia.vicQuiz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.firebase.database.*
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityConnectionBinding
import com.xmolnia.vicQuiz.models.ReadQues

class Connection : AppCompatActivity() {

    private var hasFourOption = false
    private var hasFiveOption = false
    private var imageUri: String = ""
    private var detail: String = ""
    private var title: String = ""


    lateinit var connection: ActivityConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connection = ActivityConnectionBinding.inflate(layoutInflater)
        setContentView(connection.root)

//        connection.textViewBack.text="Osman" + resources.getDrawable(R.drawable.ic_back)+"Boy"

        intent?.let {
            imageUri = it.getStringExtra(IMAGE_URI).toString()
            detail = it.getStringExtra(DETAIL).toString()
            title = it.getStringExtra(TITLE).toString()
        }

        fun shimmerFun(color: Int): Shimmer {

            return Shimmer.ColorHighlightBuilder().setBaseColor(BaseActivity.getAppResources().getColor(R.color.widget_color)).setHighlightColor(BaseActivity.getAppResources().getColor(R.color.shimmer_color))

                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(1f).setHighlightAlpha(1f) // the shimmer alpha amount

                //прозрачность палочки мерцания
                .setIntensity(0.2f)
                //размер палочки мерцания
                .setDropoff(1f).setAutoStart(true).build()
        }


        // This is the placeholder for the imageView
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmerFun(R.color.golden))
        }

        connection.detail.text = detail
        Glide.with(this).load("").placeholder(shimmerDrawable).into(connection.image)



        if (title != null) {
            basicReadWrite(title, this)
        }
        else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }




        connection.button.setOnClickListener {
            if (hasFiveOption) {
                val myIntent = Intent(this, MultiPlay2::class.java)
                myIntent.putExtra(TITLE, title)
                startActivity(myIntent)
                hasFiveOption = false
                finish()
            }
            else if (hasFourOption) {
                val myIntent = Intent(this, MultiPlay::class.java)
                myIntent.putExtra(TITLE, title)
                startActivity(myIntent)
                hasFourOption = false
                finish()
            }


        }


    }

    private fun basicReadWrite(data: String, context: Context) {

        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Recycler").child(data)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (d in dataSnapshot.children) {
                    d.getValue(ReadQues::class.java)?.let {

                        if (it.optionFive.isNullOrEmpty()) {
                            connection.button.isClickable = true
                            hasFourOption = true
                        }
                        else if (!it.optionFive.isNullOrEmpty()) {
                            connection.button.isClickable = true
                            hasFiveOption = true

                        }
                        else {
                            startActivity(Intent(context, MainActivity::class.java))
                            finish()

                        }
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }


}