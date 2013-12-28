package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameOverActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
		
		setContentView(R.layout.activity_gameover);
		
		Intent intent = getIntent();
		int height = intent.getIntExtra("height", 0);
		int money = intent.getIntExtra("money", 0);
		
		String text = "Height: " + height + "\n" + "Money: " + money + "\n Bonus money: " +  height/10;
		
		final TextView textViewToChange = (TextView) findViewById(R.id.textView2);
		textViewToChange.setText(text);
	}
	
	public void onClickShop(View view) {
		Intent myIntent = new Intent(view.getContext(), ShopActivity.class);
		startActivityForResult(myIntent, 0);
		overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
	}
}
