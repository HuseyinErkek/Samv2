package com.example.samvet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview extends AppCompatActivity {

    ArrayList<YemModel> yemModels = new ArrayList<>();
    TextView OranText;


    int[] Yemimage = {R.drawable.arpa, R.drawable.bugday, R.drawable.misir,
            R.drawable.soyakuspesi, R.drawable.tamyaglisoya, R.drawable.ayciceigikuspesi,
            R.drawable.bitkiselyag, R.drawable.misirgluteni, R.drawable.kepek};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Button Sıfırla_btn = findViewById(R.id.sıfırla);
        setContentView(R.layout.activity_recyclerview);
        OranText = findViewById(R.id.son);
        Button apo = findViewById(R.id.kam_btn);
        RecyclerView recyclerView = findViewById(R.id.sıralama);
        YemRcyView_Adapter adapter = new YemRcyView_Adapter(this, yemModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] Yemadi = getResources().getStringArray(R.array.yemler);
        for (int i = 0; i < Yemadi.length; i++) {
            yemModels.add(new YemModel(Yemadi[i], Yemimage[i]));
        }
        recyclerView.setAdapter(adapter);

        apo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double toplamSonuc = adapter.getToplamSonuc();
                OranText.setText(String.valueOf(toplamSonuc));
            }
        });
        Sıfırla_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recyclerview.this, MainActivity2.class));
            }
        });
    }
}