package botamtech.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button singlePlayerBtn, twoPlayerBtn, exitGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePlayerBtn = (Button) findViewById(R.id.single_player_btn);
        twoPlayerBtn = (Button) findViewById(R.id.two_player_btn);
        exitGameBtn = (Button) findViewById(R.id.exit_game_btn);


        singlePlayerBtn.setOnClickListener(this);
        twoPlayerBtn.setOnClickListener(this);
        exitGameBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("Single Player")) {

            Intent intent = new Intent(MainActivity.this, SinglePlayerDataView.class);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);

        } else if (((Button) v).getText().toString().equals("Two Player")) {

            Intent intent = new Intent(MainActivity.this, TwoPlayerDataView.class);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);


        }
        if (((Button) v).getText().toString().equals("Exit Game")) {
            finish();
            System.exit(0);
        }
    }
}
