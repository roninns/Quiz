package com.xmolnia.vicQuiz;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


/**
 * Created by Osman Boy on 26.03.2021.
 **/
public class MySharePreference {

        private static MySharePreference instance;
        private static SharedPreferences pref;

        private MySharePreference(Context context)
        {
            if (context != null)
            {
                pref = PreferenceManager.getDefaultSharedPreferences(context);
            }
            else
            {
                pref = PreferenceManager.getDefaultSharedPreferences(App.getintance());
            }
        }

        public static MySharePreference getInstance(Context context)
        {
            if (instance == null || pref == null)
            {
                instance = new MySharePreference(context);
            }
            return instance;
        }


        public String getLanguage()
        {
            return pref.getString("appLanguage", "ru");
        }

        public void setLanguage(String b)
        {
            pref.edit().putString("appLanguage", b).apply();
        }

}
