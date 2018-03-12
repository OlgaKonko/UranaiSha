package com.example.olga_kondratenko.androidapplication.predication_generator;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PredicationGenerator {
    private Random random = new Random();
    private List<String> predications;

    public PredicationGenerator(Context context) {
        predications = new ArrayList<>();
        File storageDirectory = Environment.getExternalStorageDirectory();
        System.out.println("!!!!"+storageDirectory.getAbsolutePath());
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader( context.getAssets().open("predications.txt")));
           String predication;
            while ((predication = reader.readLine()) != null) {
                predications.add(predication);
            }
            reader.close();
        } catch (IOException e) {
            predications.add("I can't");
            e.printStackTrace();
        }
    }

    public String predicate(){
return  predications.get(random.nextInt(predications.size()));
    }

}
