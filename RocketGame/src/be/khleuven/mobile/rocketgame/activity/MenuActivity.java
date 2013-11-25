package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}

}
