package be.khleuven.mobile.rocketgame.view;

import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.model.RocketGame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

	private Bitmap canvasbitmap;
	public Bitmap bmprocket;

	// Rocket
	public RocketGame rocketgame;

	private int width;
	private int height;

	public Matrix rotator;

	// paints
	private Paint p;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);

		p = new Paint();
		rocketgame = new RocketGame();

		
		bmprocket = BitmapFactory.decodeResource(getResources(),
				R.drawable.fullrocket);
		rotator = new Matrix();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		new Canvas(canvasbitmap);
		height = h;
		width = w;


	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLUE);

		// haal images op van rocket, en update positie door
		// accelerometervariables mee te geven

		p.setColor(Color.RED);

		// eerste run
		if (rocketgame.getRocket().getY() == 0) {
			Log.v("000", rocketgame.getRocket().getX() + " "
					+ rocketgame.getRocket().getY());
			rocketgame.getRocket().setX((width / 2) - bmprocket.getWidth() / 2);
			rocketgame.getRocket().setY(height - bmprocket.getHeight());
		}
	
		
		Bitmap rocket = generateRotatedRocket();
		

		canvas.drawBitmap(rocket, rocketgame.getRocket().getX(), rocketgame
				.getRocket().getY(), p);
		canvas.drawText(rocketgame.getRocket().getRotation() + "", width / 2,
				height / 2, p);

		invalidate();
		rocket.recycle();
	}
	
	private Bitmap generateRotatedRocket()
	{
		
		double rotation = rocketgame.getRocket().getRotation() + rocketgame.getRocket().getRotationspeed();
		
		if(rotation < 60 && rotation > -60){
			rocketgame.getRocket().setRotation(rotation);
		}
		
		rotator.postRotate((float) rocketgame.getRocket().getRotation());

		return Bitmap.createBitmap(bmprocket, 0, 0, bmprocket.getWidth(), bmprocket.getHeight(), rotator, true);
	}
}
