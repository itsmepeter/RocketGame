package be.khleuven.mobile.rocketgame.view;

import java.util.ArrayList;

import be.khleuven.mobile.rocketgame.R;
import be.khleuven.mobile.rocketgame.activity.GameActivity;
import be.khleuven.mobile.rocketgame.model.Bird;
import be.khleuven.mobile.rocketgame.model.Cloud;
import be.khleuven.mobile.rocketgame.model.Jet;
import be.khleuven.mobile.rocketgame.model.Money;
import be.khleuven.mobile.rocketgame.model.RocketGame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	// alle afbeeldingen in het geheugen
	private Bitmap canvasbitmap;
	public Bitmap bmprocket;
	public Bitmap cloud1;
	public Bitmap cloud2;
	public Bitmap cloud3;
	public Bitmap cloud4;
	public Bitmap jet;
	public Bitmap bird;
	public Bitmap star;

	// Rocket
	public RocketGame rocketgame;

	public int width;
	public int height;

	public ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public ArrayList<Cloud> toberemoved = new ArrayList<Cloud>();

	public ArrayList<Jet> jets = new ArrayList<Jet>();
	public ArrayList<Bird> birds = new ArrayList<Bird>();

	public ArrayList<Money> stars = new ArrayList<Money>();

	// paints
	private Paint p;

	// context
	private Context context;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		this.context = context;
		p = new Paint();
		rocketgame = new RocketGame();

		bmprocket = BitmapFactory.decodeResource(getResources(),
				R.drawable.fullrocket);

		cloud1 = BitmapFactory
				.decodeResource(getResources(), R.drawable.cloud1);

		cloud2 = BitmapFactory
				.decodeResource(getResources(), R.drawable.cloud2);

		cloud3 = BitmapFactory
				.decodeResource(getResources(), R.drawable.cloud3);

		cloud4 = BitmapFactory
				.decodeResource(getResources(), R.drawable.cloud4);

		jet = BitmapFactory.decodeResource(getResources(), R.drawable.plane);

		bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);

		star = BitmapFactory.decodeResource(getResources(), R.drawable.star);

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

		// HIER ZOU EIGENLIJK ALLES GESCALED MOETEN WORDEN MAAR IK BEN EEN NOOB
		// IN WISKUNDE

		bmprocket = Bitmap.createScaledBitmap(oldrocket, (int) 85, (int) 150,
				true);
		cloud1 = Bitmap.createScaledBitmap(oldcloud1, (int) 200, (int) 200,
				true);
		cloud2 = Bitmap.createScaledBitmap(oldcloud2, (int) 200, (int) 200,
				true);
		cloud3 = Bitmap.createScaledBitmap(oldcloud3, (int) 200, (int) 200,
				true);
		cloud4 = Bitmap.createScaledBitmap(oldcloud4, (int) 200, (int) 200,
				true);
		jet = Bitmap.createScaledBitmap(oldplane, (int) 200, (int) 200, true);
		bird = Bitmap.createScaledBitmap(oldbird, (int) 60, (int) 60, true);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (rocketgame.getRocket().getHealth() > 0 && rocketgame.getRocket().getSpeed() > -10) {
			if(rocketgame.getTouching() == true && rocketgame.getRocket().getFuel() > 0 ){
		    	if(rocketgame.getRocket().getSpeed() < 30 && rocketgame.getRocket().getFuel() > 0){
		    	  	rocketgame.getRocket().setSpeed(rocketgame.getRocket().getSpeed() + 0.2);	
		    	}
		    	rocketgame.getRocket().setFuel((int) (rocketgame.getRocket().getFuel() - 1));
		    } else if (rocketgame.getTouching() == false && rocketgame.getRocket().getSpeed() > -10 && rocketgame.getHeight() > 0){
		    		rocketgame.getRocket().setSpeed(rocketgame.getRocket().getSpeed() - 0.3);
		    }
			rocketgame.setHeight((rocketgame.getHeight() + rocketgame.getRocket().getSpeed()/60));			
			if (context instanceof GameActivity) {
				GameActivity activity = (GameActivity) context;
				if (activity.isSignedIn() && rocketgame.getBirds_hit() == 9) {
			        activity.getGamesClient().unlockAchievement(getResources().getString(R.string.achievement_kill_all_the_twitter_birds));
			      }
			}
			
			

			canvas.drawColor(Color.BLUE);

			// haal images op van rocket, en update positie door
			// accelerometervariables mee te geven

			p.setColor(Color.WHITE);

			// eerste run
			if (rocketgame.getRocket().getY() == 0) {
				rocketgame.getRocket().setX(
						(width / 2) - bmprocket.getWidth() / 2);
				rocketgame.getRocket().setY(height - bmprocket.getHeight());

			}

			// startanimatie
			if (rocketgame.getRocket().getY() > (0.60 * height) && rocketgame.getTouching() == true) {
				rocketgame.getRocket().setY(rocketgame.getRocket().getY() - 1);
			}

			if(rocketgame.getRocket().getFuel() < 0){
				rocketgame.getRocket().setY(rocketgame.getRocket().getY() + 1);
			}

			canvas.drawBitmap(bmprocket, generateRotationMatrix(), p);
			canvas.drawText("HEIGHT: " + (int) rocketgame.getHeight(), 10, 20,
					p);
			canvas.drawText("HEALTH: "
					+ (int) rocketgame.getRocket().getHealth(), 10, 40, p);
			canvas.drawText("MONEY: " + (int) rocketgame.getMoney(), 10, 60, p);
			canvas.drawText("SPEED: " + (int) rocketgame.getRocket().getSpeed(), 10, 80, p);
			canvas.drawText("FUEL: " + (int) rocketgame.getRocket().getFuel(), 10, 100, p);

			// clouds
			for (int i = 0; i < clouds.size(); i++) {
				if (clouds.get(i).getY() < height) {
                    clouds.get(i).setY((clouds.get(i).getY() +  rocketgame.getRocket().getSpeed()/4));
					clouds.get(i)
							.setX((int) (clouds.get(i).getX()
									+ rocketgame.getRocket().getRotation() / 10 + 1));
					canvas.drawBitmap(clouds.get(i).getImage(), clouds.get(i)
							.getX(), (float) clouds.get(i).getY(), p);
				} else {
					clouds.remove(i);
					i--;
				}
			}

			// jets
			for (int i = 0; i < jets.size(); i++) {
				if (jets.get(i).getX() + jet.getWidth() / 2 < rocketgame
						.getRocket().getX() + bmprocket.getWidth()
						&& jets.get(i).getX() + jet.getWidth() / 2 > rocketgame
								.getRocket().getX()
						&& jets.get(i).getY() + jet.getHeight() / 2 > rocketgame
								.getRocket().getY()
						&& jets.get(i).getY() + jet.getHeight() / 2 < rocketgame
								.getRocket().getY() + bmprocket.getHeight()) {
					rocketgame.getRocket().setHealth(
							rocketgame.getRocket().getHealth()
									- jets.get(i).getDmg());
					jets.get(i).setDmg(0);
					if (context instanceof GameActivity) {
						GameActivity activity = (GameActivity) context;
						activity.hitJetSound(i);
					}
					jets.remove(i);

				} else {
					if (jets.get(i).getY() < height) {
                        jets.get(i).setY((jets.get(i).getY() +  rocketgame.getRocket().getSpeed()/4));
						jets.get(i)
								.setX((int) (jets.get(i).getX()
										+ rocketgame.getRocket().getRotation() / 10 + 3));
						canvas.drawBitmap(jets.get(i).getImage(), jets.get(i)
								.getX(), (float) jets.get(i).getY(), p);
					} else {
						jets.remove(i);
						i--;
					}
				}
			
			}

			// birds
			for (int i = 0; i < birds.size(); i++) {
				if (birds.get(i).getX() + bird.getWidth() / 2 < rocketgame
						.getRocket().getX() + bmprocket.getWidth()
						&& birds.get(i).getX() + bird.getWidth() / 2 > rocketgame
								.getRocket().getX()
						&& birds.get(i).getY() + bird.getHeight() / 2 > rocketgame
								.getRocket().getY()
						&& birds.get(i).getY() + bird.getHeight() / 2 < rocketgame
								.getRocket().getY() + bmprocket.getHeight()) {
					rocketgame.getRocket().setHealth(
							rocketgame.getRocket().getHealth()
									- birds.get(i).getDmg());
					if (context instanceof GameActivity) {
						GameActivity activity = (GameActivity) context;

						activity.hitBirdSound(i);
					}
					birds.get(i).setDmg(0);
					birds.remove(i);
					rocketgame.setBirds_hit(rocketgame.getBirds_hit()+1);
				} else {
					if (birds.get(i).getY() < height) {
                        birds.get(i).setY((birds.get(i).getY() +  rocketgame.getRocket().getSpeed()/4));
						birds.get(i)
								.setX((int) (birds.get(i).getX()
										+ rocketgame.getRocket().getRotation() / 10 + 3));
						canvas.drawBitmap(birds.get(i).getImage(), birds.get(i)
								.getX(), (float) birds.get(i).getY(), p);
					} else {
						birds.remove(i);
						i--;
					}
				}
				
			}
			

			for (int i = 0; i < stars.size(); i++) {
				if (stars.get(i).getX() + star.getWidth() / 2 < rocketgame
						.getRocket().getX() + bmprocket.getWidth()
						&& stars.get(i).getX() + star.getWidth() / 2 > rocketgame
								.getRocket().getX()
						&& stars.get(i).getY() + star.getHeight() / 2 > rocketgame
								.getRocket().getY()
						&& stars.get(i).getY() + star.getHeight() / 2 < rocketgame
								.getRocket().getY() + bmprocket.getHeight()) {
					rocketgame.setMoney(rocketgame.getMoney() + 20);
					stars.get(i).setDmg(0);
					if (context instanceof GameActivity) {
						GameActivity activity = (GameActivity) context;
						activity.hitStarSound(i);
						stars.remove(i);
					}

				} else {
					if (stars.get(i).getY() < height && rocketgame.getHeight() > 0) {
                        stars.get(i).setY((stars.get(i).getY() + 5 +  rocketgame.getRocket().getSpeed()/4));
						stars.get(i).setX(
								(int) (stars.get(i).getX() + rocketgame
										.getRocket().getRotation() / 10));
						canvas.drawBitmap(stars.get(i).getImage(), stars.get(i)
								.getX(), (float) stars.get(i).getY(), p);
					} else {
						stars.remove(i);
						i--;
					}
				}
			}

			invalidate();
			
		} else {
			if (context instanceof GameActivity) {
				GameActivity activity = (GameActivity) context;
				activity.gameOver(this);
			}

		}
	}

	private Matrix generateRotationMatrix() {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setTranslate(rocketgame.getRocket().getX(), rocketgame
				.getRocket().getY());
		matrix.preRotate((float) -rocketgame.getRocket().getRotation(),
				bmprocket.getWidth() / 2, bmprocket.getHeight() / 2);
		return matrix;
	}
	
	@Override 
	public boolean onTouchEvent(MotionEvent event){
		   if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
			    rocketgame.setTouching(true);
			    rocketgame.setPlaying(true);
		    } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
		    	rocketgame.setTouching(false);
		    }
		return true;	
	}
}
