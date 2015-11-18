package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.Collections;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.PrescriptionListAdapter;
import com.mobtecnica.medirect.docter.adapters.ViewPrescriptionAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPrescriptionFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;

	ExpandableListView expandableListViewPrescription;
	ViewPrescriptionAdapter adapter;
	ArrayList<PrescriptionDetailModel> presscriptionDetailModel;
	Profile_Model patientModel;

	CircularImageView profileImage;
	TextView patientName;
	TextView patientaddressValue;
	TextView AgeValue;
	TextView GenderValue;
	TextView phoneValue;
	Button newprescriptions;
	Button buttonNewMessage;
	private int lastExpandedPosition = -1;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	public ViewPrescriptionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_view_prescription,
				container, false);
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

		expandableListViewPrescription = (ExpandableListView) rootView
				.findViewById(R.id.expandableListViewPrescription);
		profileImage = (CircularImageView) rootView
				.findViewById(R.id.profileImage);
		patientName = (TextView) rootView.findViewById(R.id.patientName);
		patientaddressValue = (TextView) rootView
				.findViewById(R.id.patientaddressValue);
		AgeValue = (TextView) rootView.findViewById(R.id.AgeValue);
		GenderValue = (TextView) rootView.findViewById(R.id.GenderValue);
		phoneValue = (TextView) rootView.findViewById(R.id.phoneValue);
		newprescriptions = (Button) rootView
				.findViewById(R.id.newprescriptions);
		buttonNewMessage = (Button) rootView
				.findViewById(R.id.buttonNewMessage);
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
		newprescriptions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
				/*	HttpRequestHelper.getAllFrequencies(getActivity(),
							patientModel);*/
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		buttonNewMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		expandableListViewPrescription
				.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
					public boolean onGroupClick(ExpandableListView arg0,
							View itemView, int itemPosition, long itemId) {
						expandableListViewPrescription
								.expandGroup(itemPosition);
						return true;
					}
				});
		/*
		 * expandableListViewPrescription .setOnGroupExpandListener(new
		 * OnGroupExpandListener() {
		 * 
		 * @Override public void onGroupExpand(int groupPosition) { // TODO
		 * Auto-generated method stub if (lastExpandedPosition != -1 &&
		 * groupPosition != lastExpandedPosition) {
		 * expandableListViewPrescription .collapseGroup(lastExpandedPosition);
		 * } lastExpandedPosition = groupPosition;
		 * expandableListViewPrescription .setSelection(groupPosition); } });
		 */

	}

	private void buildUI(View rootView) {
		Bundle extras = getArguments();
		if (extras != null) {
			presscriptionDetailModel = extras
					.getParcelableArrayList("prescriptiondetail");
			Collections.reverse(presscriptionDetailModel);
			patientModel = extras.getParcelable("patModel");
		}

		Picasso.with(getActivity()).load(patientModel.getPhoto())
				.placeholder(R.drawable.ic_profile)
				.error(R.drawable.ic_profile).into(profileImage);
		patientName.setText(patientModel.getFirst_name() + " "
				+ patientModel.getLast_name());
		patientaddressValue.setText(patientModel.getAddress());

		int ageValue = Utilities.getInstance(getActivity()).getAge(
				Integer.parseInt(patientModel.getDob().split("-")[0]),
				Integer.parseInt(patientModel.getDob().split("-")[1]),
				Integer.parseInt(patientModel.getDob().split("-")[2]));

		AgeValue.setText("" + ageValue + ",");
		GenderValue.setText(patientModel.getGender());
		phoneValue.setText(patientModel.getPhone());
		adapter = new ViewPrescriptionAdapter(getActivity(),
				presscriptionDetailModel, getActivity());
		expandableListViewPrescription.setAdapter(adapter);
		for (int i = 0; i < presscriptionDetailModel.size(); i++) {
			expandableListViewPrescription.expandGroup(i);
		}

		/* expandableListViewPrescription */
		/*
		 * expandableListViewPrescription.expandGroup(presscriptionDetailModel.size
		 * ());
		 */

		// Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(
		// expandableListViewPrescription);
	}

}
