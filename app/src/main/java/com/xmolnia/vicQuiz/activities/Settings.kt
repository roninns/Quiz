package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.cleanarch.MainActivity


class Settings : PreferenceFragmentCompat() {

    private lateinit var auth: FirebaseAuth

    private lateinit var switchPreferenceCompat: SwitchPreference
    private lateinit var listPreferenceCompat: ListPreference
    private lateinit var categoryPreferenceCompat: PreferenceCategory
    private lateinit var mySharePreference: MySharePreference
    private lateinit var logoutPreferenceCompat:CheckBoxPreference

    init {
        Log.d("CREATED", "Settings : $this")

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)


        init()
        mySharePreference= MySharePreference.getInstance(requireActivity().applicationContext)




        /*START- Check SDK version for Night Mode*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            categoryPreferenceCompat.isVisible=true
            switchPreferenceCompat.isVisible=true
        }
        else{
            categoryPreferenceCompat.isVisible=false
            switchPreferenceCompat.isVisible=false

        }





        /*..............START CHANGER APP THEME:  NIGHT OR LIGHT FOR SDK LEVEL VERSION................*/
        when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
            Configuration.UI_MODE_NIGHT_NO ->{
                switchPreferenceCompat.isChecked = false

            }
            Configuration.UI_MODE_NIGHT_YES ->{
                switchPreferenceCompat.isChecked = true

            }
        }
        /*..............END CHANGER APP THEME:  NIGHT OR LIGHT FOR SDK LEVEL VERSION................*/






        /*..............START CHANGER APP THEME:  NIGHT OR LIGHT ................*/
        switchPreferenceCompat.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, obj ->

                val checked = obj as Boolean

                if (checked) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchPreferenceCompat.isChecked = true
                    Base.nightThemeActivated()
                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchPreferenceCompat.isChecked = false
                    Base.nightThemeDisActivated()
                }

                true
            }
        /*..............END CHANGER APP THEME:  NIGHT OR LIGHT................*/





        /*..............START CHANGER APP LANGUAGE................*/
        when(mySharePreference.language){
            "ru"->{
                listPreferenceCompat.setValueIndex(0)
                listPreferenceCompat.summary = listPreferenceCompat.entries[0]}

            "en"->{
                listPreferenceCompat.setValueIndex(1)
                listPreferenceCompat.summary = listPreferenceCompat.entries[1]}

        }
        listPreferenceCompat.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, obj ->

            when(obj as String){

                "ru" -> {
                    mySharePreference.language = "ru"
                    startActivity(Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
                "en" -> {
                    mySharePreference.language = "en"
                    startActivity(Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
            true
        }
        /*..............END CHANGER APP LANGUAGE................*/





        //LOGOUT
        logoutPreferenceCompat.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            val yes = newValue as Boolean
            if(yes){
                Base.putAnswer(MARVEL_TEST_CORRECT_ANSWERS, 0)
                Base.putAnswer(MARVEL_MOVIE_CORRECT_ANSWERS, 0)
                Base.putAnswer(MARVEL_PIXEL_CORRECT_ANSWERS, 0)
                Base.putAnswer(FREE_FIRE_CORRECT_ANSWERS, 0)
                Base.putAnswer(ZOMBIE_TEST_CORRECT_ANSWERS, 0)
                Base.putAnswer(DISNEYLAND_CORRECT_ANSWERS, 0)
                Base.putAnswer(PART1_CORRECT_ANSWERS, 0)
                Base.putAnswer(PART2_CORRECT_ANSWERS, 0)
                Base.putAnswer(DISNEYLAND2_CORRECT_ANSWERS, 0)
                Base.putValues("log", "default")
                auth.signOut()
                startActivity(Intent(context, Sign::class.java))
                activity?.finish()
                requireActivity().finish()
            }
            true
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        logoutPreferenceCompat.setDefaultValue(false)
    }

    private fun init() {
        Base.initial(requireContext())
        auth = Firebase.auth
        categoryPreferenceCompat = findPreference("themes_category")!!
        switchPreferenceCompat = findPreference("NIGHT")!!
        logoutPreferenceCompat = findPreference("LOG")!!
        listPreferenceCompat = findPreference("Language")!!
        logoutPreferenceCompat.isChecked = false
        logoutPreferenceCompat.setDefaultValue(false)
    }

}



