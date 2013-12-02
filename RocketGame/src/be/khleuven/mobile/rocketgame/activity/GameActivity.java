package be.khleuven.mobile.rocketgame.activity;

import android.app.Activity;
import android.os.Bundle;
import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.view.GameView;

public class GameActivity extends Activity {
	GameView gameview;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameview = (GameView)findViewById(R.id.gameActivity1);
    }
    
   public void initialiseerImages(){
	   
	   
   }
    
}
