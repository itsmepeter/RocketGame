package be.khleuven.mobile.rocketgame.activity;

import android.app.Activity;
import android.os.Bundle;
import be.khleuven.mobile.rocketgame.R;

public class GameActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    
}
