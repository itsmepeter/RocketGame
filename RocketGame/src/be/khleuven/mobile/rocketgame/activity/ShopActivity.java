package be.khleuven.mobile.rocketgame.activity;

import com.example.rocketgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class ShopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    public void onClickBack(View view){
    	setContentView(R.layout.activity_menu);
    }
    
}
