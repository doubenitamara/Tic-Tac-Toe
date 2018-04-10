package botamtech.com.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayGame extends AppCompatActivity implements View.OnClickListener {
    private int boardSize, roundCount, player1Points, player2Points;
    private boolean singlePlayer;
    private String player1_name, player2_name;
    private Button resetBtn;
    private boolean player1Turn = true;
    private TextView player1_text_view;
    private TextView player2_text_view;
    private TextView turn_text_view;
    private int row, col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();

        boardSize = Integer.parseInt(intent.getExtras().getString("board size"));
        singlePlayer = intent.getExtras().getBoolean("single player");
        player1_name = intent.getExtras().getString("player 1 name");

        if (singlePlayer == false)
            player2_name = intent.getExtras().getString("player 2 name");
        else {
            player2_name = intent.getExtras().getString("player 1 name");
            player1_name = "Computer";
        }


        if (boardSize == 3)
            setContentView(R.layout.activity_play_game_3);

        if (boardSize == 4)
            setContentView(R.layout.activity_play_game_4);

        if (boardSize == 5)
            setContentView(R.layout.activity_play_game_5);


        player1_text_view = (TextView) findViewById(R.id.player1_text_view);
        player2_text_view = (TextView) findViewById(R.id.player2_text_view);
        turn_text_view = (TextView) findViewById(R.id.turn_text_view);

        player1_text_view.setText(player1_name + ": 0");
        player2_text_view.setText(player2_name + ":0");
        turn_text_view.setText(player1_name + "'s turn(X)");

        Button[][] playBoard = new Button[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {

            for (int col = 0; col < boardSize; col++) {

                String btn_Id = "btn_" + row + col;
                int resource_Id = getResources().getIdentifier(btn_Id, "id", getPackageName());

                playBoard[row][col] = (Button) findViewById(resource_Id);
                playBoard[row][col].setOnClickListener(this);

            }
        }

        resetBtn = (Button) findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGameBoard();
            }
        });
       /* do{*/
      /*  if (singlePlayer == true) {
            player2_text_view.setText(player1_name + ": 0");
            computerPlay(playBoard);
            turn_text_view.setText(player2_name + "'s turn(O)");
        }
*/

        if (singlePlayer == true) {
            computerPlay();
            turn_text_view.setText(player2_name + "'s turn(O)");
        }
    }

    private void resetGameBoard() {

        Button[][] playBoard = new Button[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {

            for (int col = 0; col < boardSize; col++) {

                String btn_Id = "btn_" + row + col;
                int resource_Id = getResources().getIdentifier(btn_Id, "id", getPackageName());

                playBoard[row][col] = (Button) findViewById(resource_Id);

            }
        }

        for (int row = 0; row < boardSize; row++) {

            for (int col = 0; col < boardSize; col++) {

                playBoard[row][col].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;

        if (singlePlayer == false) {
            turn_text_view.setText(player1_name + "'s turn(X)");
        } else {
            computerPlay();
            turn_text_view.setText(player2_name + "'s turn(O)");
        }


    }


    private void player1Wins() {

        player1Points++;
        turn_text_view.setText(player1_name + " wins!");
        updatePointsTextView();
        // resetGameBoard();
    }

    private void player2Wins() {
        player2Points++;
        turn_text_view.setText(player2_name + " wins!");
        updatePointsTextView();
        //resetGameBoard();
    }

    private void updatePointsTextView() {
        player1_text_view.setText(player1_name + ": " + player1Points);
        player2_text_view.setText(player2_name + ": " + player2Points);
        //  turn_text_view.setText(player1_name + "'s turn(X)");
    }

    private void draw() {
        turn_text_view.setText("Game Draw!");
    }


    private boolean checkForWin() {

        Button[][] playBoard = new Button[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {

            for (int col = 0; col < boardSize; col++) {

                String btn_Id = "btn_" + row + col;
                int resource_Id = getResources().getIdentifier(btn_Id, "id", getPackageName());

                playBoard[row][col] = (Button) findViewById(resource_Id);

            }
        }


        String[][] field = new String[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                field[row][col] = playBoard[row][col].getText().toString();

            }
        }
        if (boardSize == 3) {

            for (int i = 0; i < boardSize; i++) {
                if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])
                        && !field[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < boardSize; i++) {
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])
                        && !field[0][i].equals("")) {
                    return true;
                }
            }

            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")) {
                return true;
            }

            if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")) {
                return true;
            }

        } else if (boardSize == 4) {

            for (int i = 0; i < boardSize; i++) {
                if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])
                        && field[i][0].equals(field[i][3]) && !field[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < boardSize; i++) {
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])
                        && field[0][i].equals(field[3][i]) && !field[0][i].equals("")) {
                    return true;
                }
            }

            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                    && field[0][0].equals(field[3][3]) && !field[0][0].equals("")) {
                return true;
            }

            if (field[0][3].equals(field[1][2]) && field[2][1].equals(field[3][0])
                    && !field[0][3].equals("")) {
                return true;
            }

        } else if (boardSize == 5) {

            for (int i = 0; i < boardSize; i++) {
                if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])
                        && field[i][0].equals(field[i][3]) && field[i][4].equals(field[i][2])
                        && !field[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < boardSize; i++) {
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])
                        && field[0][i].equals(field[3][i]) && field[0][i].equals(field[4][i])
                        && !field[0][i].equals("")) {
                    return true;
                }
            }

            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                    && field[0][0].equals(field[3][3]) && field[0][0].equals(field[4][4])
                    && !field[0][0].equals("")) {
                return true;
            }

            if (field[0][4].equals(field[1][3]) && field[2][2].equals(field[3][1])
                    && field[3][1].equals(field[4][0]) && !field[0][2].equals("")) {
                return true;
            }

        }


        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        /**
         * check if its human and computer player
         * or not
         */
        if (singlePlayer == false) {
            if (player1Turn) {
                ((Button) v).setText("X");
                turn_text_view.setText(player2_name + "'s turn(O)");
            } else {
                ((Button) v).setText("O");
                turn_text_view.setText(player1_name + "'s turn(X)");
            }
            updateAfterCheckWin();
        } else {
            // if (!player1Turn) {
            ((Button) v).setText("O");
            turn_text_view.setText(player1_name + "'s turn(X)");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerPlay();
                    turn_text_view.setText(player2_name + "'s turn(O)");
                }
            }, 1000);

            updateAfterCheckWin();
        }/*else {
                computerPlay();
                turn_text_view.setText(player1_name + "'s turn(X)");

            }*/
           /* computerPlay();
            turn_text_view.setText(player1_name + "'s turn(X)");
            updateAfterCheckWin();*/
        // }

    }

    /**
     * get roundcounts and check for a winner or draw
     */

    private void updateAfterCheckWin() {
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == (boardSize * boardSize)) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private void computerPlay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                /**
                 * get all the data from the views
                 * for use
                 */
                final Button[][] playBoard = new Button[boardSize][boardSize];

                for (int row = 0; row < boardSize; row++) {

                    for (int col = 0; col < boardSize; col++) {

                        String btn_Id = "btn_" + row + col;
                        int resource_Id = getResources().getIdentifier(btn_Id, "id", getPackageName());

                        playBoard[row][col] = (Button) findViewById(resource_Id);

                    }
                }

                int randCount = (boardSize * boardSize);
                int random = (int) (Math.random() * randCount);
                /**
                 * generate random numbers for the row and col
                 */
                row = random / boardSize;
                col = random % boardSize;

                if (player1Turn && !(playBoard[row][col].getText().equals("X")) && !(playBoard[row][col].getText().equals("O"))) {

                    playBoard[row][col].performClick();
                    playBoard[row][col].setText("X");

                } else {

                    computerPlay();
                }
            }
        }, 200);
    }
}
