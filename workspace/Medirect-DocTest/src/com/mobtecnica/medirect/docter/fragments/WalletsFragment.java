package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.Calendar;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.HistoryListWalletAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.WalletHistory;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.support.v4.app.Fragment;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WalletsFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	String final_balance;
	ArrayList<WalletHistory> walletHistory_list = new ArrayList<WalletHistory>();
	TextView textViewavailBalanceValue;
	Button buttonredeemeBalance;
	EditText editTextfrom;
	EditText editTextTo;
	Button buttonSubmit;
	ListView listviewPrescriptionsList;
	int mYear;
	int mMonth;
	int mDay;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	public WalletsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_wallet, container,
				false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_wallet));
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplayFromdate();
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		Bundle bundle = getArguments();
		if (bundle != null) {
			walletHistory_list = bundle
					.getParcelableArrayList("walletHistory_list");
			final_balance = bundle.getString("final_balance");
			textViewavailBalanceValue.setText("Rs:" + final_balance);
			HistoryListWalletAdapter historyListAdapter = new HistoryListWalletAdapter(
					getActivity(), walletHistory_list);
			listviewPrescriptionsList.setAdapter(historyListAdapter);
			Utilities
					.getInstance(getActivity())
					.setListViewHeightBasedOnChildren(listviewPrescriptionsList);
		}

	}

	private void initializeViews(View rootView) {

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		textViewavailBalanceValue = (TextView) rootView
				.findViewById(R.id.textViewavailBalanceValue);
		buttonredeemeBalance = (Button) rootView
				.findViewById(R.id.buttonredeemeBalance);
		editTextfrom = (EditText) rootView.findViewById(R.id.editTextfrom);
		editTextTo = (EditText) rootView.findViewById(R.id.editTextTo);
		buttonSubmit = (Button) rootView.findViewById(R.id.buttonSubmit);
		listviewPrescriptionsList = (ListView) rootView
				.findViewById(R.id.listviewPrescriptionsList);

	}

	private void initializeListeners(View rootView) {
		editTextfrom.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(getActivity(), mDateSetListenerFromDate,
						mYear, mMonth, mDay).show();

			}
		});
		editTextTo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(getActivity(), mDateSetListenerToDate,
						mYear, mMonth, mDay).show();

			}
		});
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
		buttonSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					HttpRequestHelper.getWalletHistory(
							getActivity(),
							getActivity().getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""),
							editTextfrom.getText().toString().trim(),
							editTextTo.getText().toString().trim());
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private DatePickerDialog.OnDateSetListener mDateSetListenerFromDate = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplayFromdate();

		}

	};

	private void updateDisplayFromdate() {
		if (editTextfrom != null) {
			editTextfrom.setText(new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay).append(" "));
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListenerToDate = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplayTodate();

		}

	};

	private void updateDisplayTodate() {
		if (editTextTo != null) {
			editTextTo.setText(new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay).append(" "));
		}
	}
}
