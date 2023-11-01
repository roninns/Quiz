package com.xmolnia.vicQuiz.cleanarch


import android.os.Bundle
import android.widget.Toast
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.MySharePreference
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.activities.Movie
import com.xmolnia.vicQuiz.activities.Profile
import com.xmolnia.vicQuiz.activities.Settings
import com.xmolnia.vicQuiz.activities.Tests
import com.xmolnia.vicQuiz.customToastForMain
import com.xmolnia.vicQuiz.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mySharePreference = MySharePreference.getInstance(this)
        mySharePreference.language = mySharePreference.language

        val fragmentSettings = Settings()
        val fragmentMovie = Movie.newInstance()
        val fragmentTests = Tests()
        val fragmentProfile = Profile()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentTests)
                .commit()
            binding.bottomBar.selectedItemId = TEST_ID
        }



        binding.bottomBar.setOnItemSelectedListener {

            val fragment = when (it.itemId) {
                R.id.settingsFragment -> fragmentSettings
                R.id.movieFragment -> fragmentMovie
                R.id.testFragment -> fragmentTests
                R.id.profileFragment -> fragmentProfile
                else -> throw RuntimeException("fragment from index: $it is not found")
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        }


    }

    companion object {
        private const val PROFILE_ID = 0
        private const val TEST_ID = 1
        private const val MOVIE_ID = 2
        private const val SETTINGS_ID = 3

    }


    /*Системная кнопка "Назад" - начало*/

    private var toast: Toast? = null
    private var backPressedTime: Long = 0
    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast?.cancel()
            super.onBackPressed()
            return
        } else {
            toast = Toast(this).customToastForMain(this)
            toast?.show()
        }

        backPressedTime = System.currentTimeMillis()
    }


}
