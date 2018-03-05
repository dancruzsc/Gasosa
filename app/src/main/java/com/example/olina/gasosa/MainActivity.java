package com.example.olina.gasosa;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.olina.gasosa.model.Entrada;
import com.example.olina.gasosa.model.EntradaDao;
import com.example.olina.gasosa.util.AppDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Entrada> entradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        new DatabaseAsync().execute();
    }

    public void atualizaLista() {
        ListView view = (ListView) findViewById(R.id.lista);
        ArrayAdapter<Entrada> adapter = new ArrayAdapter<Entrada>(getApplicationContext(),
                android.R.layout.simple_list_item_1, entradas);

        view.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    public void adicionarEntrada(View view) {
        Intent intent = new Intent(this, AdicionarEntradaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                new DatabaseAsync().execute();
            }
        }
    }

    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            entradas = db.entradaDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            atualizaLista();
        }
    }
}