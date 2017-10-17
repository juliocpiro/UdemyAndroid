package com.jcsar.seccion_03_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jcsar.seccion_03_lab.R;
import com.jcsar.seccion_03_lab.adapters.FruitAdapter;
import com.jcsar.seccion_03_lab.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FruitAdapter adapter;

    private List<Fruit> fruits;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = getAllFruits();

        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        layoutManager = new LinearLayoutManager(this);

        adapter = new FruitAdapter(fruits, R.layout.recycler_view_fruit_item, this, new FruitAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Fruit fruit, int position) {
                fruit.addQuantity(1);
                adapter.notifyItemChanged(position);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit :
                int position = fruits.size();
                fruits.add(position,new Fruit("Plum " + (++counter),"Fruit added by the user", R.drawable.plum_bg, R.mipmap.ic_plum, 0));
                adapter.notifyItemInserted(position);
                layoutManager.scrollToPosition(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
