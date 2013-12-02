package be.khleuven.mobile.rocketgame.view;


import be.khleuven.mobile.rocketgame.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	
    private Canvas canvas;
    private Bitmap canvasbitmap;
    private Bitmap bmprocket;
    
    private int width;
    private int height;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		//bmprocket = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);     
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
		canvasbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); 
		canvas = new Canvas(canvasbitmap); 
		height = h;
		width = w;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		canvas.drawColor(Color.BLUE);	
		canvas.drawRect(new Rect(0,0,20,20), new Paint(Color.BLACK));
		
		
		Paint p=new Paint();
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
        p.setColor(Color.RED);
        canvas.drawBitmap(b, (width/2) - b.getWidth()/2, height-b.getHeight(), p);
        
        
		drawRocket();
        invalidate();
	}	
	
	public void drawRocket(){
        //canvas.drawBitmap(bmprocket, 50, 50, null);  
	}
}
