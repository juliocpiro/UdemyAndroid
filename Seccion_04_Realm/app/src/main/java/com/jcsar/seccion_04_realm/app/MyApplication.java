package com.jcsar.seccion_04_realm.app;

import android.app.Application;

import com.jcsar.seccion_04_realm.models.Board;
import com.jcsar.seccion_04_realm.models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

//Esta clase se ejecuta siempre antes del mainActivity (en este ejemplo BoardActivity)
public class MyApplication extends Application {
    public static AtomicInteger BoardId = new AtomicInteger();
    public static AtomicInteger NoteId = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        BoardId = getIdByTable(realm, Board.class);
        NoteId = getIdByTable(realm, Note.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        RealmConfiguration config = new RealmConfiguration
                .Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> result = realm.where(anyClass).findAll();
        return (result.size() > 0) ? new AtomicInteger(result.max("id").intValue()) : new AtomicInteger();
    }
}
