package com.example.axel_.controladordifuso_propensionvictimas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DB_Handler manejador;
    RadioGroup respuesta1;
    RadioGroup respuesta2;
    RadioGroup respuesta3;
    RadioGroup respuesta4;
    RadioGroup respuesta5;
    RadioButton respuesta1_radio;
    RadioButton respuesta2_radio;
    RadioButton respuesta3_radio;
    RadioButton respuesta4_radio;
    RadioButton respuesta5_radio;
    float propension;
    Button buttonFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manejador = new DB_Handler(this, null, null, 1);

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

    public void onButtonFinalizarClick(View view) {
        Encuesta encuesta = new Encuesta();
        int cuenta=0;
        respuesta1 = (RadioGroup) findViewById(R.id.respuesta1);
        respuesta2 = (RadioGroup) findViewById(R.id.respuesta2);
        respuesta3 = (RadioGroup) findViewById(R.id.respuesta3);
        respuesta4 = (RadioGroup) findViewById(R.id.respuesta4);
        respuesta5 = (RadioGroup) findViewById(R.id.respuesta5);
        buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        int[] selectId = new int[5];
        //Encontrar el id seleccionado de cada radio group
        selectId[0] = respuesta1.getCheckedRadioButtonId();
        selectId[1] = respuesta2.getCheckedRadioButtonId();
        selectId[2] = respuesta3.getCheckedRadioButtonId();
        selectId[3] = respuesta4.getCheckedRadioButtonId();
        selectId[4] = respuesta5.getCheckedRadioButtonId();
        //encontrar el radioButton seleccionado
        respuesta1_radio = (RadioButton) findViewById(selectId[0]);
        if(respuesta1_radio!=null) {
            Respuesta resp1 = new Respuesta(encuesta.getId_encuesta(), selectId[0], (String) respuesta1_radio.getText());
            manejador.addRespuesta(resp1);
            cuenta +=2;

        }
        respuesta2_radio = (RadioButton) findViewById(selectId[1]);
        if(respuesta2_radio!= null) {
            Respuesta resp2 = new Respuesta(encuesta.getId_encuesta(), selectId[1], (String) respuesta2_radio.getText());
            manejador.addRespuesta(resp2);
            cuenta +=3;
        }
        respuesta3_radio = (RadioButton) findViewById(selectId[2]);
        if(respuesta3_radio!=null) {
            Respuesta resp3 = new Respuesta(encuesta.getId_encuesta(), selectId[2], (String) respuesta3_radio.getText());
            manejador.addRespuesta(resp3);
            cuenta += 1;
        }
        respuesta4_radio = (RadioButton) findViewById(selectId[3]);
        if(respuesta4_radio!=null) {
            Respuesta resp4 = new Respuesta(encuesta.getId_encuesta(), selectId[3], (String) respuesta4_radio.getText());
            manejador.addRespuesta(resp4);
            cuenta +=3;

        }
        respuesta5_radio = (RadioButton) findViewById(selectId[4]);
        if(respuesta5_radio!=null) {
            Respuesta resp5 = new Respuesta(encuesta.getId_encuesta(), selectId[4], (String) respuesta5_radio.getText());
            manejador.addRespuesta(resp5);
            cuenta +=2;
        }
        manejador.addEncuesta(encuesta);
        propension = cuenta;
        switch (selectId[0]) {
            case R.id.respuesta1_1:
                propension += 2;
                break;
            case R.id.respuesta1_2:
                propension -= 2;
                break;
        }
        switch (selectId[1]) {
            case R.id.respuesta2_1:
                propension += 3;
                break;
            case R.id.respuesta2_2:
                propension -= 3;
                break;
        }
        switch (selectId[2]) {
            case R.id.respuesta3_1:
                propension -= 1;
                break;
            case R.id.respuesta3_2:
                propension += 1;
                break;
        }
        switch (selectId[3]) {
            case R.id.respuesta4_1:
                propension -= 3;
                break;
            case R.id.respuesta4_2:
                propension += 3;
                break;
        }
        switch (selectId[4]) {
            case R.id.respuesta5_1:
                propension += 2;
                break;
            case R.id.respuesta5_2:
                propension -= 2;
                break;
        }
        propension = (propension/(cuenta*2))*100;
        Intent intent = new Intent(this,Resultado.class);
        String resultado = String.valueOf(propension);
        intent.putExtra("resultado",propension);
        startActivity(intent);
        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
