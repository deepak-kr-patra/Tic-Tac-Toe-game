package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button singlePlayer;
    Button multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePlayer = (Button) findViewById(R.id.button1);
        multiplayer = (Button) findViewById(R.id.button2);

        singlePlayer.setOnClickListener(this);
        multiplayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1) {
            Intent intent = new Intent(this, SinglePlayerLayout.class);
            startActivity(intent);
        } else if (view.getId() == R.id.button2) {
            Intent intent = new Intent(this, MultiplayerLayout.class);
            startActivity(intent);
        }
    }
}