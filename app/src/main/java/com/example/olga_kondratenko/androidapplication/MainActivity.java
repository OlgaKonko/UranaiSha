package com.example.olga_kondratenko.androidapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.olga_kondratenko.androidapplication.predication_generator.PredicationGenerator;

public class MainActivity extends AppCompatActivity {
private PredicationGenerator predicationGenerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        predicationGenerator = new PredicationGenerator(getApplicationContext());
    }
private TextView predication;
    public void onClick(View view) {
        predication = findViewById(R.id.Predication);
        predication.setText(predicationGenerator.predicate());
    }
}
