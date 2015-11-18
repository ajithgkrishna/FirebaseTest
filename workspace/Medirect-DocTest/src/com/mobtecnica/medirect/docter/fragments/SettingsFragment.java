package com.mobtecnica.medirect.docter.fragments;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	public SettingsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_settings, container,
				false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_settings));
		initializeViews(rootView);
		initializeListeners(rootView);
		return rootView;
	}

	private void initializeViews(View rootView) {

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);

	}

	private void initializeListeners(View rootView) {
		// imageButtonwallet_nav.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new View.OnClickListener() {
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
	}
}
