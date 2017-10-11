package com.jcsar.seccion_03_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> names;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getAllNames();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(names, R.layout.recycler_view_items, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String name, int position) {
                DeleteName(position);
                //Toast.makeText(MainActivity.this,"Hola "+names.get(position)+"-"+position,Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //creando menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.AddName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void AddName(int position){
        names.add(position,"Nuevo "+(++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void DeleteName(int position){
        names.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Hola");
            add("Julio");
            add("Cesar");
            add("Piro");
            add("Gonzales");
        }};
    }
}
