package com.jcsar.seccion_03_recycler_card_view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jcsar.seccion_03_recycler_card_view.adapters.MyAdapter;
import com.jcsar.seccion_03_recycler_card_view.R;
import com.jcsar.seccion_03_recycler_card_view.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movies;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = getAllMovies();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_items, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Movie movie, int position) {
                //Toast.makeText(MainActivity.this,"Hola "+movies.get(position).getName()+"-"+position,Toast.LENGTH_SHORT).show();
                RemoveMovie(position);
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
                this.AddMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void AddMovie(int position){
        movies.add(position,new Movie("Nuevo "+(++counter),R.drawable.paisaje));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void RemoveMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Clash 1", R.drawable.clash1));
            add(new Movie("Clash 2", R.drawable.clash2));
            add(new Movie("Clash 3", R.drawable.clash3));
            add(new Movie("Clash 4", R.drawable.clash4));
            add(new Movie("paisaje no se que", R.drawable.paisaje));
        }};
    }
}
