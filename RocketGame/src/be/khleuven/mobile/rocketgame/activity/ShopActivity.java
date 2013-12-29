package be.khleuven.mobile.rocketgame.activity;

import be.khleuven.mobile.rocketgame.R;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends Activity {
	private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		        
        setContentView(R.layout.activity_shop);
        
        prefs = this.getSharedPreferences("be.khleuven.mobile.rocketgame", Context.MODE_PRIVATE);
        
        loadTextViews();
    }


	private void loadTextViews() {
		TextView moneyview = (TextView)findViewById(R.id.money);
        
        TextView health = (TextView)findViewById(R.id.textView9);
        TextView next_health = (TextView)findViewById(R.id.textView10);
        TextView health_cost = (TextView)findViewById(R.id.textView15);
        
        TextView fuel = (TextView)findViewById(R.id.textView11);
        TextView next_fuel = (TextView)findViewById(R.id.textView8);
        TextView fuel_cost = (TextView)findViewById(R.id.textView14);
        
        TextView engine = (TextView)findViewById(R.id.textView13);
        TextView next_engine = (TextView)findViewById(R.id.textView12);
        TextView engine_cost = (TextView)findViewById(R.id.textView16);


		String text = "Money: " + prefs.getInt("money", 0);
		
		moneyview.setText(text);
		
		health.setText(prefs.getInt("health", 100) + "");
		next_health.setText(prefs.getInt("health", 100) + 10 + "");
		health_cost.setText(prefs.getInt("health", 100)*10 + "");

		fuel.setText(prefs.getInt("fuel", 1000) + "");
		next_fuel.setText(prefs.getInt("fuel", 1000)+ 1000 + "");
		fuel_cost.setText(prefs.getInt("fuel", 1000) + "");
		
		engine.setText(prefs.getInt("engine",30) + "");
		next_engine.setText(prefs.getInt("engine",30) + 10 + "");
		engine_cost.setText(prefs.getInt("engine",30)*30 + "");
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
	
	private void savePreferences(String key, int value) {
        Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
	}
	
	public void onclickFuel(View view){
		int money = prefs.getInt("money", 0);
		if(money > prefs.getInt("fuel", 1000)){
			savePreferences("money", money - prefs.getInt("fuel", 1000));
			savePreferences("fuel", prefs.getInt("fuel", 1000)+1000);
			loadTextViews();
		}else{
			showToast();
		}
		
	}
	
	public void onclickHealth(View view){
		int money = prefs.getInt("money", 0);
		if(money > prefs.getInt("health", 100)*10){
			savePreferences("money", money - prefs.getInt("health", 100)*10);
			savePreferences("health", prefs.getInt("health", 100)+10);
			loadTextViews();
		}else{
			showToast();
		}
	}
	
	public void onclickEngine(View view){
		int money = prefs.getInt("money", 0);
		if(money > prefs.getInt("engine",30)*30){
			savePreferences("money", money - prefs.getInt("engine",30)*30);
			savePreferences("engine", prefs.getInt("engine",30)+10);
			loadTextViews();
		}else{
			showToast();
		}
	}
	
	private void showToast(){
		Context context = getApplicationContext();
		CharSequence text = "Not enough money!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
