package com.example.kolokvij;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    String citajPod;
    String lista[];
    ArrayAdapter adapter;
    ArrayList<String> list;
    ListView lv;
    String lista1[];
    String lista2[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
        lv=findViewById(R.id.listView);






        try {
            FileInputStream datCitaj =openFileInput("datoteka.txt");
            InputStreamReader citaj=new InputStreamReader(datCitaj);
            BufferedReader bf=new BufferedReader(citaj);
            StringBuilder sb=new StringBuilder();
            while(( citajPod= bf.readLine())!=null){
                lista=citajPod.split("#");


                for(int i=0;i<lista.length;++i){
                    String spremi=lista[i];

                    lista2=spremi.split(",");
                    for(int j=0;j<lista2.length/3;++j) {
                       list.add(lista2[0]);
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
        adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(MainActivity.this,Komentar.class);
                i.putExtra("naziv",lista[0]);
                startActivity(i);
            }
        });


    }

}
