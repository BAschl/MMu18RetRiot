package at.ac.tuwien.ims.uemultimediatemplate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**@author Hannah Clara
 * HighScore-Screen, where the highest sores are listed
 */
public class HighScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
    }
}
