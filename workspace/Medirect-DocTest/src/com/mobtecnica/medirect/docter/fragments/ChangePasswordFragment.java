package com.mobtecnica.medirect.docter.fragments;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordFragment extends Fragment {
	EditText currentpassword, newpassword, confirmpassword;
	Button save;
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.change_password_fragment,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.changepassword));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		// TODO Auto-generated method stub

	}

	private void initializeListeners(View rootView) {
		// imageButtonwallet_nav.setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		imageViewuser_nav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getActivity()
						.getSharedPreferences(PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				Toast.makeText(getActivity(),
						"You have been Successfully Logged out !",
						Toast.LENGTH_SHORT).show();
				Intent dashBoardIntent = new Intent(getActivity(),
						LoginActivity.class);
				startActivity(dashBoardIntent);
				getActivity().finish();

			}
		});
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if ((currentpassword.getText().toString().trim().length() > 0)
						&& (newpassword.getText().toString().trim().length() > 0)
						&& (confirmpassword.getText().toString().trim()
								.length() > 0)) {
					Bundle extras = getArguments();
					Profile_Model docter = null;
					if (extras != null) {
						docter = extras.getParcelable("Profile_Model");
					}
					if (Utilities.getInstance(getActivity()).isNetAvailable()) {
						HttpRequestHelper.change_password(
								getActivity(),
								getActivity().getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, ""),
								currentpassword.getText().toString(),
								newpassword.getText().toString(),
								confirmpassword.getText().toString(), docter);
					}
				} else {
					Toast.makeText(getActivity(),
							R.string.error_field_required_empty,
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	private void initializeViews(View rootView) {
		// TODO Auto-generated method stub
		currentpassword = (EditText) rootView.findViewById(R.id.curentpassword);
		newpassword = (EditText) rootView.findViewById(R.id.newpassword);

		confirmpassword = (EditText) rootView
				.findViewById(R.id.confirmnewpassword);
		save = (Button) rootView.findViewById(R.id.save);
		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
	}
}
