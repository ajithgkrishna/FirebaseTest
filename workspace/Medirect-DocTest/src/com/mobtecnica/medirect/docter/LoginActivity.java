package com.mobtecnica.medirect.docter;

import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForLogin;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements
		OnHtttpResponseListenerForLogin {
	Button buttonLoginCheck;
	TextView textViewForgotPassword;
	TextView editTextUserName;
	TextView editTextPassword;
	public static String PREFS_LOGIN_STATUS = "LOGIN";
	public static String PREFS_HAS_LOGIN = "hasLoggedIn";
	public static String PREFS_USERID = "userid";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		makeFullScreen();
		setContentView(R.layout.activity_login);
		initializeViews();
		initializeListeners();
	}

	public void makeFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void initializeListeners() {
		buttonLoginCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!validateloginfielsEntered()) {
					if (Utilities.getInstance(LoginActivity.this)
							.isNetAvailable()) {
						HttpRequestHelper
								.LoginUser(LoginActivity.this, editTextUserName
										.getText().toString(), editTextPassword
										.getText().toString(), "1");

					} else
						Toast.makeText(LoginActivity.this,
								R.string.error_internet,
								Toast.LENGTH_SHORT).show();
				}
//				Intent in = new Intent(LoginActivity.this, ItemsListActivity.class);
//				startActivity(in);
//				LoginActivity.this.finish();
			}
		});
		/*textViewForgotPassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(LoginActivity.this, ForgotPassword.class);
				startActivity(in);

			}
		});*/

	}

	private void initializeViews() {
		buttonLoginCheck = (Button) findViewById(R.id.buttonLoginCheck);
		textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);

	}

	/**
	 * onHttpSuccessfulResponseLogin
	 */
	
/**
 * validate login fiels Entered
 * @return
 */
	private boolean validateloginfielsEntered() {

		String strTextUserName = editTextUserName.getText().toString();
		String strTextPassword = editTextPassword.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(strTextPassword)) {
			editTextPassword.setError(getString(R.string.error_field_required));
			focusView = editTextPassword;
			cancel = true;
		} /*
		 * else if (mPassword.length() < 4) {
		 * mPasswordView.setError(getString(R.string.error_invalid_password));
		 * focusView = mPasswordView; cancel = true; }
		 */
		// Check for a valid email address.
		if (TextUtils.isEmpty(strTextUserName)) {
			editTextUserName.setError(getString(R.string.error_field_required));
			focusView = editTextUserName;
			cancel = true;
		}

		/*
		 * else if (!mEmail.contains("@")) {
		 * mEmailView.setError(getString(R.string.error_invalid_email));
		 * focusView = mEmailView; cancel = true; }
		 */

		if (cancel) {

			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {

			return cancel;
		}
		return cancel;
	}

	@Override
	public void onHttpSuccessfulResponseLogin(String response,
			boolean responseStatus, String responseResultMsg) {
		if (responseStatus) {

			SharedPreferences.Editor editor = getSharedPreferences(
					PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
			editor.putBoolean(PREFS_HAS_LOGIN, true);
			editor.putString(PREFS_USERID, responseResultMsg);
			editor.commit();

			Intent in = new Intent(LoginActivity.this, ItemsListActivity.class);
			startActivity(in);
			LoginActivity.this.finish();
			Toast.makeText(LoginActivity.this, "Login Success",
					Toast.LENGTH_SHORT).show();
		} else
			Toast.makeText(LoginActivity.this, responseResultMsg,
					Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onHttpFailedResponseLogin(Throwable throwable, String response,
			boolean resposeStatus, String responseResultMessage) {
		Toast.makeText(LoginActivity.this, "Please Try Again !",
				Toast.LENGTH_SHORT).show();

	}
	
}
