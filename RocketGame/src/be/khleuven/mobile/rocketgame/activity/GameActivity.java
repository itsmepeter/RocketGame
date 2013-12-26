package be.khleuven.mobile.rocketgame.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.model.Cloud;
import be.khleuven.mobile.rocketgame.model.Jet;
import be.khleuven.mobile.rocketgame.view.GameView;

public class GameActivity extends Activity {
	GameView gameview;
	private Timer cloudtimer;
	private TimerTask refresher;

	private SensorManager mSensorManager;
	private float x; // acceleration apart from gravity
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			x = se.values[0];
			float rotationmodifier = x/2;
			float newrotation = (float) (gameview.rocketgame.getRocket().getRotation() + rotationmodifier);
			float newX = (float) (gameview.rocketgame.getRocket().getX() - gameview.rocketgame.getRocket().getRotation()/10);
			if(newrotation < 45 && newrotation > -45){
				gameview.rocketgame.getRocket().setRotation(newrotation);
			}
			if(newX < 0.65*gameview.width && newX > 0.1*gameview.width){
				gameview.rocketgame.getRocket().setX((int) newX);
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
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		gameview = (GameView) findViewById(R.id.gameActivity1);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		cloudtimer = new Timer();    
		refresher = new TimerTask() {
		    public void run() {
		    	//clouds
		       double cloudrandom = Math.random();
		       if(cloudrandom < 0.2 && gameview.getHeight() < 17000 && gameview.clouds.size()<=5){
		    	   double cloudrandom2 = Math.random();
		    	   int randomx = (int) ((-1*gameview.width) + (Math.random()*gameview.width*2));
		           int y = -250;
		           Cloud cloud;
		           if(cloudrandom2 < 0.25){
		        	   cloud = new Cloud(randomx, y, gameview.cloud1, 0);
		           }else if (cloudrandom2 < 0.50){
		        	   cloud = new Cloud(randomx, y, gameview.cloud2, 0);
		           }else if (cloudrandom2 < 0.75){
		        	   cloud = new Cloud(randomx, y, gameview.cloud3, 0); 
		           }else{
		        	   cloud = new Cloud(randomx, y, gameview.cloud4, 0);
		           }
		           gameview.clouds.add(cloud);
		       }
		       
		       
		       //planes
		       double obstaclerandom = Math.random();
		       if(obstaclerandom < 0.01 && gameview.jets.size() < 1 && gameview.getHeight() > 750 && gameview.getHeight() < 12000){
		    	   int randomx = (int) ((-1*gameview.width) + (Math.random()*gameview.width*2));
		    	   int y = -250;
		    	   Jet jet = new Jet(randomx, y, gameview.jet, 0);
			       gameview.jets.add(jet);
			    }
		    };
		};
		// first event immediately,  following after 1 seconds each
		cloudtimer.scheduleAtFixedRate(refresher, 0, 100);
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
        overridePendingTransition(R.anim.animation_enter2, R.anim.animation_leave2);
    }

	public void initialiseerImages() {

	}

}
