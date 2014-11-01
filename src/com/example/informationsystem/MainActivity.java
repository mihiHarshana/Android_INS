package com.example.informationsystem;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
public DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        Button btnLogin = (Button)  findViewById(R.id.btnLogin);
//        
//        btnLogin.setOnClickListener( new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this, AddPatientData.class);
//				
//				
//				
//				
//				Log.d("Strating second one", "What will happen'");
//				MainActivity.this.startActivity(intent);
//				
//				
//				
//			}
//		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
         
    public void Login(View view) {
    	  
    	   
     	EditText txtUName = (EditText) findViewById(R.id.txtUserName);
     	EditText txtPword =(EditText) findViewById(R.id.txtPasswprd);
//     	txtUName.setText("admin");
//     	txtPword.setText("admin");
    	
    	if (txtUName.getText().toString().matches("")) {
    		//lblUName.setText("Invalid User Name");
    		Toast.makeText(this, "User Name cannot be blank" , Toast.LENGTH_SHORT).show();
    		
    	} else if (txtPword.getText().toString().matches("")) {
    		Toast.makeText(this, "Password cannot be blank" , Toast.LENGTH_SHORT).show();
    	}
    	else { 
    		
    		Users users = db.getUser(txtUName.getText().toString());
    		
    		if (users != null) {
    			
    			if (txtUName.getText().toString().matches(users.getUname())) {
    				if (txtPword.getText().toString().matches(users.getUpword())) {
    					Intent intent  = new Intent (this, AddPatientData.class);
    					startActivity(intent);
    				} else {
    					Toast.makeText(this, "Incorrect password" , Toast.LENGTH_SHORT).show();
    				} 
    			} else {
    				Toast.makeText(this, "Incorrect User name " , Toast.LENGTH_SHORT).show();
    			}
    			
    		}  else {
    			Toast.makeText(this, "User Not found" , Toast.LENGTH_SHORT).show();
    			txtPword.setText("");
    			txtUName.setText("");
    			txtUName.setFocusable(true);
    		}

//    		// should be checked for correct user name and password
//    		if (txtUName.getText().toString().matches("admin") &&  txtPword.getText().toString().matches("admin") ){
//    			Intent intent = new Intent(this, AddPatientData.class);
//            	startActivity(intent);		
//    		} else {
//    			Toast.makeText(this, "User name or password incorrect" , Toast.LENGTH_SHORT).show();
//    			
//    		}
//    	
    	}
    } 
}
