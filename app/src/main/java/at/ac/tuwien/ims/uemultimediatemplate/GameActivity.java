package at.ac.tuwien.ims.uemultimediatemplate;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * The main game takes place in this activity. It contains only a {@Link GameSurfaceview}.
 * TODO: Make it fullscreen.
 * @author Bernhard Aschl
 */
public class GameActivity extends AppCompatActivity {
    /**
     * Pretty much standard onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

}
