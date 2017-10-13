package com.jcsar.seccion_03_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcsar.seccion_03_lab.R;
import com.jcsar.seccion_03_lab.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private FruitAdapter adapter;

    private List<Fruit> fruits;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = getAllFruits();

        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        layoutManager = new LinearLayoutManager(this);
    }

    private List<Fruit> getAllFruits(){
        return new ArrayList<Fruit>(){
            {
                add(new Fruit("Strawberry","strawberry description",R.drawable.strawberry_bg,R.mipmap.ic_strawberry,0));
                add(new Fruit("Orange","orange description",R.drawable.orange_bg,R.mipmap.ic_orange,0));
                add(new Fruit("Apple","apple description",R.drawable.apple_bg,R.mipmap.ic_apple,0));
                add(new Fruit("Banana","banana description",R.drawable.banana_bg,R.mipmap.ic_banana,0));
                add(new Fruit("Cherry","cherry description",R.drawable.cherry_bg,R.mipmap.ic_cherry,0));
                add(new Fruit("Pear","pear description",R.drawable.pear_bg,R.mipmap.ic_pear,0));
                add(new Fruit("Raspberry","rasbperry description",R.drawable.raspberry_bg,R.mipmap.ic_rasperry,0));
            }
        };
    }
}
