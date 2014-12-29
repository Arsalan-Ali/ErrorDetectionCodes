package com.ali.arsalan.errordetectioncodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HammingActivity extends ActionBarActivity{
	
	private Button codeWord;
	private Button detection;
	private static final String ABOUT_DIALOG_TAG = "com.ali.arsalan.errordetectioncodes.ABOUT_DIALOG_TAG";
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.about) {
			AboutDialogFragment aboutDialogFragment=new AboutDialogFragment();
			aboutDialogFragment.show(getSupportFragmentManager(), ABOUT_DIALOG_TAG);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hamming);
		codeWord=(Button)this.findViewById(R.id.codeword);
		detection=(Button)this.findViewById(R.id.detection);
		
		codeWord.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent=new Intent(HammingActivity.this, DataWordActivity.class);
				startActivity(intent);
				
			}
		});
		
		detection.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
					
					
					Intent intent=new Intent(HammingActivity.this, DetectionActivity.class);
					startActivity(intent);
					
				}
				
			
		});
	}

}
