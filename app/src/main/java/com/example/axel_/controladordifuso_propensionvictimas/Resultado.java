package com.example.axel_.controladordifuso_propensionvictimas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle resultado = getIntent().getExtras();

        if(resultado==null)
            return;
        float resltadoPorcentaje = resultado.getFloat("resultado");
        TextView textViewResultado = (TextView)findViewById(R.id.resultadoView);
        textViewResultado.setText("Tu porcentaje de propensión es: "+String.valueOf(resltadoPorcentaje));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Text I want to share.";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
            }
        });
    }

    public void regresarClick(View view){
        Intent intentRegresar = new Intent(this,MainActivity.class);
        startActivity(intentRegresar);
    }

}
