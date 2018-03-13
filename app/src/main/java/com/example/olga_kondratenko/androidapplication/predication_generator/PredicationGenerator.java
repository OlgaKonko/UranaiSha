package com.example.olga_kondratenko.androidapplication.predication_generator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static constants.locate.*;
import static constants.predicatonConstants.*;

public class PredicationGenerator {
    private Random random = new Random();
    private List<String> predications;
    private int currentPredicationNumber;
    private String filename;
    public PredicationGenerator(Context context) {
        System.out.println("reload predications");
        predications = new ArrayList<>();
        setLocateSettings(context);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( context.getAssets().open(filename)));
           String predication;
            while ((predication = reader.readLine()) != null) {
                predications.add(predication);
            }
            reader.close();
            currentPredicationNumber = START_CODE;
        } catch (IOException e) {
            currentPredicationNumber = ERROR_CODE;
            e.printStackTrace();
        }
        rePredicate();
    }

    public String predicate(){
        if (currentPredicationNumber == ERROR_CODE){
            return predications.get(ERROR_CODE);
        }
        currentPredicationNumber = random.nextInt(predications.size()-1)+1;
        return  predications.get(currentPredicationNumber);
    }

    public String rePredicate(){
       return predications.get(currentPredicationNumber);
    }

    private void setLocateSettings(Context context){
        System.out.println("predications locate "+ context.getResources().getConfiguration().locale.getLanguage());
        switch (context.getResources().getConfiguration().locale.getLanguage()){
            case  (UKRAINIAN): {
                predications.add(ERROR_UK);
                filename = FILE_NAME_UK;
                break;
            }
            default: {
                predications.add(ERROR);
                filename = FILE_NAME;
                break;
            }
        }
        System.out.println("loading predications from "+ filename);
    }

}
