package botamtech.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SinglePlayerDataView extends AppCompatActivity implements View.OnClickListener {
    private Button submitBtn;
    private EditText player_name_1_tf, board_size_tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_data_view);

        submitBtn = (Button) findViewById(R.id.submit_player_data_btn);


        player_name_1_tf = (EditText) findViewById(R.id.player1_tf);
        board_size_tf = (EditText) findViewById(R.id.board_size_tf);
        submitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("Submit")) {
            String player1 = player_name_1_tf.getText().toString();
            if (Integer.parseInt(board_size_tf.getText().toString()) < 3 ||
                    Integer.parseInt(board_size_tf.getText().toString()) > 5) {
                Toast.makeText(SinglePlayerDataView.this, "Board size must be between 3 - 5", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(SinglePlayerDataView.this, PlayGame.class);
                intent.putExtra("player 1 name", player1);
                intent.putExtra("board size", board_size_tf.getText().toString());
                intent.putExtra("single player", true);

                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        }
    }
}
