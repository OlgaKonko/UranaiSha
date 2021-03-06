package com.example.olga_kondratenko.androidapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.olga_kondratenko.androidapplication.predication_generator.PredicationGenerator;

import org.apache.log4j.Logger;

import java.util.Locale;

import static constants.names.APP_PREFERENCES;
import static constants.names.APP_PREFERENCES_LANGUAGE;

public class MainActivity extends Activity {
private PredicationGenerator predicationGenerator;
    private SharedPreferences languageSettings;
    private Logger log = Logger.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("Main activity start");
        System.out.println("Main activity start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        languageSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        predicationGenerator = new PredicationGenerator(getBaseContext());
    }



    @Override
    protected void onRestart(){
        log.info("Main activity restart");
        System.out.println("Main activity restart");
        super.onRestart();
        setLocate();
    }

    private void setLocate(){
        log.info("Try to set locate");
        System.out.println("Try to set locate");

        if (languageSettings.contains(APP_PREFERENCES_LANGUAGE)) {
        String lang = languageSettings.getString(APP_PREFERENCES_LANGUAGE, getBaseContext().getResources().getConfiguration().locale.getLanguage());
        log.info("Set "+lang+" language");
        System.out.println("Set "+lang+" language");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
        }
    }

    public void onClick(View view) {
        log.info("Click on predicate button");
        System.out.println("Click on predicate button");
        final TextView predication = findViewById(R.id.Predication);
        final TextView main = findViewById(R.id.MainText);
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        predication.setText(R.string.waiting);
        main.setText(R.string.waiting);

        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                progressBar.setVisibility(View.INVISIBLE);
                predication.setText(predicationGenerator.predicate());
                main.setText(R.string.new_greatings);
            }
        }.start();
    }

    public void openSettings(View view) {
        log.info("Open settings");
        System.out.println("Open settings");
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
        setLocate();}}
        recreate();
    }
}
