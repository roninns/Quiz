package com.xmolnia.vicQuiz;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Osman Boy on 26.03.2021.
 **/
public class BaseActivity extends AppCompatActivity {
    public static Resources resources;
    public BaseActivity context;
    public MySharePreference mySharePreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        mySharePreference = MySharePreference.getInstance(context);
        LocaleHelper.setLocale(context, mySharePreference.getLanguage() );
        resources=getResources();
    }

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public static Resources getAppResources(){
        return resources;
    }

}
