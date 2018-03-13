package com.example.olga_kondratenko.androidapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static constants.locate.*;
import static constants.names.*;


public class SettingsActivity extends Activity {
    private SharedPreferences languageSettings;
    List<View> languages;
    private Logger log = Logger.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("Settings activity create");
        System.out.println("Settings activity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       checkLanguageOptions();
    }

    @Override
    protected void onRestart() {
        log.info("Settings activity restart");
        System.out.println("Settings activity restart");
        super.onRestart();
    }

    public void returnBack(View view) {
        log.info("Settings activity close");
        System.out.println("Settings activity close");
        finish();
    }

    public void chooseLanguage(View view) {
        log.info("choose language");
        System.out.println("choose language");
        for (View language:languages){
            if (!language.equals(view))
            ((RadioButton)language).setChecked(false);
            else
                ((RadioButton)language).setChecked(true);
        }

        switch (view.getId()){
            case R.id.englishButton:{
                setLocate(ENGLISH);
                break;
            }
            case R.id.ukrainianButton:{
                setLocate(UKRAINIAN);
                break;
            }
        }
        recreate();
    }
    private void checkLanguageOptions(){
        log.info("set language option");
        System.out.println("set language option");
        languages = new ArrayList<>();
        languages.add(findViewById(R.id.englishButton));
        languages.add(findViewById(R.id.ukrainianButton));

        Locale currentLocale = getBaseContext().getResources().getConfiguration().locale;
        switch (currentLocale.getLanguage()){
            case ENGLISH:{
                ((RadioButton)findViewById(R.id.englishButton)).setChecked(true);
                break;
            }
            case UKRAINIAN:{
                ((RadioButton)findViewById(R.id.ukrainianButton)).setChecked(true);
                break;
            }
        }
    }

    private void setLocate(String locate){
        log.info("set and save locate");
        System.out.println("set and save locate");

        Intent answerIntent = new Intent();
        answerIntent.putExtra(APP_PREFERENCES_LANGUAGE, locate);
        setResult(RESULT_OK, answerIntent);

        languageSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = languageSettings.edit();
        editor.putString(APP_PREFERENCES_LANGUAGE, locate);
        editor.apply();

        Locale locale = new Locale(locate);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }
}
