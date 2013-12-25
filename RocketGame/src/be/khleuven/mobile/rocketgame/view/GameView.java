package be.khleuven.mobile.rocketgame.view;

import java.util.ArrayList;

import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.model.Cloud;
import be.khleuven.mobile.rocketgame.model.RocketGame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	//alle afbeeldingen in het geheugen
	private Bitmap canvasbitmap;
	public Bitmap bmprocket;
	public Bitmap cloud1;
	public Bitmap cloud2;
	public Bitmap cloud3;
	public Bitmap cloud4;
	

	// Rocket
	public RocketGame rocketgame;

	public int width;
	public int height;
	
	public ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public ArrayList<Cloud>toberemoved = new ArrayList<Cloud>();



	// paints
	private Paint p;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);

		p = new Paint();
		rocketgame = new RocketGame();

		
		bmprocket = BitmapFactory.decodeResource(getResources(),
				R.drawable.fullrocket);
		
		cloud1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.cloud1);
		
		cloud2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.cloud2);
		
		cloud3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.cloud3);
		
		cloud4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.cloud4);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		new Canvas(canvasbitmap);
		height = h;
		width = w;
		

		
		Bitmap oldrocket = bmprocket;
		Bitmap oldcloud1 = cloud1;
		Bitmap oldcloud2 = cloud2;
		Bitmap oldcloud3 = cloud3;
		Bitmap oldcloud4 = cloud4;
		
		//HIER ZOU EIGENLIJK ALLES GESCALED MOETEN WORDEN MAAR IK BEN EEN NOOB IN WISKUNDE
		
		bmprocket = Bitmap.createScaledBitmap(oldrocket, (int) 85, (int) 150,true);
		oldcloud1 = Bitmap.createScaledBitmap(oldcloud1, (int) 30, (int) 30,true);;
		oldcloud2 = Bitmap.createScaledBitmap(oldcloud2, (int) 30, (int) 30,true);;
		oldcloud3 = Bitmap.createScaledBitmap(oldcloud3, (int) 30, (int) 30,true);;
		oldcloud4 = Bitmap.createScaledBitmap(oldcloud4, (int) 30, (int) 30,true);;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		rocketgame.setHeight(rocketgame.getHeight()+1);
		
		canvas.drawColor(Color.BLUE);

		// haal images op van rocket, en update positie door
		// accelerometervariables mee te geven

		p.setColor(Color.WHITE);

		// eerste run
		if (rocketgame.getRocket().getY() == 0) {
			rocketgame.getRocket().setX((width / 2) - bmprocket.getWidth() / 2);
			rocketgame.getRocket().setY(height - bmprocket.getHeight());
			
		}
	

		canvas.drawBitmap(bmprocket, generateRotationMatrix(), p);
		canvas.drawText("HEIGHT: " + (int)rocketgame.getHeight(), 10, 20, p);
		canvas.drawText("HEALTH: " + (int)rocketgame.getRocket().getHealth(), 10, 40, p);
		
		
		for(Cloud cloud : clouds){
			if(cloud.getY() > height){
				toberemoved.add(cloud);
            }else{
                cloud.setY(cloud.getY() + 7);
                cloud.setX((int) (cloud.getX() + rocketgame.getRocket().getRotation()/10 + 1));
                canvas.drawBitmap(cloud.getImage(), cloud.getX(), cloud.getY(), p);
            }
		}
		
		for(Cloud cloud:toberemoved){
            clouds.remove(cloud);
		}
		toberemoved.clear();

		invalidate();
		//rocket.recycle();
	}
	
	private Matrix generateRotationMatrix()
	{		
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setTranslate(rocketgame.getRocket().getX() , height - bmprocket.getHeight());
		matrix.preRotate((float) - rocketgame.getRocket().getRotation(),bmprocket.getWidth()/2,bmprocket.getHeight()/2);
		return matrix;
	}
}
