package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.os.Bundle
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.cleanarch.MainActivity

class SplashScreen : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Base.initial(applicationContext)
        super.onCreate(savedInstanceState)



        if (Base.getValues("log") == "default") {
            val intent = Intent(this, Sign::class.java)

            startActivity(intent)
            finish()

        } else {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}




