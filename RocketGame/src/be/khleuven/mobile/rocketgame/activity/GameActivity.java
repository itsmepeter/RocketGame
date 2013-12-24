package be.khleuven.mobile.rocketgame.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.view.GameView;

public class GameActivity extends Activity {
	GameView gameview;

	private SensorManager mSensorManager;
	private float x; // acceleration apart from gravity
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			x = se.values[0];
			float rotationmodifier = x/5;
			float newrotation = (float) (gameview.rocketgame.getRocket().getRotation() + rotationmodifier);
			if(newrotation < 45 && newrotation > -45){
				gameview.rocketgame.getRocket().setRotation(gameview.rocketgame.getRocket().getRotation() + rotationmodifier);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameview = (GameView) findViewById(R.id.gameActivity1);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	public void initialiseerImages() {

	}

}
