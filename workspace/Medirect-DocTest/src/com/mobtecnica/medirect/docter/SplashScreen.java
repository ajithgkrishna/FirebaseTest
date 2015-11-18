package com.mobtecnica.medirect.docter;

import com.mobtecnica.medirect.docter.utils.Config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	private final String PREFS_LOGIN_STATUS = "LOGIN";
	private String TAG = "SplashScreen";
	private long delayMillis = 6000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		makeFullScreen();
		setContentView(R.layout.splash_screen);
		delayIntentHandler();

	}

	/**
	 * Making the activity is in full screen mode
	 */
	public void makeFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * Setting the delay to shows the splash screen then go to next activity
	 */
	public void delayIntentHandler() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (isAlreadyLogin()) {
					// user is already login; goto dash board
					
					Config.LogError("Login Status ", "User Logged in");
					/*Log.e("Login Status ", "User Logged in");*/
					Intent dashBoardIntent = new Intent(
							getApplicationContext(), ItemsListActivity.class);
					startActivity(dashBoardIntent);
					SplashScreen.this.finish();

				} else {
					// no logged user found; goto login page
					Config.LogError("Login Status ", "No User Logged in");
					/*Log.e("Login Status ", "No User Logged in");*/
					Intent loginPageIntent = new Intent(
							getApplicationContext(), LoginActivity.class);
					startActivity(loginPageIntent);
					SplashScreen.this.finish();
				}
			}
		}, delayMillis);
	}

	/**
	 * checking whether the user is already login
	 * 
	 * @return
	 */
	public boolean isAlreadyLogin() {
		boolean currentLoginStatus = false;
		currentLoginStatus = getSharedPreferences(PREFS_LOGIN_STATUS,
				Context.MODE_PRIVATE).getBoolean("hasLoggedIn", false);
		
		
		Config.LogError(TAG + "@isalreadyLogin", currentLoginStatus + "");
		/*Log.e(TAG + "@isalreadyLogin", currentLoginStatus + "");*/
		return currentLoginStatus;
	}

}
