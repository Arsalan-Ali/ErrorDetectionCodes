package com.ali.arsalan.errordetectioncodes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetectionActivity extends ActionBarActivity{

	private Button calculate;
	private EditText codeWord;
	private EditText err_pos;
	private DataCentre dataCentre;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detection);
		calculate=(Button)findViewById(R.id.calculate);
		codeWord=(EditText)findViewById(R.id.codeword);
		err_pos=(EditText)findViewById(R.id.err_pos);
		dataCentre=DataCentre.getInstance();
		codeWord.setText(dataCentre.getCodeWord());
		codeWord.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            	dataCentre.setCodeWord(c.toString());
            	
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
            	
            	err_pos.setText("");
            	for(int i=0;i<c.length();i++)
		            if(c.charAt(i)!='0' && c.charAt(i)!='1'){
		               	c.delete(i, i+1);
		               	
		            }
            	 if(c.length()>24){
		               	c.delete(24, c.length());
		               	
		         }
           
            	 
            }
		});
		calculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try{
					int pos=dataCentre.calcErrPos();
					
					err_pos.setText(""+pos);
				}
				catch(Exception e){
					
				}
			}
		});
	}
}
