package at.ac.tuwien.ims.uemultimediatemplate;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**@author Hannah Clara
 * MainActivity is the main Menu of the game, includes 3 buttons (start, highscores, help)
 * The Start button will start the game(GameActivity). The Highscore Button opens the Highscore-screen (HighScoreActivty)
 * and the Help-button will lead to a screen (HelpActivtiy), where controls and rules of the game are explained.
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start_button = (Button) findViewById(R.id.start_button);
        Button highscore_button = (Button) findViewById(R.id.highscore_button);
        Button help_button = (Button) findViewById(R.id.help_button);

        start_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startgame_intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startgame_intent);
            }
        });

        highscore_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent openhighscore_intent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(openhighscore_intent);
            }
        });

        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openhelp_intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(openhelp_intent);
            }
        });

    }
}

