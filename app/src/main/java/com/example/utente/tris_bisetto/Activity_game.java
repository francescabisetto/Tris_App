package com.example.utente.tris_bisetto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class Activity_game extends AppCompatActivity {

    private TextView mTextMessage, info;
    private String text, utente;
    private Button A1, A2, A3, B1, B2, B3, C1, C2, C3, new_game;
    String simbolo, pc_simbolo, tipo;
    Integer flag=0;
    Boolean tris=false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent i= new Intent( Activity_game.this, MainActivity.class );
                    startActivity( i );
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //presenti nella parte grafica
        mTextMessage = (TextView) findViewById(R.id.message);
        A1= (Button) findViewById(R.id.button_A1);
        A2= (Button) findViewById(R.id.button_A2);
        A3= (Button)findViewById(R.id.button_A3);
        B1= (Button) findViewById(R.id.button_B1);
        B2= (Button) findViewById(R.id.button_B2);
        B3= (Button)findViewById(R.id.button_B3);
        C1= (Button) findViewById(R.id.button_C1);
        C2= (Button) findViewById(R.id.button_C2);
        C3= (Button)findViewById(R.id.button_C3);
        info= (TextView)findViewById(R.id.textView_info);
        new_game=(Button)findViewById(R.id.button_newGame);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Bundle extra= getIntent().getExtras();
        TextView nome_ut= (TextView) findViewById(R.id.textNome_Utente);
        utente= extra.getString("mod");
        simbolo= extra.getString("simbolo");
        tipo=extra.getString("tipo");
        nome_ut.setText(""+utente);
        info.setText( getResources().getString( R.string.first ) );

        if (simbolo.equalsIgnoreCase("x")){
            pc_simbolo="o";
        }else{
            pc_simbolo="x";
        }


        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("single")){
                    insert_into_grid(A1, simbolo);
                }else{
                    //multiplayer
                    multiplayer(A1,simbolo);
                }

            }
        });

        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("single")){
                    insert_into_grid(A2, simbolo);
                }else{
                    multiplayer(A2,simbolo);
                }

            }
        });

        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("single")){
                    insert_into_grid(A3, simbolo);
                }else{
                    multiplayer(A3,simbolo);
                }

            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("single")){
                    insert_into_grid(B1, simbolo);
                }else{
                    multiplayer(B1,simbolo);
                }

            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("single")){
                    insert_into_grid(B2, simbolo);
                }else{
                    multiplayer(B2,simbolo);
                }

            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("single")){
                    insert_into_grid(B3, simbolo);
                }else{
                    multiplayer(B3,simbolo);
                }
            }
        });

        C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("single")){
                    insert_into_grid(C1, simbolo);
                }else{
                    multiplayer(C1,simbolo);
                }
            }
        });

        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("single")){
                    insert_into_grid(C2, simbolo);
                }else{
                    multiplayer(C2,simbolo);
                }
            }
        });

        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("single")){
                    insert_into_grid(C3, simbolo);
                }else{
                    multiplayer(C3,simbolo);
                }
            }
        });

        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public void insert_into_grid (Button B, String simb) {
        flag++;
        controllo();
        if (!tris) {
            if (flag == 5) {
                B.setText(simb);
                info.setText( R.string.finish);
            } else {
                text = B.getText().toString();
                info.setText("");
                if (text == "") {
                    B.setText(simb);
                    insert_pc_simbolo(pc_simbolo);
                } else {
                    info.setText(R.string.error_game);
                }
            }
        } else {
            info.setText(R.string.button_new);
        }
    }

    public void multiplayer (Button b, String simb){
        flag++;
        controllo();
        text=b.getText().toString();
        if (!tris){
            if (flag==9){
                controllo();
                b.setText(simb);
                info.setText(R.string.finish);
            }
            if (flag%2==0){
                if(text.equals("")){
                    controllo();
                    b.setText(pc_simbolo);
                }else{
                    info.setText(R.string.error_game);
                }
            }else{
                if(text.equals("")){
                    controllo();
                    b.setText(simb);
                }else{
                    info.setText(R.string.error_game);
                }
            }
        }
        controllo();
    }

    public void insert_pc_simbolo (String simbolo){
        Integer n;
        Boolean f;
        controllo();
        if(!tris){
            Random rand= new Random();
            n=rand.nextInt(9);
            Log.i("Numero:  ", String.valueOf(n));
            switch (n){
                case 0:
                    if (A1.getText()==""){
                        A1.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 1:
                    if (A2.getText()==""){
                        A2.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 2:
                    if (A3.getText()==""){
                        A3.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 3:
                    if (B1.getText()==""){
                        B1.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 4:
                    if (B2.getText()==""){
                        B2.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 5:
                    if (B3.getText()==""){
                        B3.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 6:
                    if (C1.getText()==""){
                        C1.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 7:
                    if (C2.getText()==""){
                        C2.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
                case 8:
                    if (C3.getText()==""){
                        C3.setText(simbolo);
                        controllo();
                    }else{
                        insert_pc_simbolo(simbolo);
                    }
                    break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void controllo() {
        String a1 = "", a2 = "", a3 = "", b1 = "", b2 = "", b3 = "", c1 = "", c2 = "", c3 = "";
        a1 = A1.getText().toString();
        a2 = A2.getText().toString();
        a3 = A3.getText().toString();
        b1 = B1.getText().toString();
        b2 = B2.getText().toString();
        b3 = B3.getText().toString();
        c1 = C1.getText().toString();
        c2 = C2.getText().toString();
        c3 = C3.getText().toString();
        System.out.println("a1 " + a1 + " a2 " + a2 + " a3 " + a3 + "  b1 " + b1 + " b2 " + b2 + " b3 " + b3 + " c1 " + c1 + " c2 " + c2 + " c3 " + c3);

        //tris orizzontali
        if (a1 != "" && a2 != "" && a3 != "" && Objects.equals(a1, a2) && Objects.equals(a2, a3)) {
            tris = true;
            if (a1.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }
            change_color(A1,A2,A3);
        }
        if (b1 != "" && b2 != "" && b3 != "" && Objects.equals(b1, b2) && Objects.equals(b2, b3)) {
            tris = true;
            if (b1.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }
            change_color(B1,B2,B3);

        }
        if (c1 != "" && c2 != "" && c3 != "" && Objects.equals(c1, c2) && Objects.equals(c2, c3)) {
            tris = true;
            if (c1.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }
            change_color(C1,C2,C3);
        }

        //tris verticali
        if (a1 != "" && b1 != "" && c1 != "" && Objects.equals(a1, b1) && Objects.equals(b1, c1)) {
            tris = true;
            if (a1.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }
            change_color(A1,B1,C1);
        }
        if (a2 != "" && b2 != "" && c2 != "" && Objects.equals(a2, b2) && Objects.equals(b2, c2)) {
            tris = true;
            if (a2.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }
            change_color(A2,B2,C2);
        }
        if (a3 != "" && b3 != "" && c3 != "" && Objects.equals(a3, b3) && Objects.equals(b3, c3)) {
            tris = true;
            if (a3.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }            change_color(A3,B3,C3);
        }

        //tris diagonali
        if (a3 != "" && b2 != "" && c1 != "" && Objects.equals(a3, b2) && Objects.equals(b2, c1)) {
            tris = true;
            if (a3.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }            change_color(A3,B2,C1);
        }
        if (a1 != "" && b2 != "" && c3 != "" && Objects.equals(a1, b2) && Objects.equals(b2, c3)) {
            tris = true;
            if (a1.equals( simbolo )){
                info.setText(utente + "   " + getResources().getString( R.string.vinto ));
            }else{
                info.setText(getResources().getString( R.string.ospite_giocatore ) + "   " + getResources().getString( R.string.vinto ));
            }            change_color(A1,B2,C3);
        }
    }


        public void change_color (Button a, Button b, Button c){
            a.setBackgroundColor(0xffffff00);
            b.setBackgroundColor(0xffffff00);
            c.setBackgroundColor(0xffffff00);
    }


}
