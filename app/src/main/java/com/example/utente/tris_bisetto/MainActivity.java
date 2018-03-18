package com.example.utente.tris_bisetto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String scelta_gioco="", nome, tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText utente= (EditText) findViewById(R.id.editNome);
        final RadioButton single=(RadioButton)findViewById(R.id.radioButton_single);
        final RadioButton multi=(RadioButton)findViewById(R.id.radioButton_multi);
        Button play_guess= (Button) findViewById(R.id.button_playGuess);
        final RadioButton choose_x= (RadioButton) findViewById(R.id.radio_x);
        final RadioButton choose_o= (RadioButton)findViewById(R.id.radio_o);



        choose_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scelta_gioco ="o";
            }
        });

        choose_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scelta_gioco ="x";
            }
        });

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo="single";
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo="multi";
            }
        });

        play_guess.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View view) {
               nome=utente.getText().toString();
                TextView err= (TextView) findViewById(R.id.text_error);

                if (scelta_gioco=="" || nome=="" || tipo=="") {
                    err.setText( R.string.error );
                }
                else {
                    Intent i = new Intent(MainActivity.this, Activity_game.class);
                    i.putExtra("simbolo", scelta_gioco);
                    i.putExtra("mod", nome);
                    i.putExtra("tipo", tipo);
                    startActivity(i);
                    err.setText("");
                    choose_o.setChecked(false);
                    choose_x.setChecked(false);
                    single.setChecked(false);
                    multi.setChecked(false);
                    utente.setText("");
                }
            }
        });


    }

}
