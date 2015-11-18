package com.mobtecnica.medirect.docter.fragments;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PatientsFragment extends Fragment {

	public PatientsFragment() {
	}

	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView textViewAddPatients;
	ListView listViewPatientsList;
	TextView buttonPatientsListSeeMore;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_patients, container,
				false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_patients));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void initializeViews(View rootView) {

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		textViewAddPatients = (TextView) rootView
				.findViewById(R.id.textViewAddPatients);
		listViewPatientsList = (ListView) rootView
				.findViewById(R.id.listViewPatientsList);
		buttonPatientsListSeeMore = (TextView) rootView
				.findViewById(R.id.buttonPatientsListSeeMore);

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
		textViewAddPatients.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					HttpRequestHelper.getAllCountries(getActivity());
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		;
		listViewPatientsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
		buttonPatientsListSeeMore
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

					}
				});
		;
	}

	private void buildUI(View rootView) {
		// TODO Auto-generated method stub

	}
}
