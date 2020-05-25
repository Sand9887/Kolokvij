package com.example.kolokvij;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Komentar extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    TextView aktivnost;
    TextView datum;
    TextView komentari;
    RatingBar rb;
    SharedPreferences sharedPreferences;
    Intent i;
    String pod;
    String[] lista;
    String ak="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar);
        aktivnost=findViewById(R.id.editText);
        datum=findViewById(R.id.editText2);
        komentari=findViewById(R.id.editText3);
        rb=findViewById(R.id.ratingBar);



        try {

            i=getIntent();
            String aktivNaslov=i.getStringExtra("naziv");
            lista=aktivNaslov.split(",");
            aktivnost.setText(lista[0]);
            datum.setText(lista[1]);
            komentari.setText(lista[2]);


            FileInputStream datCitaj =openFileInput("datoteka.txt");
            InputStreamReader citaj=new InputStreamReader(datCitaj);
            BufferedReader bf=new BufferedReader(citaj);
            StringBuilder sb=new StringBuilder();
            while(( pod= bf.readLine())!=null){
                lista=pod.split(",");
                for(int i=0;i<lista.length;++i){
                    if(aktivNaslov.equals(lista[i])){
                        aktivnost.setText(lista[0]);
                    }
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








        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment=new Datum();
                dialogFragment.show(getSupportFragmentManager(),"DATE");
            }
        });

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menulayout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spremi:
               // sharedPreferences=getSharedPreferences("Kolokvij",MODE_PRIVATE);
                //SharedPreferences.Editor ed=sharedPreferences.edit();
                //ed.putString("aktivnost",aktivnost.getText().toString());
                //ed.commit();
                try {
                    FileOutputStream dat = openFileOutput("datoteka.txt",MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(dat);


                    String stari=ak+aktivnost.getText().toString()+","+komentari.getText().toString()+","+datum.getText().toString()+","+rb.getRating()+"#";
                    ak=stari;


                    outputStreamWriter.write(stari);
                    outputStreamWriter.close();
                    Toast.makeText(Komentar.this,"Podaci su uspjesno spremljeni",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }




                return true;

            case R.id.rezultati:
                Intent i=new Intent(Komentar.this,Rezultat.class);
                startActivity(i);

             default:
                 return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datum.setText(dayOfMonth+"/"+month+"/"+year);
    }

}
