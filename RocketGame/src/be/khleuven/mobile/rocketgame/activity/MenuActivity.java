package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public void onClickStart(View view) {
		Intent myIntent = new Intent(view.getContext(), ShopActivity.class);
		startActivityForResult(myIntent, 0);
		overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
	}

}
