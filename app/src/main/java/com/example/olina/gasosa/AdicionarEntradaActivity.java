package com.example.olina.gasosa;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.olina.gasosa.model.Entrada;
import com.example.olina.gasosa.util.AppDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarEntradaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_entrada);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void cancelar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Cancelar nova entrada?");
        builder.setMessage("Tem certeza de que deseja cancelar?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AdicionarEntradaActivity.super.onBackPressed();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            cancelar();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.iconeAdicionarEntrada:
                new DatabaseAsync().execute();
                return true;
            case android.R.id.home:
                cancelar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());

                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                Date data = sdf.parse(((TextView) findViewById(R.id.campoData)).getText().toString());

                double preco = Double.parseDouble(((TextView) findViewById(R.id.campoPreco)).getText().toString());
                double litros = Double.parseDouble(((TextView) findViewById(R.id.campoLitragem)).getText().toString());
                long quilometros = Long.parseLong(((TextView) findViewById(R.id.campoQuilometragem)).getText().toString());

                Entrada entrada = new Entrada();
                entrada.setData(data);
                entrada.setValorPago(preco);
                entrada.setLitragem(litros);
                entrada.setQuilometragem(quilometros);

                db.entradaDao().insertEntradas(entrada);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setResult(RESULT_OK);
            finish();
        }
    }
}
