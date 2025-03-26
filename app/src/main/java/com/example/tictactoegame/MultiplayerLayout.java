package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplayerLayout extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button horizontalBar1;
    Button horizontalBar2;
    Button verticalBar1;
    Button verticalBar2;
    Button newGame;
    Button backButton;

    List<Integer> crossPositions = new ArrayList<>();
    List<Integer> zeroPositions = new ArrayList<>();

    int position;
    int turn = 1;
    boolean newGameClicked;
    boolean backClicked;
    boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_layout);

        text = (TextView) findViewById(R.id.txt);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);
        horizontalBar1 = (Button) findViewById(R.id.horBar1);
        horizontalBar2 = (Button) findViewById(R.id.horBar2);
        verticalBar1 = (Button) findViewById(R.id.verBar1);
        verticalBar2 = (Button) findViewById(R.id.verBar2);
        newGame = (Button) findViewById(R.id.new_game);
        backButton = (Button) findViewById(R.id.back);

        startGame();
    }

    public void startGame() {
        gameOver = false;

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        newGame.setOnClickListener(this);
        backButton.setOnClickListener(this);

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        text.setText("");
    }

    @Override
    public void onClick(View view) {
        newGameClicked = false;
        backClicked = false;
        if (view.getId() == R.id.btn1) {
            position = 1;
        } else if (view.getId() == R.id.btn2) {
            position = 2;
        } else if (view.getId() == R.id.btn3) {
            position = 3;
        } else if (view.getId() == R.id.btn4) {
            position = 4;
        } else if (view.getId() == R.id.btn5) {
            position = 5;
        } else if (view.getId() == R.id.btn6) {
            position = 6;
        } else if (view.getId() == R.id.btn7) {
            position = 7;
        } else if (view.getId() == R.id.btn8) {
            position = 8;
        } else if (view.getId() == R.id.btn9) {
            position = 9;
        } else if (view.getId() == R.id.new_game) {
            crossPositions.removeAll(crossPositions);
            zeroPositions.removeAll(zeroPositions);
            newGameClicked = true;
            turn = 1;
            startGame();
        } else if (view.getId() == R.id.back) {
            backClicked = true;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //ODD TURN
        if (turn % 2 == 1 && newGameClicked == false && backClicked == false) {
            if (gameOver == false) {
                int crossPos;
                if (crossPositions.contains(position) || zeroPositions.contains(position)) {
                    turn += 0;
                }
                else {
                    crossPos = position;
                    turn++;

                    crossPositions.add(crossPos);
                    placeSymbol(crossPos, "cross");

                    String result = checkWinner();
                    if(result.length() > 0) {
                        text.setText(result);
                        gameOver = true;
                    }
                }
            }
        }
        //EVEN TURN
        else if (turn % 2 == 0 && newGameClicked == false && backClicked == false) {
            if (gameOver == false) {
                int zeroPos;
                if (crossPositions.contains(position) || zeroPositions.contains(position)) {
                    turn += 0;
                }
                else {
                    zeroPos = position;
                    turn++;

                    zeroPositions.add(zeroPos);
                    placeSymbol(zeroPos, "zero");

                    String result = checkWinner();
                    if(result.length() > 0) {
                        text.setText(result);
                        gameOver = true;
                    }
                }
            }
        }
    }

    public void placeSymbol(int pos, String user) {
        String symbol = "";

        if(user.equals("cross")) {
            symbol = "X";
        }
        else if (user.equals("zero")) {
            symbol = "0";
        }

        switch(pos) {
            case 1 :
                button1.setText(symbol);
                break;
            case 2 :
                button2.setText(symbol);
                break;
            case 3 :
                button3.setText(symbol);
                break;
            case 4 :
                button4.setText(symbol);
                break;
            case 5 :
                button5.setText(symbol);
                break;
            case 6 :
                button6.setText(symbol);
                break;
            case 7 :
                button7.setText(symbol);
                break;
            case 8 :
                button8.setText(symbol);
                break;
            case 9 :
                button9.setText(symbol);
                break;
        }
    }

    public String checkWinner() {
        List<Integer> firstRow = Arrays.asList(1, 2, 3);
        List<Integer> secondRow = Arrays.asList(4, 5, 6);
        List<Integer> thirdRow = Arrays.asList(7, 8, 9);
        List<Integer> firstCol = Arrays.asList(1, 4, 7);
        List<Integer> secondCol = Arrays.asList(2, 5, 8);
        List<Integer> thirdCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<>();
        winning.add(firstRow);
        winning.add(secondRow);
        winning.add(thirdRow);
        winning.add(firstCol);
        winning.add(secondCol);
        winning.add(thirdCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning) {
            if(crossPositions.containsAll(l)) {
                return "X won!";
            }
            else if(zeroPositions.containsAll(l)) {
                return "0 won!";
            }
        }
        if(crossPositions.size() + zeroPositions.size() == 9) {
            return "draw";
        }

        return "";
    }
}
