package com.example.kolokvij;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rezultat extends AppCompatActivity {
    EditText komentar1;
    EditText komentar2;
    EditText naziv;
    RatingBar ocjena;
    String pod;
    String[] lista;
    String[] aktivnosti;
    float[] rating;
    String[] komentar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultat);
        ocjena=findViewById(R.id.ratingBar2);
        komentar2=findViewById(R.id.editText6);
        komentar1=findViewById(R.id.editText5);
        naziv=findViewById(R.id.editText4);

        try {
            FileInputStream datCitaj =openFileInput("datoteka.txt");
            InputStreamReader citaj=new InputStreamReader(datCitaj);
            BufferedReader bf=new BufferedReader(citaj);
            StringBuilder sb=new StringBuilder();
            while(( pod= bf.readLine())!=null){
                lista=pod.split(",");
                for(int i=0;i<lista.length;++i){
                    aktivnosti[i]=lista[i];
                    rating[i]=Float.valueOf(lista[0]);
                    komentar[i]=lista[0];
                }




            }
            bf.close();
            citaj.close();
            datCitaj.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<rating.length;++i){
            float sum=0;
            sum= sum+rating[i];
            ocjena.setRating(sum);
            float prosjek= (float) 5.0;
            float prosjek1=(float) 0.0;

            if(rating[i]<prosjek){
                prosjek=rating[i];
                int broj=i;
                naziv.setText(String.valueOf(broj));
                komentar2.setText(String.valueOf(broj));

            }
            if(rating[i]>prosjek){
                prosjek=rating[i];
                int broj1=i;
                naziv.setText(String.valueOf(broj1));
                komentar1.setText(String.valueOf(broj1));

            }

        }






    }
}
