package be.khleuven.mobile.rocketgame.view;


import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.model.Rocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

public class GameView extends View implements SensorEventListener {
	
    private Canvas canvas;
    private Bitmap canvasbitmap;
    private Bitmap bmprocket;
    
    //accelerometer
	private SensorManager sm;
	private Sensor accelerometer;	
    private float accelerometerX;
    private float accelerometerY;
    private float accelerometerZ;
    private long accelerometerEventTijd;
	private Display display;
	
	//Rocket
	private Rocket rocket;
    
    private int width;
    private int height;
    
    
    //paints
    private Paint p;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		
		WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		display = mWindowManager.getDefaultDisplay();
		//initialiseer accelerometer en sensormanager
		sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
		canvasbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); 
		canvas = new Canvas(canvasbitmap); 
		height = h;
		width = w;
		
		rocket = new Rocket();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		canvas.drawColor(Color.BLUE);	

		// haal images op van rocket, en update positie door accelerometervariables mee te geven
		p=new Paint();
        bmprocket =BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
        p.setColor(Color.RED);
        
		//eerste run
		if(rocket.getY() == 0){
			rocket.setX((width/2) - bmprocket.getWidth()/2);
			rocket.setY(height-bmprocket.getHeight());
		}
        rocket.updatePosition(accelerometerX, accelerometerY, accelerometerZ, accelerometerEventTijd);
        canvas.drawBitmap(bmprocket, rocket.getX(), rocket.getY(), p);
       
        invalidate();
	}	
	

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Log.v("000","0000000000" + event);
		  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
	        	switch (display.getRotation()) {
	    		case Surface.ROTATION_0:
	    			accelerometerX = event.values[0];
	    			accelerometerY = event.values[1];
	    			break;
	    		case Surface.ROTATION_90:
	    			accelerometerX = -event.values[1];
	    			accelerometerY = event.values[0];
	    			break;
	    		case Surface.ROTATION_180:
	    			accelerometerX = -event.values[0];
	    			accelerometerY = -event.values[1];
	    			break;
	    		case Surface.ROTATION_270:
	    			accelerometerX = event.values[1];
	    			accelerometerY = -event.values[0];

	    			break;
	    		}
	    		accelerometerZ = event.values[2];
	    		accelerometerEventTijd = event.timestamp;
	        } 	
		
	}
	
	public void startSpel() {
		sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
	}

	public void stopSpel() {
		sm.unregisterListener(this);
	}
}
