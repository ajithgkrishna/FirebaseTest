package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.DeliveryAddressModel;
import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.support.v4.app.Fragment;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DeliveryAddressFragment extends Fragment {
	TextView menuname;
	private EditText edtName, edtAddress1, edtAddress2, edtPhoneNumber,
			edtPincode, edtLandMark;
	private TextView txtCountry, txtState, txtCity;
	private Button save;
	private String countryDialogSelected = "country",
			stateDialogSelected = "state", cityDialogSelected = "city";
	private String countryIdSel = "", stateIdSel = "", cityIdSel = "",
			prescriptionId = "";
	private PurchaseOrderPatientDetails patientDetails;
	
	public ArrayList<GetAllCountryModel> allCountriesList = new ArrayList<GetAllCountryModel>();
	public ArrayList<GetAllStateModel> allStatesList = new ArrayList<GetAllStateModel>();
	public ArrayList<GetAllCitiesModel> allCitiesList = new ArrayList<GetAllCitiesModel>();
	private ArrayList<PurchaseOrderModel> purchaseOrderList;
	public DeliveryAddressFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_delivery_address,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_confirm_order));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);

		return rootView;
	}

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle bun = getArguments();
			if (!bun.isEmpty()) {
				prescriptionId = bun.getString("prescriptionId");
				purchaseOrderList = bun.getParcelableArrayList("purchaseOrder");
				patientDetails = bun.getParcelable("patientAddress");
			}
		}
		if (patientDetails != null) {
			((TextView)rootView.findViewById(R.id.txtPatientName)).setText(patientDetails.getName());
			((TextView)rootView.findViewById(R.id.txtPatientAddress)).setText(patientDetails.getAddress());
			((TextView)rootView.findViewById(R.id.txtPatientPhoneNumber)).setText(patientDetails.getPhone());
		}
	}

	/***
	 * 
	 * @param rootView
	 */
	private void initializeListeners(final View rootView) {
		((RadioGroup)rootView.findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioPatientAddress:
					((LinearLayout)rootView.findViewById(R.id.linearPatientAddress)).setVisibility(View.VISIBLE);
					((LinearLayout)rootView.findViewById(R.id.linearNewAddress)).setVisibility(View.GONE);
					break;

				case R.id.radioAddNewAddress:
					((LinearLayout)rootView.findViewById(R.id.linearPatientAddress)).setVisibility(View.GONE);
					((LinearLayout)rootView.findViewById(R.id.linearNewAddress)).setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
			}
		});
		
		txtCountry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!allCountriesList.isEmpty()) {
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToCountry(allCountriesList),
							countryDialogSelected);
					dialog.show();
					txtState.setClickable(false);
					txtCity.setClickable(false);
					txtState.setText("");
					txtCity.setText("");
					txtState.setHint(R.string.chose_state);
					txtCity.setHint(R.string.chose_city);
					stateIdSel = "";
					cityIdSel = "";
				} else {
					HttpRequestHelper.getAllCountries(getActivity());
				}
			}
		});
		txtState.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!allStatesList.isEmpty()) {
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToStates(allStatesList),
							stateDialogSelected);
					dialog.show();
					txtState.setClickable(true);
					txtCity.setClickable(false);
					txtCity.setText("");
					txtCity.setHint(R.string.chose_city);
					cityIdSel = "";
				}
			}
		});
		txtCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!allCitiesList.isEmpty()) {
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToCities(allCitiesList),
							cityDialogSelected);
					dialog.show();
				}
			}
		});
		((Button)rootView.findViewById(R.id.next)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userid = getActivity().getSharedPreferences(
						LoginActivity.PREFS_LOGIN_STATUS,
						Context.MODE_PRIVATE).getString(
						LoginActivity.PREFS_USERID, "");
				HttpRequestHelper.saveDeliveryAddressFromProfile(getActivity(),
							patientDetails.getId(), userid, prescriptionId, purchaseOrderList);
			}
		});
		
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!validateFields()) {
					String userid = getActivity().getSharedPreferences(
							LoginActivity.PREFS_LOGIN_STATUS,
							Context.MODE_PRIVATE).getString(
							LoginActivity.PREFS_USERID, "");
					DeliveryAddressModel addressModel = new DeliveryAddressModel(
							userid, edtName.getText().toString().trim(),
							edtAddress1.getText().toString().trim(),
							edtAddress2.getText().toString().trim(), cityIdSel,
							stateIdSel, countryIdSel, edtPincode.getText()
									.toString().trim(), edtPhoneNumber
									.getText().toString().trim(), edtLandMark
									.getText().toString().trim());
					HttpRequestHelper.saveDeliveryAddress(getActivity(),
							addressModel, prescriptionId,purchaseOrderList);
				}
			}
		});
	}

	/***
	 * 
	 * @param rootView
	 */
	private void initializeViews(View rootView) {
		if (rootView != null) {
			edtName = (EditText) rootView.findViewById(R.id.edtName);
			edtAddress1 = (EditText) rootView.findViewById(R.id.edtAddress1);
			edtAddress2 = (EditText) rootView.findViewById(R.id.edtAddress2);
			edtPhoneNumber = (EditText) rootView
					.findViewById(R.id.edtPhoneNumber);
			edtPincode = (EditText) rootView.findViewById(R.id.edtPincode);
			edtLandMark = (EditText) rootView.findViewById(R.id.edtLandMark);
			txtCountry = (TextView) rootView.findViewById(R.id.txtCountry);
			txtState = (TextView) rootView.findViewById(R.id.txtState);
			txtCity = (TextView) rootView.findViewById(R.id.txtCity);
			save = (Button) rootView.findViewById(R.id.save);
		}

	}

	public class CountryListDialog extends Dialog {
		private ListView lstDialogConfirm;
		private EditText edtSearch;
		private ArrayList<String> countryDuplicate = new ArrayList<String>();
		private ArrayList<String> finalArray = new ArrayList<String>();
		private int textlenght = 0;
		private Context con;
		private String selectedDialog;

		/*****
		 * Depending on this constructor the country,state and city loading
		 * takes place.
		 * 
		 * @param context
		 * @param finalArray
		 * @param selectedDialog
		 *            this value helps to determine whether it is country ,
		 *            state or city.
		 */

		public CountryListDialog(Context context, ArrayList<String> finalArray,
				String selectedDialog) {
			super(context);
			// TODO Auto-generated constructor stub
			this.finalArray = finalArray;
			this.con = context;
			this.selectedDialog = selectedDialog;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.dialog_country_list);
			lstDialogConfirm = (ListView) findViewById(R.id.lstCountry);
			edtSearch = (EditText) findViewById(R.id.edtSearchName);
			lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
					R.layout.spinner_item, finalArray));
			edtSearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub
					textlenght = edtSearch.getText().length();
					countryDuplicate.clear();
					for (int i = 0; i < finalArray.size(); i++) {
						if (textlenght <= finalArray.get(i).length()) {
							if (edtSearch
									.getText()
									.toString()
									.equalsIgnoreCase(
											(String) finalArray.get(i)
													.subSequence(0, textlenght))) {
								countryDuplicate.add(finalArray.get(i));
							}
						}
					}
					lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
							R.layout.spinner_item, countryDuplicate));

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub
				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
				}
			});

			lstDialogConfirm.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					dismiss();
					String selectedItem = (String) lstDialogConfirm
							.getItemAtPosition(position);
					int finalPosition = finalArray.indexOf(selectedItem);

					if (selectedDialog.equals(countryDialogSelected)) {
						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {

							txtCountry.setText(selectedItem);
							HttpRequestHelper.getAllStates_Details(
									getActivity(),

									allCountriesList.get(finalPosition)
											.getCountry_id(), false);
							countryIdSel = allCountriesList.get(finalPosition)
									.getCountry_id();
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}
					} else if (selectedDialog.equals(stateDialogSelected)) {
						txtState.setText(selectedItem);

						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {
							HttpRequestHelper.getAllCities_Details(
									getActivity(),
									allStatesList.get(finalPosition).getId(),
									false);
							stateIdSel = allStatesList.get(finalPosition)
									.getId();
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}
					} else if (selectedDialog.equals(cityDialogSelected)) {
						txtCity.setText(selectedItem);

						cityIdSel = allCitiesList.get(finalPosition).getId();
					}

				}
			});
			;
		}

	}

	private ArrayList<String> setDataToCountry(
			ArrayList<GetAllCountryModel> allCountries) {
		ArrayList<String> countryNameList = new ArrayList<String>();

		for (int i = 0; i < allCountries.size(); i++) {
			countryNameList.add(allCountries.get(i).getCountry_name());
		}

		return countryNameList;
	}

	public ArrayList<String> setDataToStates(
			ArrayList<GetAllStateModel> allstates) {
		ArrayList<String> stateNameList = new ArrayList<String>();

		for (int i = 0; i < allstates.size(); i++) {
			stateNameList.add(allstates.get(i).getName());
		}

		return stateNameList;
	}

	public ArrayList<String> setDataToCities(
			ArrayList<GetAllCitiesModel> allcities) {
		ArrayList<String> cityNameList = new ArrayList<String>();

		for (int i = 0; i < allcities.size(); i++) {
			cityNameList.add(allcities.get(i).getName());
		}

		return cityNameList;
	}

	public void showCountryDialog() {
		if (!allCountriesList.isEmpty()) {
			CountryListDialog dialog = new CountryListDialog(getActivity(),
					setDataToCountry(allCountriesList), countryDialogSelected);
			dialog.show();
			txtState.setClickable(true);
			txtCity.setClickable(false);
			txtCity.setText("");
			txtState.setText("");
			txtCity.setHint(R.string.chose_city);
			txtState.setHint(R.string.chose_state);
			stateIdSel = "";
			cityIdSel = "";

		}
	}

	/**
	 * 
	 * @return true if all fields contains data.
	 */
	private Boolean validateFields() {
		String name = edtName.getText().toString();
		String address1 = edtAddress1.getText().toString();
		String address2 = edtAddress2.getText().toString();
		String phone_number = edtPhoneNumber.getText().toString();
		String pinCode = edtPincode.getText().toString();
		String landMark = edtLandMark.getText().toString();
		View focusView = null;
		Boolean validation = false;

		if (TextUtils.isEmpty(name)) {
			edtName.setError(getString(R.string.error_field_required));
			focusView = edtName;
			validation = true;
		}
		if (TextUtils.isEmpty(address1)) {
			edtAddress1.setError(getString(R.string.error_field_required));
			focusView = edtAddress1;
			validation = true;
		}
		if (TextUtils.isEmpty(address2)) {
			edtAddress2.setError(getString(R.string.error_field_required));
			focusView = edtAddress2;
			validation = true;
		}
		if (TextUtils.isEmpty(phone_number)) {
			edtPhoneNumber.setError(getString(R.string.error_field_required));
			focusView = edtPhoneNumber;
			validation = true;
		}
		if (!(Utilities.getInstance(getActivity()).isValidMobile(phone_number))) {
			edtPhoneNumber.setError(getString(R.string.error_field_required));
			focusView = edtPhoneNumber;
			validation = true;
		}
		if (TextUtils.isEmpty(pinCode)) {
			edtPincode.setError(getString(R.string.error_field_required));
			focusView = edtPincode;
			validation = true;
		}
		if (!(Utilities.getInstance(getActivity()).isValidPinCode(pinCode))) {
			edtPincode.setError(getString(R.string.error_field_required));
			focusView = edtPincode;
			validation = true;
		}

		if (TextUtils.isEmpty(landMark)) {
			edtLandMark.setError(getString(R.string.error_field_required));
			focusView = edtLandMark;
			validation = true;
		}
		if (TextUtils.isEmpty(countryIdSel)) {
			txtCountry.setError(getString(R.string.error_field_required));
			focusView = txtCountry;
			validation = true;
		}
		if (TextUtils.isEmpty(stateIdSel)) {
			txtState.setError(getString(R.string.error_field_required));
			focusView = txtState;
			validation = true;
		}
		if (TextUtils.isEmpty(cityIdSel)) {
			txtCity.setError(getString(R.string.error_field_required));
			focusView = txtCity;
			validation = true;
		}
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

}
