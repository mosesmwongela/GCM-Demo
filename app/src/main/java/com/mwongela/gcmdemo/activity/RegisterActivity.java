package com.mwongela.gcmdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mwongela.gcmdemo.R;
import com.mwongela.gcmdemo.utility.AlertDialogManager;
import com.mwongela.gcmdemo.utility.ConnectionDetector;

import static com.mwongela.gcmdemo.utility.CommonUtilities.SENDER_ID;
import static com.mwongela.gcmdemo.utility.CommonUtilities.SERVER_URL;

/**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 */

public class RegisterActivity extends Activity {

	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Internet detector
	ConnectionDetector cd;
	
	// UI elements
	EditText etName;
	EditText etPhone;
	EditText etPassword;
	
	// Register button
	Button btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}
		
		etName = (EditText) findViewById(R.id.etFullName);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String name = etName.getText().toString();
				String phone = etPhone.getText().toString();
				String password = etPassword.getText().toString();

				int error = 0;

				//check if user filled in their name
				if(name.trim().length() == 0){
					error++;
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Tell us your name", false);
				}
				if(name.trim().length() < 3 && error==0){
					error++;
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "That name is too short", false);
				}
				if(phone.trim().length() != 10 && error==0){
					error++;
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "That name is too short", false);
				}
				
				// Check if user filled the form
				if(name.trim().length() > 0 && phone.trim().length() > 0){
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("name", name);
					i.putExtra("email", email);
					startActivity(i);
					finish();
				}else{
					// user doen't filled that data
					// ask him to fill the form
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}

}
