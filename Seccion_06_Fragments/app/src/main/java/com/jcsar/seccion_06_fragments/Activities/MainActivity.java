package com.jcsar.seccion_06_fragments.Activities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcsar.seccion_06_fragments.Fragments.DataFragment;
import com.jcsar.seccion_06_fragments.Fragments.DetailsFragment;
import com.jcsar.seccion_06_fragments.R;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendData(String text) {
        // llamar al metodo rederizartexto de el detailsfragment
        // pasando el texto que recibimos por parametro en ese mismo metodo

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(text);
    }
}
