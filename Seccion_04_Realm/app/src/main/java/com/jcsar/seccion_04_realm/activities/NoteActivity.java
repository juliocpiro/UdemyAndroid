package com.jcsar.seccion_04_realm.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jcsar.seccion_04_realm.R;
import com.jcsar.seccion_04_realm.adapters.NoteAdapter;
import com.jcsar.seccion_04_realm.models.Board;
import com.jcsar.seccion_04_realm.models.Note;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton fab;

    private NoteAdapter adapter;
    private RealmList<Note> notes;
    private Realm realm;

    private int boardId;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm = Realm.getDefaultInstance();

        if(getIntent().getExtras() != null)
            boardId = getIntent().getExtras().getInt("id");

        board = realm.where(Board.class).equalTo("id",boardId).findFirst();
        notes = board.getNotes();

        this.setTitle(board.getTitle());

        fab = (FloatingActionButton) findViewById(R.id.fabAddNote);
        listView = (ListView) findViewById(R.id.listViewNote);
        adapter = new NoteAdapter(this,notes,R.layout.list_view_note_item);

        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
