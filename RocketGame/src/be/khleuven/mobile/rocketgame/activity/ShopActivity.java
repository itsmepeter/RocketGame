package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ShopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		        
        setContentView(R.layout.activity_shop);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    public void onClickBack(View view){
		Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
		startActivityForResult(myIntent, 0);
		overridePendingTransition(R.anim.animation_enter2, R.anim.animation_leave2);
    }
    
	public void onClickStart(View view) {
		Intent myIntent = new Intent(view.getContext(), GameActivity.class);
		startActivityForResult(myIntent, 0);
		overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
        overridePendingTransition(R.anim.animation_enter2, R.anim.animation_leave2);
    }
}
