package com.example.finalmemoriegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String[][] gameBoard;
    ImageView[] ImageViews;
    int count;
    int player1 = 0;
    int player2 = 0;
    int card;
    int lastOpenedCard1 = -1;
    int lastOpenedCard2 = -1;
    String turn = "counterPlayer1";
    Integer[] drawableIds = {
            //Array of pics under cards
            R.drawable.alon, R.drawable.basketball, R.drawable.books, R.drawable.chikenalfredo, R.drawable.football, R.drawable.integrals, R.drawable.penguin, R.drawable.pops,
            R.drawable.alon, R.drawable.basketball, R.drawable.books, R.drawable.chikenalfredo, R.drawable.football, R.drawable.integrals, R.drawable.penguin, R.drawable.pops
    };
    int[] viewId = {
            //Array of cards
            R.id.card_11, R.id.card_12, R.id.card_13, R.id.card_14,
            R.id.card_21, R.id.card_22, R.id.card_23, R.id.card_24,
            R.id.card_31, R.id.card_32, R.id.card_33, R.id.card_34,
            R.id.card_41, R.id.card_42, R.id.card_43, R.id.card_44
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = findViewById(R.id.WelcomeMsg);
        welcomeTextView.setText("welcome: " + username);
    }

    private void onNewGame()
    {
        shuffle();
        gameBoard = new String[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                gameBoard[row][col] = new String();
            }
            count=0;
        }
    }

    private void onNewGame2(){
        ImageView[] imageViews = new ImageView[16]; // initialize the ImageViews array
        for (int i = 0; i < drawableIds.length; i++) {
            // get context from the activity
            Context context = null;
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(drawableIds[i]);
            imageViews[i] = imageView;
        }
    }

    private void shuffle() {
        List<Integer> drawableIdsList = Arrays.asList(drawableIds);
        Collections.shuffle(drawableIdsList);
        drawableIdsList.toArray(drawableIds);
    }

    public void knock(View view) {
        count++;
        for (int i = 0; i < viewId.length; i++) {
            if (view.getId() == viewId[i]) {
                ImageView backImageView = findViewById(viewId[i]);
                backImageView.setImageResource(drawableIds[i]);
                turnEnd(view);
            }
        }
        //update last open card
        if (lastOpenedCard1 != -1)
        {
            lastOpenedCard2 = lastOpenedCard1;
        }
        lastOpenedCard1 = view.getId();
    }

    public void turnEnd(View view) {
        if (count % 2 != 0) {
            card = view.getId();
            return;
        }
        if (count % 2 == 0) {
            ImageView imageViewCard = findViewById(card);
            Drawable image1 = imageViewCard.getDrawable();
            Drawable image2 = ((ImageView) view).getDrawable();
            if (image1.getConstantState().equals(image2.getConstantState())) {
                if (turn.equals("counterPlayer1")) {
                    player1++;
                    TextView counterTextView = findViewById(R.id.player1counter);
                    counterTextView.setText("P1-" + player1);
                    turn = "counterPlayer2";
                } else {
                    player2++;
                    TextView counterTextView = findViewById(R.id.player2counter);
                    counterTextView.setText("P2-" + player2);
                    turn = "counterPlayer1";
                }
            } else {
                if (turn.equals("counterPlayer1")) {
                    turn = "counterPlayer2";
                } else {
                    turn = "counterPlayer1";

                }
            }
        }
    }

    public void Reset(View view) {
        shuffle();
        count = 0;
        player1 = 0;
        TextView counterTextView = findViewById(R.id.player1counter);
        counterTextView.setText("P1-" + player1);
        player2 = 0;
        TextView counterTextView2 = findViewById(R.id.player2counter);
        counterTextView2.setText("P2-" + player2);
        for (int i = 0; i < viewId.length; i++) {
            ImageView cardClosed = findViewById(viewId[i]);
            cardClosed.setImageResource(R.drawable.cards);
        }
        lastOpenedCard1 = -1;
        lastOpenedCard2 = -1;
    }

    public void closeCard(View view) {
        ImageView cardClosed1 = findViewById(lastOpenedCard1);
        cardClosed1.setImageResource(R.drawable.cards);
        ImageView cardClosed2 = findViewById(lastOpenedCard2);
        cardClosed2.setImageResource(R.drawable.cards);
    }


}