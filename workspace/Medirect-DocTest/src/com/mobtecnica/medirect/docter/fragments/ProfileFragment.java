package com.mobtecnica.medirect.docter.fragments;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	CircularImageView profileImage;
	TextView doctorsname;
	TextView gender;
	TextView registrationid;
	TextView designationtextview;
	TextView specializationtextview;
	TextView age;
	TextView email;
	TextView phone;
	TextView address;
	TextView dob;

	Profile_Model docter_profile;
	TextView editprofilebutton;
	TextView changepasswordbutton;
	Profile_Model docter;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	public ProfileFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_profile_fragment,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_profile));
		initializeViews(rootView);
		initializeListeners(rootView);
		Bundle extras = getArguments();

		if (extras != null) {

			docter = extras.getParcelable("Profile_Model");
		}
		buildUI(docter);
		return rootView;
	}

	/**
	 * buildUI
	 */
	private void buildUI(Profile_Model doctor) {
		if (doctor != null) {
			Picasso.with(getActivity()).load(doctor.getPhoto())
					.placeholder(R.drawable.ic_profile)
					.error(R.drawable.ic_profile).into(profileImage);

			doctorsname.setText("" + doctor.getFirst_name() + " "
					+ doctor.getLast_name());
			gender.setText("" + doctor.getGender());
			registrationid.setText("Registration Number: "
					+ doctor.getRegistration_number());
			designationtextview.setText("Medical Council: "
					+ doctor.getMedical_council());
			specializationtextview.setText("Qualification: "
					+ doctor.getQualification());
			int ageValue = Utilities.getInstance(getActivity()).getAge(
					Integer.parseInt(doctor.getDob().split("-")[0]),
					Integer.parseInt(doctor.getDob().split("-")[1]),
					Integer.parseInt(doctor.getDob().split("-")[2]));
			age.setText("age: " + ageValue);
			email.setText("Email: " + doctor.getEmail());
			phone.setText("Phone: " + doctor.getPhone());
			address.setText("Address: " + doctor.getAddress());
			dob.setText("Dob: " + doctor.getDob());
		}
	}

	private void initializeViews(View rootView) {

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);

		profileImage = (CircularImageView) rootView
				.findViewById(R.id.profileImage);
		doctorsname = (TextView) rootView.findViewById(R.id.doctorsname);
		gender = (TextView) rootView.findViewById(R.id.gender);
		registrationid = (TextView) rootView.findViewById(R.id.registrationid);
		designationtextview = (TextView) rootView
				.findViewById(R.id.designationtextview);
		specializationtextview = (TextView) rootView
				.findViewById(R.id.specializationtextview);
		age = (TextView) rootView.findViewById(R.id.age);
		email = (TextView) rootView.findViewById(R.id.email);
		phone = (TextView) rootView.findViewById(R.id.phone);
		address = (TextView) rootView.findViewById(R.id.address);
		dob = (TextView) rootView.findViewById(R.id.dob);

		editprofilebutton = (TextView) rootView
				.findViewById(R.id.editprofilebutton);
		changepasswordbutton = (TextView) rootView
				.findViewById(R.id.changepasswordbutton);
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
		editprofilebutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Config.LogError("Edit ....", "profile");
				/* Log.e("Edit ....", "profile"); */
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					HttpRequestHelper.getAllCountriesForEditProfile(
							getActivity(), docter);
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		changepasswordbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment changePasswordFragment = new ChangePasswordFragment();
				Bundle pDataBundle = new Bundle();
				pDataBundle.putParcelable("Profile_Model", docter);
				changePasswordFragment.setArguments(pDataBundle);
				Utilities.getInstance(getActivity()).changeChildFragment(
						changePasswordFragment, "ChangePasswordFragment",
						getActivity());

			}
		});
	}
}
