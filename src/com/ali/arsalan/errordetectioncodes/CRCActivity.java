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

public class CRCActivity extends ActionBarActivity {
	private DataCentre dataCentre;
	private EditText msgBits;
	private EditText msgBitsSent;
	private EditText pattern;
	private EditText fcs;
	private static final String ABOUT_DIALOG_TAG = "com.ali.arsalan.errordetectioncodes.ABOUT_DIALOG_TAG";
	private Button calculate;
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
		setContentView(R.layout.activity_crc);
		dataCentre=DataCentre.getInstance();
		msgBits=(EditText)this.findViewById(R.id.msgbits);
		msgBitsSent=(EditText)this.findViewById(R.id.msgbits_sent);
		pattern=(EditText)this.findViewById(R.id.patternbits);
		fcs=(EditText)this.findViewById(R.id.fcs);
		
		calculate=(Button)this.findViewById(R.id.calculate);
		msgBits.setText(dataCentre.getMsgBits());
		msgBits.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            	dataCentre.setMsgBits(c.toString());  
            	
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
            
              	for(int i=0;i<c.length();i++)
		            if(c.charAt(i)!='0' && c.charAt(i)!='1'){
		               	c.delete(i, i+1);
		               	
		            }
            	 if(c.length()>16){
		               	c.delete(16, c.length());
		               	
		         }
            	 
            
            	 if(fcs.getText().length()>0){
	            	 fcs.setText("");
            	 }
            	 if(msgBitsSent.getText().length()>0){
	 				 msgBitsSent.setText("");
            	 }
            		 
            	 
            }
		});
		pattern.setText(dataCentre.getPattern());
		pattern.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            	dataCentre.setPattern(c.toString());         
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {           
            	            		 
            		 
            		 if(fcs.getText().length()>0){
    	            	 fcs.setText("");
                	 }
                	 if(msgBitsSent.getText().length()>0){
    	 				 msgBitsSent.setText("");
                	 }
	            	for(int i=0;i<c.length();i++)
			            if(c.charAt(i)!='0' && c.charAt(i)!='1'){
			               	c.delete(i, i+1);
			               	
			            }
	            
	            	 if(c.length()>15){
			               	c.delete(15, c.length());
			               	
			         }
	            	
	            	
	            
            }
		});
		
		
		calculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					dataCentre.calculateFCS();					
					fcs.setText(dataCentre.getFcs());					
					msgBitsSent.setText(dataCentre.getMsgBitsSent());
				}
				catch(Exception ex){
					
				}
				
				
				
			}
		});
		
	}

	
}
