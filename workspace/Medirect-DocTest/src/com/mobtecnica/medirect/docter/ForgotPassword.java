package com.mobtecnica.medirect.docter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword extends Activity {
	
	Button buttonLoginCheck;
	TextView textViewForgotPassword;
	TextView editTextUserName;
	TextView editTextPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		makeFullScreen();
		setContentView(R.layout.activity_forgotpassword);

	}

	public void makeFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
