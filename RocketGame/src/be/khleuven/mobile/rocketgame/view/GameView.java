package be.khleuven.mobile.rocketgame.view;

import java.util.ArrayList;

import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.activity.GameActivity;
import be.khleuven.mobile.rocketgame.activity.GameOverActivity;
import be.khleuven.mobile.rocketgame.activity.ShopActivity;
import be.khleuven.mobile.rocketgame.model.Bird;
import be.khleuven.mobile.rocketgame.model.Cloud;
import be.khleuven.mobile.rocketgame.model.Jet;
import be.khleuven.mobile.rocketgame.model.RocketGame;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
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
	public Bitmap jet;
	public Bitmap bird;
	

	// Rocket
	public RocketGame rocketgame;

	public int width;
	public int height;
	
	public ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public ArrayList<Cloud>toberemoved = new ArrayList<Cloud>();

	public ArrayList<Jet> jets = new ArrayList<Jet>();
	public ArrayList<Bird> birds = new ArrayList<Bird>();


	// paints
	private Paint p;
	
	//context
	private Context context;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		this.context = context;
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
		
		jet = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
		
		bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);

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
		Bitmap oldplane = jet;
		Bitmap oldbird = bird;
		
		//HIER ZOU EIGENLIJK ALLES GESCALED MOETEN WORDEN MAAR IK BEN EEN NOOB IN WISKUNDE
		
		bmprocket = Bitmap.createScaledBitmap(oldrocket, (int) 85, (int) 150,true);
		cloud1 = Bitmap.createScaledBitmap(oldcloud1, (int) 200, (int) 200,true);
		cloud2 = Bitmap.createScaledBitmap(oldcloud2, (int) 200, (int) 200,true);
		cloud3 = Bitmap.createScaledBitmap(oldcloud3, (int) 200, (int) 200,true);
		cloud4 = Bitmap.createScaledBitmap(oldcloud4, (int) 200, (int) 200,true);
		jet = Bitmap.createScaledBitmap(oldplane, (int) 200, (int) 200, true);
		bird = Bitmap.createScaledBitmap(oldbird, (int) 60, (int) 60, true);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(rocketgame.getRocket().getHealth() > 0){
			rocketgame.setHeight(rocketgame.getHeight()+1);
			
			canvas.drawColor(Color.BLUE);

			// haal images op van rocket, en update positie door
			// accelerometervariables mee te geven

			p.setColor(Color.WHITE);

			// eerste run
			if (rocketgame.getRocket().getY() == 0) {
				rocketgame.getRocket().setX((width / 2) - bmprocket.getWidth() / 2);
				rocketgame.getRocket().setY(height + bmprocket.getHeight()/2);
				
			}
			
			//startanimatie
			if(rocketgame.getRocket().getY() > (0.70 * height)){
				rocketgame.getRocket().setY(rocketgame.getRocket().getY()-1);
	        }
		

			canvas.drawBitmap(bmprocket, generateRotationMatrix(), p);
			canvas.drawText("HEIGHT: " + (int)rocketgame.getHeight(), 10, 20, p);
			canvas.drawText("HEALTH: " + (int)rocketgame.getRocket().getHealth(), 10, 40, p);
			
			//clouds
			for(int i = 0; i<clouds.size();i++){
				if(clouds.get(i).getY() < height){
					clouds.get(i).setY(clouds.get(i).getY() + 7);
					clouds.get(i).setX((int) (clouds.get(i).getX() + rocketgame.getRocket().getRotation()/10 + 1));
	                canvas.drawBitmap(clouds.get(i).getImage(), clouds.get(i).getX(), clouds.get(i).getY(), p);
	            }else{
	            	clouds.remove(i);
	            	i--;
	            }
			}
			
			//jets
			for(int i = 0; i< jets.size();i++){
			   if(jets.get(i).getX() < rocketgame.getRocket().getX() && jets.get(i).getX() + 600 > rocketgame.getRocket().getX() && jets.get(i).getY() + 252 > rocketgame.getRocket().getY() && jets.get(i).getY()+252 < rocketgame.getRocket().getY()+60){
	               rocketgame.getRocket().setHealth(rocketgame.getRocket().getHealth() - jets.get(i).getDmg());
	               jets.get(i).setDmg(0);
	               if(context instanceof GameActivity)
	               {
	            	   GameActivity activity = (GameActivity)context;
	            	   activity.hitJetSound(i);
	               }
	               
	           }  
				if(jets.get(i).getY() < height){
					jets.get(i).setY(jets.get(i).getY() + 7);
					jets.get(i).setX((int) (jets.get(i).getX() + rocketgame.getRocket().getRotation()/10 + 3));
	                canvas.drawBitmap(jets.get(i).getImage(), jets.get(i).getX(), jets.get(i).getY(), p);
	            }else{
	            	jets.remove(i);
	            	i--;
	            }
			}
			
			//birds
					for(int i = 0; i< birds.size();i++){
					   if(birds.get(i).getX() + 20 < rocketgame.getRocket().getX() && birds.get(i).getX() + 100 > rocketgame.getRocket().getX() && birds.get(i).getY() + 60 > rocketgame.getRocket().getY() && birds.get(i).getY()+60 < rocketgame.getRocket().getY()+60){
			               rocketgame.getRocket().setHealth(rocketgame.getRocket().getHealth() - birds.get(i).getDmg());
			               birds.get(i).setDmg(0);
			               if(context instanceof GameActivity)
			               {
			            	   GameActivity activity = (GameActivity)context;
			                   
			                   activity.hitBirdSound(i);
			               }
			               
			           }  
						if(birds.get(i).getY() < height){
							birds.get(i).setY(birds.get(i).getY() + 7);
							birds.get(i).setX((int) (birds.get(i).getX() + rocketgame.getRocket().getRotation()/10 + 3));
			                canvas.drawBitmap(birds.get(i).getImage(), birds.get(i).getX(), birds.get(i).getY(), p);
			            }else{
			            	birds.remove(i);
			            	i--;
			            }
					}
		} else {
			   if(context instanceof GameActivity)
               {
            	   GameActivity activity = (GameActivity)context;
            	   activity.gameOver(this);
               }

		}

		invalidate();
		//rocket.recycle();
	}
	
	private Matrix generateRotationMatrix()
	{		
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setTranslate(rocketgame.getRocket().getX() , rocketgame.getRocket().getY());
		matrix.preRotate((float) - rocketgame.getRocket().getRotation(),bmprocket.getWidth()/2,bmprocket.getHeight()/2);
		return matrix;
	}
}
