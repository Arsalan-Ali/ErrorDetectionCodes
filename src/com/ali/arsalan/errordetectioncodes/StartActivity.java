package com.ali.arsalan.errordetectioncodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends ActionBarActivity {
	private static final String ABOUT_DIALOG_TAG = "com.ali.arsalan.errordetectioncodes.ABOUT_DIALOG_TAG";
	private Button hamming;
	private Button crc;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		hamming=(Button)this.findViewById(R.id.hamming);
		crc=(Button)this.findViewById(R.id.crc);
		
		hamming.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(StartActivity.this, HammingActivity.class);
				startActivity(intent);
				
			}
		});
		
		crc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
					
					Intent intent=new Intent(StartActivity.this, CRCActivity.class);
					startActivity(intent);
					
				}
				
			
		});
		
	}
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

}
