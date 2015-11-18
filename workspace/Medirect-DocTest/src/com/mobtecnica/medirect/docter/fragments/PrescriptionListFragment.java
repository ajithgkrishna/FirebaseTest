package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.PrescriptionListAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
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
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PrescriptionListFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView textViewAddPrescription;

	ExpandableListView expandableListViewPrescriptionList;
	public PrescriptionListAdapter adapter;
	TextView textViewSearch;
	EditText editTextAccountNo;
	EditText editTextFirstName;
	EditText editTextLastName;
	EditText editTextEmail;
	EditText editTextPhone;
	private int lastExpandedPosition = -1;
	private final String PREFS_LOGIN_STATUS = "LOGIN";
	public ArrayList<Profile_Model> pat_modelList = new ArrayList<Profile_Model>();
	public Boolean loading =false ;

	public PrescriptionListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_prescription_list,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_prescriptions));
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
		textViewAddPrescription = (TextView) rootView
				.findViewById(R.id.textViewAddPrescription);

		expandableListViewPrescriptionList = (ExpandableListView) rootView
				.findViewById(R.id.expandableListViewPrescriptionList);
		textViewSearch = (TextView) rootView.findViewById(R.id.textViewSearch);
		editTextAccountNo = (EditText) rootView
				.findViewById(R.id.editTextAccountNo);
		editTextFirstName = (EditText) rootView
				.findViewById(R.id.editTextFirstName);
		editTextLastName = (EditText) rootView
				.findViewById(R.id.editTextLastName);
		editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
		editTextPhone = (EditText) rootView.findViewById(R.id.editTextPhone);

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
		textViewAddPrescription.setOnClickListener(new View.OnClickListener() {

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
		textViewSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				HttpRequestHelper.getPrescriptionList(getActivity(),
						editTextAccountNo.getText().toString(),
						editTextFirstName.getText().toString(),
						editTextLastName.getText().toString(), editTextEmail
								.getText().toString(), editTextPhone.getText()
								.toString(),false,"0");

			}
		});
		expandableListViewPrescriptionList
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {
						// TODO Auto-generated method stub
						if (lastExpandedPosition != -1
								&& groupPosition != lastExpandedPosition) {
							expandableListViewPrescriptionList
									.collapseGroup(lastExpandedPosition);
						}
						lastExpandedPosition = groupPosition;
						expandableListViewPrescriptionList
								.setSelection(groupPosition);
					}
				});
		
		

	}

	private void buildUI(View rootView) {

		/*ArrayList<Profile_Model> pat_modelList = new ArrayList<Profile_Model>();*/

		Bundle extras = getArguments();
		if (extras != null) {
			pat_modelList = extras.getParcelableArrayList("Profile_Model");
			adapter = new PrescriptionListAdapter(getActivity(), pat_modelList,
					getActivity());

		}

		expandableListViewPrescriptionList.setAdapter(adapter);
		// Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(
		// expandableListViewPrescriptionList);
		if (!pat_modelList.isEmpty()) {
			expandableListViewPrescriptionList.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {

					int lastInScreen = firstVisibleItem + visibleItemCount;
					if ((lastInScreen == pat_modelList.size())
							&& !(loading)) {
						if (Utilities.getInstance(getActivity())
								.checkCondition(pat_modelList.size())) {
							loading = true;
							if (Utilities.getInstance(getActivity())
									.isNetAvailable()) {
								HttpRequestHelper.getPrescriptionList(
										getActivity(), "", "", "", "", "",true,String.valueOf(pat_modelList.size()));
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}
						}

					}
				}
			});
		}
	}

}
