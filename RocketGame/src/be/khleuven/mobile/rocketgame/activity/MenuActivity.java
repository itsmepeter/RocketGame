package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.example.games.basegameutils.BaseGameActivity;

public class MenuActivity extends BaseGameActivity {
	public static int REQUEST_ACHIEVEMENTS = 1001;
	public static int REQUEST_LEADERBOARD = 1002;

	public void setSigninButtonState() {
		if (isSignedIn()) {
			findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
			findViewById(R.id.sign_in_button).setVisibility(View.GONE);
		} else {
			findViewById(R.id.sign_out_button).setVisibility(View.GONE);
			findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onSignInFailed() {
		setSigninButtonState();
	}

	@Override
	public void onSignInSucceeded() {
		setSigninButtonState();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// start the asynchronous sign in flow
						beginUserInitiatedSignIn();
					}
				});

		findViewById(R.id.sign_out_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						signOut();
						setSigninButtonState();
					}
				});

		findViewById(R.id.btnAchievements).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (isSignedIn()) {
							startActivityForResult(getGamesClient()
									.getAchievementsIntent(),
									REQUEST_ACHIEVEMENTS);
						}
					}
				});
		findViewById(R.id.btnHighscores).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (isSignedIn()) {
						      startActivityForResult(getGamesClient().getLeaderboardIntent(
						              getResources().getString(R.string.leaderboard_highest_distance_android)), REQUEST_LEADERBOARD);
						}
					}
				});
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
		overridePendingTransition(R.anim.animation_enter,
				R.anim.animation_leave);
	}

}
