package com.mobtecnica.medirect.docter.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.AddNewPrescriptionAdapter;
import com.mobtecnica.medirect.docter.adapters.ConfirmPrescriptionAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.fragments.AddHistoryFragment.AlergiMedicineDialog;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PatientAddModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.CustomEditText;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.mobtecnica.medirect.docter.utils.CustomEditText.DrawableClickListener;
import com.mobtecnica.medirect.docter.utils.CustomEditText.DrawableClickListener.DrawablePosition;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class AddMedicineFragment extends Fragment {

	private static String AddMedicineFragmentTag = "AddMedicineFragment";
	private static String AddMedicineFragmentFrequencyTag = "AddMedicineFragmentFrequency";
	private static String AddMedicineHistoryFrequencyTag = "AddMedicineHistoryFrequency";
	private static String AddMedicineFragmentDiagnosticsTag = "AddMedicineFragmentDiagnostics";
	private static String AddMedicineAddedMedicineTag = "AddMedicineAddedMedicine";
	private ArrayList<MedicinsModel> medicinesList;

	private ArrayList<MedicinsModel> prescriptionMedicineList;
	private AddNewPrescriptionAdapter adapter;
	private EditText txtMedicineName;
	private TextView txtMedicinePerDose;
	private TextView txtDosePerDay;
	private TextView txtNoOfDays;
	private TextView txtRefillNo;
	private TextView txtNotes;
	private TextView txtSaveAndNotify;
	private ListView lstPatientsList;
	private Spinner spnChoseFrequencies, spnTakeFood;
	private CheckBox chbMorning, chbAfterNoon, chbEvening, chbNight;
	private LinearLayout linearCheckBoxList;
	// private Spinner spnDose;
	private TextView spnDoseTextUnit;
	CircularImageView profImage;
	TextView doctorsname;
	TextView doctorsAddress;
	TextView doctorsAge;
	TextView doctorsGender;
	TextView textViewSuggest;
	Profile_Model patient_prof;
	public ArrayList<MedicinsListModel> med_list = new ArrayList<MedicinsListModel>();
	public ArrayList<FrequencyModel> frequency_list = new ArrayList<FrequencyModel>();
	private int medicineSelectedId = -1;

	// public ArrayList<UnitsModel> allunits = new ArrayList<UnitsModel>();
	boolean firstTimeCalled = false;
	private final String PREFS_LOGIN_STATUS = "LOGIN";
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView menuname;

	/****
	 * The data passed while creating this fragment
	 * 
	 * @param profile
	 * @return
	 */
	public static AddMedicineFragment newInstanceOfAddMedicineFragment(
			Profile_Model profile, MyHistoryModel myHistory,
			ArrayList<FrequencyModel> frequencyModel,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(AddMedicineFragmentTag, profile);
		bundle.putParcelableArrayList(AddMedicineFragmentFrequencyTag,
				frequencyModel);
		bundle.putParcelable(AddMedicineHistoryFrequencyTag, myHistory);
		bundle.putParcelableArrayList(AddMedicineFragmentDiagnosticsTag,
				diagNosticModel);
		bundle.putParcelableArrayList(AddMedicineAddedMedicineTag,
				addedMedicinesList);
		AddMedicineFragment fragment = new AddMedicineFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.newprescription_medicines,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_Medicine));
		/*
		 * getBundleValues(); initializeViews(rootView);
		 */

		buildUI(rootView);
		initializeListeners(rootView);
		return rootView;
	}

	

	public void functionshowmedicindialog() {
		MedicineListDialog medicineDialog = new MedicineListDialog(
				getActivity(), addDataToDialog());
		medicineDialog.show();
	}

	private void initializeListeners(final View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			if (extras != null) {
				final ArrayList<FrequencyModel> frequencyModelList = extras
						.getParcelableArrayList(AddMedicineFragmentFrequencyTag);

				final Profile_Model patient_prof = extras
						.getParcelable(AddMedicineFragmentTag);
				final MyHistoryModel myHistory = extras
						.getParcelable(AddMedicineHistoryFrequencyTag);
				final ArrayList<DiagnosticsModelForAddPrescription> diagnosticsList = extras
						.getParcelableArrayList(AddMedicineFragmentDiagnosticsTag);
				medicinesList = extras
						.getParcelableArrayList(AddMedicineAddedMedicineTag);

				((CustomEditText) rootView.findViewById(R.id.edtMedicineName))
						.setDrawableClickListener(new DrawableClickListener() {

							@Override
							public void onClick(DrawablePosition target) {
								// TODO Auto-generated method stub
								switch (target) {
								case RIGHT:
									if (Utilities.getInstance(getActivity())
											.isNetAvailable()) {
										String key = ((CustomEditText) getView()
												.findViewById(
														R.id.edtMedicineName))
												.getText().toString();
										if (key != null) {
											/* if (!key.equals("")) { */
											HttpRequestHelper.getAllMedicins(
													getActivity(), key);
											/* } */
										}
									} else {
										Toast.makeText(getActivity(),
												R.string.error_internet,
												Toast.LENGTH_SHORT).show();
									}

									break;

								default:
									break;
								}
							}
						});
				((Spinner) rootView.findViewById(R.id.spnChoseFrequencies))
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								if (!frequencyModelList.isEmpty()) {
									Config.LogError("Frequencies Position", ""
											+ position);
									Config.LogError(
											"Frequencies List PositionID",
											frequencyModelList.get(position)
													.getId());
									if (frequencyModelList.get(position)
											.getId().equals("1")
											|| frequencyModelList.get(position)
													.getId().equals("2")
											|| frequencyModelList.get(position)
													.getId().equals("3")
											|| frequencyModelList.get(position)
													.getId().equals("4")
											|| frequencyModelList.get(position)
													.getId().equals("5")) {
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setVisibility(View.VISIBLE);
										((LinearLayout) rootView
												.findViewById(R.id.linearAmountPerDose))
												.setVisibility(View.GONE);
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setClickable(true);
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setEnabled(true);

									} else {
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setVisibility(View.GONE);
										((LinearLayout) rootView
												.findViewById(R.id.linearAmountPerDose))
												.setVisibility(View.VISIBLE);
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setClickable(false);
										((LinearLayout) rootView
												.findViewById(R.id.linearCheckBoxList))
												.setEnabled(false);
									}
								}

							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});

				((TextView) rootView.findViewById(R.id.txtAddMedicine))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (!validateFields(rootView)) {
									if (medicinesList != null) {
										medicinesList.add(addData(rootView,
												frequencyModelList));
									} else {
										medicinesList = new ArrayList<MedicinsModel>();
										medicinesList.add(addData(rootView,
												frequencyModelList));
									}
									Fragment frg = AddNewPrescription
											.newInstanceOfAddNewPrescription(
													patient_prof, myHistory,
													diagnosticsList, medicinesList);
									Utilities.getInstance(getActivity())
											.changeChildFragment(frg,
													"AddNewPrescription",
													getActivity());
								}
							}
						});
				((TextView) rootView.findViewById(R.id.txtCancel)).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Fragment frg = AddNewPrescription
								.newInstanceOfAddNewPrescription(
										patient_prof, myHistory,
										diagnosticsList, medicinesList);
						Utilities.getInstance(getActivity())
								.changeChildFragment(frg,
										"AddNewPrescription",
										getActivity());
					}
				});
				((ImageButton) rootView
						.findViewById(R.id.imageViewuser_nav)).setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								SharedPreferences.Editor editor = getActivity()
										.getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
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

	}

	private void buildUI(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			if (extras != null) {
				Profile_Model patient_prof = extras
						.getParcelable(AddMedicineFragmentTag);
				MyHistoryModel myHistory = extras
						.getParcelable(AddMedicineHistoryFrequencyTag);
				ArrayList<FrequencyModel> frequencyModelList = extras
						.getParcelableArrayList(AddMedicineFragmentFrequencyTag);
				if (patient_prof != null) {
					Picasso.with(getActivity())
							.load(patient_prof.getPhoto())
							.placeholder(R.drawable.ic_profile)
							.error(R.drawable.ic_profile)
							.into((CircularImageView) rootView
									.findViewById(R.id.profImage));
					((TextView) rootView.findViewById(R.id.txtAccount_no))
							.setText(patient_prof.getAccount_no());
					((TextView) rootView.findViewById(R.id.txtname))
							.setText(patient_prof.getFirst_name() + " "
									+ patient_prof.getLast_name());
					((TextView) rootView.findViewById(R.id.txtAddress))
							.setText(patient_prof.getAddress());
					((TextView) rootView.findViewById(R.id.txtEmail))
							.setText(patient_prof.getEmail());
					((TextView) rootView.findViewById(R.id.txtPhone))
							.setText(patient_prof.getPhone());
					((TextView) rootView.findViewById(R.id.txtAge))
							.setText(patient_prof.getAge());
					((TextView) rootView.findViewById(R.id.txtGender))
							.setText(patient_prof.getGender());
				}
				if (myHistory != null) {
					((TextView) rootView.findViewById(R.id.txtMedicalhistory))
							.setText(myHistory.getMedical_history());
					((TextView) rootView.findViewById(R.id.txtSurgicalhistory))
							.setText(myHistory.getSurgical_history());
					((TextView) rootView.findViewById(R.id.txtFamilyhistory))
							.setText(myHistory.getFamily_history());
					((TextView) rootView.findViewById(R.id.txtAllergies))
							.setText(addAlergiesToString(myHistory
									.getAllergies()));
				}
				if (frequencyModelList != null) {
					loadDatatoFrequencySpinner(frequencyModelList, rootView);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(), R.layout.spinner_item, foodArray());
				((Spinner) rootView.findViewById(R.id.spnTakeFood))
						.setAdapter(adapter);
			}
		}

		
	}

	/****
	 * The method for adding alergies list to string
	 * 
	 * @param alergieList
	 * @return
	 */
	private String addAlergiesToString(
			ArrayList<AllergiMedicineModel> alergieList) {
		String allergies = "";
		if (alergieList != null) {
			if (!alergieList.isEmpty()) {
				for (int i = 0; i < alergieList.size(); i++) {
					if (i == 0) {
						allergies = alergieList.get(i).getMedicine_name();
					} else {
						allergies = allergies + " ,"
								+ alergieList.get(i).getMedicine_name();
					}

				}
			}
		}
		return allergies;
	}

	/***
	 * 
	 * @param rootView
	 * @returns PrescriptionMedicine object
	 */
	private MedicinsModel addData(View rootView,
			ArrayList<FrequencyModel> freqList) {
		MedicinsListModel medicineModel = (MedicinsListModel) (((CustomEditText) rootView
				.findViewById(R.id.edtMedicineName)).getTag());
		String medicin_id = medicineModel.getId().toString().trim();
		String medicin_name = medicineModel.getName().toString().trim();
		String medicineUnit = medicineModel.getMedicine_unit_id().toString()
				.trim();
		String medicines_per_dose = ((EditText) rootView
				.findViewById(R.id.edtAmountPerDose)).getText().toString();
		String no_of_days = ((EditText) rootView.findViewById(R.id.edtNoOfDays))
				.getText().toString();
		String refill_number = ((EditText) rootView
				.findViewById(R.id.edtNoOfRefils)).getText().toString();
		String frequencyId = freqList.get(
				((Spinner) rootView.findViewById(R.id.spnChoseFrequencies))
						.getSelectedItemPosition()).getId();
		String frequencyName = freqList.get(
				((Spinner) rootView.findViewById(R.id.spnChoseFrequencies))
						.getSelectedItemPosition()).getName();
		String afterFood;
		if (((Spinner) rootView.findViewById(R.id.spnTakeFood))
				.getSelectedItem().toString().equals("After Food")) {
			afterFood = "0";
		} else {
			afterFood = "1";
		}
		String morning = ((EditText) rootView
				.findViewById(R.id.edtMedicineMorning)).getText().toString();
		String afterNoon = ((EditText) rootView
				.findViewById(R.id.edtMedicineAfterNoon)).getText().toString();
		String evening = ((EditText) rootView
				.findViewById(R.id.edtMedicineEvening)).getText().toString();
		String night = ((EditText) rootView.findViewById(R.id.edtMedicineNight))
				.getText().toString();
		String notes = ((EditText) rootView.findViewById(R.id.typeANote))
				.getText().toString();

		

		MedicinsModel medicine = new MedicinsModel(medicin_id, medicin_name,
				medicines_per_dose, medicineUnit, no_of_days, refill_number,
				notes, afterFood, morning, afterNoon, evening, night,
				frequencyId, frequencyName);

		return medicine;
	}

	

	/***
	 * to set data to the spinner
	 * 
	 * @param rootView
	 */
	public ArrayList<String> addDataToDialog() {
		ArrayList<String> medicineList = new ArrayList<String>();

		for (int i = 0; i < med_list.size(); i++) {
			medicineList.add(med_list.get(i).getName());
		}
		// ArrayList<String> quantityList = new ArrayList<String>();
		// for (int i = 0; i < allunits.size(); i++) {
		// quantityList.add(allunits.get(i).getName());
		// }
		return medicineList;

	}

	/**
	 * 
	 * @return true if all fields contains data.
	 */
	private Boolean validateFields(View rootView) {

		/*String medicines_per_dose = txtMedicinePerDose.getText().toString();*/
		/* String dose_per_day = txtDosePerDay.getText().toString(); */
		String no_of_days = ((EditText)rootView.findViewById(R.id.edtNoOfDays)).getText().toString();
		
		String refill_number = ((EditText)rootView.findViewById(R.id.edtNoOfRefils)).getText().toString();
		String medicine_name = ((CustomEditText)rootView.findViewById(R.id.edtMedicineName)).getText().toString();
		View focusView = null;
		Boolean validation = false;
		if (TextUtils.isEmpty(medicine_name)) {
			((CustomEditText)rootView.findViewById(R.id.edtMedicineName)).setError(getString(R.string.error_field_required));
			focusView = ((CustomEditText)rootView.findViewById(R.id.edtMedicineName));
			validation = true;
		}
		/*if (TextUtils.isEmpty(medicines_per_dose)) {
			txtMedicinePerDose
					.setError(getString(R.string.error_field_required));
			focusView = txtMedicinePerDose;
			validation = true;
		}*/
		/*
		 * if (TextUtils.isEmpty(dose_per_day)) {
		 * txtDosePerDay.setError(getString(R.string.error_field_required));
		 * focusView = txtDosePerDay; validation = true; }
		 */
		if (TextUtils.isEmpty(no_of_days)) {
			((EditText)rootView.findViewById(R.id.edtNoOfDays)).setError(getString(R.string.error_field_required));
			focusView = ((EditText)rootView.findViewById(R.id.edtNoOfDays));
			validation = true;
		}
		if (TextUtils.isEmpty(refill_number)) {
			 ((EditText)rootView.findViewById(R.id.edtNoOfRefils)).setError(getString(R.string.error_field_required));
			focusView =  ((EditText)rootView.findViewById(R.id.edtNoOfRefils));
			validation = true;
		}
		if (TextUtils.isEmpty(((EditText)rootView.findViewById(R.id.typeANote)).getText().toString())) {
			((EditText)rootView.findViewById(R.id.typeANote)).setError(getString(R.string.error_field_required));
			focusView = ((EditText)rootView.findViewById(R.id.typeANote));
			validation = true;
		}
		/*if (medicineSelectedId < 0) {
			txtMedicineName.setError(getString(R.string.medicine_not_selected));
			focusView = txtMedicineName;
			validation = true;
		}
		if (frequency_list.isEmpty()) {
			new loadFrequencies().execute();
			focusView = spnChoseFrequencies;
			validation = true;
		}*/
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

	private void clearFields() {
		txtMedicinePerDose.setText("");
		txtDosePerDay.setText("");
		txtNoOfDays.setText("");
		txtNotes.setText("");
		txtRefillNo.setText("");
		txtMedicineName.setText("");
		txtMedicineName.setError(null); // to remove the error symbol.
		txtMedicineName.setHint(R.string.choose_medicine);
		medicineSelectedId = -1;
		spnDoseTextUnit.setText("");
	}

	/***
	 * Dailog for showing the list of selected medicines
	 */
	public class DialogConfirmPrescription extends Dialog {

		private Activity activity;
		private ListView lstDialogConfirm;

		public DialogConfirmPrescription(Activity context) {
			super(context);
			// TODO Auto-generated constructor stub
			this.activity = context;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.dialog_confirm_prescription);
			lstDialogConfirm = (ListView) findViewById(R.id.lstDialogConfirm);
			/***
			 * code for adding data to listview in dialog
			 */
			if (!prescriptionMedicineList.isEmpty()) {
				ConfirmPrescriptionAdapter adapter = new ConfirmPrescriptionAdapter(
						activity, prescriptionMedicineList);
				lstDialogConfirm.setAdapter(adapter);
				Utilities.getInstance(getActivity().getApplicationContext())
						.setListViewHeightBasedOnChildren(lstDialogConfirm);

			}
			/****
			 * onclicklistener for confirm button
			 */
			((TextView) findViewById(R.id.txtConfirm))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dismiss();
							if (Utilities.getInstance(activity)
									.isNetAvailable()) {

								Fragment fragmentItemDetail;

								fragmentItemDetail = new AddNewPrescription();

								Bundle bundle = new Bundle();
								// bundle.putParcelableArrayList("allmedicins",
								// medicinslist);
								bundle.putParcelable("pat_profile",
										patient_prof);

								bundle.putParcelableArrayList("Frequencies",
										frequency_list);
								bundle.putParcelableArrayList(
										"prescriptionMedicineList",
										prescriptionMedicineList);
								fragmentItemDetail.setArguments(bundle);
								Utilities
										.getInstance(getActivity())
										.changeChildFragment(
												fragmentItemDetail,
												"AddNewPrescription_Medicine_Diagnosis_Fragment",
												getActivity());

								/*
								 * HttpRequestHelper .add_prescriptions(
								 * getActivity(), getActivity()
								 * .getSharedPreferences(
								 * LoginActivity.PREFS_LOGIN_STATUS,
								 * Context.MODE_PRIVATE) .getString(
								 * LoginActivity.PREFS_USERID, ""),
								 * prescriptionMedicineList,
								 * patient_prof.getId(), patient_prof);
								 */
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			/****
			 * onclicklistener for cancel button
			 */
			((TextView) findViewById(R.id.txtCancel))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dismiss();
						}
					});

			/****
			 * onclicklistener for Generate Invoice
			 */
			((TextView) findViewById(R.id.txtGenerateInvoice))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dismiss();
							if (Utilities.getInstance(activity)
									.isNetAvailable()) {

							/*	HttpRequestHelper
										.addPrescriptionsReturnsId(
												getActivity(),
												getActivity()
														.getSharedPreferences(
																LoginActivity.PREFS_LOGIN_STATUS,
																Context.MODE_PRIVATE)
														.getString(
																LoginActivity.PREFS_USERID,
																""),
												prescriptionMedicineList,
												patient_prof.getId(),
												patient_prof);*/
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}
						}
					});

		}

	}

	/******
	 * Dialog for showing the list of Medicines.
	 * 
	 * @author user
	 */
	public class MedicineListDialog extends Dialog {
		private ListView lstDialogConfirm;
		private EditText edtSearch;
		private ArrayList<String> medicineDuplicate = new ArrayList<String>();
		private ArrayList<String> finalArray = new ArrayList<String>();
		private int textlenght = 0;
		private Context con;

		public MedicineListDialog(Context context, ArrayList<String> finalArray) {
			super(context);
			// TODO Auto-generated constructor stub
			this.finalArray = finalArray;
			this.con = context;
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
					medicineDuplicate.clear();
					for (int i = 0; i < finalArray.size(); i++) {
						if (textlenght <= finalArray.get(i).length()) {
							if (edtSearch
									.getText()
									.toString()
									.equalsIgnoreCase(
											(String) finalArray.get(i)
													.subSequence(0, textlenght))) {
								medicineDuplicate.add(finalArray.get(i));
							}
						}
					}
					lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
							R.layout.spinner_item, medicineDuplicate));

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
					medicineSelectedId = finalArray.indexOf(selectedItem);

					txtMedicineName.setText(selectedItem);
					txtMedicineName.setError(null);
					spnDoseTextUnit.setText(med_list.get(medicineSelectedId)
							.getMedicine_unit_name().toString());

				}
			});

		}

	}

	public class loadFrequencies extends
			AsyncTask<Void, Void, ArrayList<FrequencyModel>> {
		private ProgressDialog progress;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progress = ProgressDialog.show(getActivity(), "Loading",
					"Loading...", true, true,
					new DialogInterface.OnCancelListener() {

						@Override
						public void onCancel(DialogInterface dialog) {

						}
					});
		}

		@Override
		protected ArrayList<FrequencyModel> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpPost httppost;

			/* public void uploadPatientProfile(PatientAddModel profile) { */
			ArrayList<FrequencyModel> frequencyModel = new ArrayList<FrequencyModel>();

			String result = "";
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httppost = new HttpPost(
					HttpRequestHelper.BASE_URL_MEDICINE_FREQUENCIES);
			try {
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity resEntity = response.getEntity();
				StringBuilder builder = new StringBuilder();
				if (resEntity != null) {

					InputStream content = resEntity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);

					}
					result = builder.toString();
					frequencyModel = JsonParser.getInstance()
							.getAllFrequencies(result);

				}

				Config.LogError("RESPONSE", builder.toString());
				/* Log.e("RESPONSE", builder.toString()); */

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Config.LogError("ERROR2", e + "");
				/* Log.e("ERROR2", e + ""); */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Config.LogError("ERROR3", e + "");
				/* Log.e("ERROR3", e + ""); */
			}
			return frequencyModel;
		}

		@Override
		protected void onPostExecute(ArrayList<FrequencyModel> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			frequency_list = result;
			/* loadDatatoFrequencySpinner(); */
			if (progress != null) {
				if (progress.isShowing()) {
					progress.cancel();
					progress.dismiss();
				}
			}
		}

	}

	private ArrayList<String> foodArray() {
		ArrayList<String> foodArrayList = new ArrayList<String>();
		foodArrayList.add("After Food");
		foodArrayList.add("Before Food");
		return foodArrayList;
	}

	/****
	 * The method for adding alegy medicine dialog box
	 * 
	 * @param medicineList
	 */
	public void showMedicineDialog(ArrayList<MedicinsListModel> medicineList) {
		MedicineDialog alergiDialog = new MedicineDialog(getActivity(),
				splitMedicineList(medicineList), medicineList);
		alergiDialog.show();
	}

	/****
	 * Method for looping Medicine list Model to string with medicine name only
	 * 
	 * @param medicineList
	 * @return
	 */
	private ArrayList<String> splitMedicineList(
			ArrayList<MedicinsListModel> medicineList) {
		ArrayList<String> list = new ArrayList<String>();
		if (medicineList != null) {
			if (!medicineList.isEmpty()) {
				for (int i = 0; i < medicineList.size(); i++) {
					list.add(medicineList.get(i).getName());
				}
			}
		}
		return list;
	}

	/***
	 * The method for loading data to frequency spinner
	 * 
	 * @param freqList
	 * @param rootView
	 */
	private void loadDatatoFrequencySpinner(ArrayList<FrequencyModel> freqList,
			View rootView) {
		if (!freqList.isEmpty()) {
			ArrayList<String> frequencyNameList = new ArrayList<String>();
			for (int i = 0; i < freqList.size(); i++) {
				frequencyNameList.add(freqList.get(i).getName());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), R.layout.spinner_item, frequencyNameList);
			((Spinner) rootView.findViewById(R.id.spnChoseFrequencies))
					.setAdapter(adapter);
		}
	}

	private void setselectedMedicine(MedicinsListModel medicine) {
		((CustomEditText) getView().findViewById(R.id.edtMedicineName))
				.setText(medicine.getName());
		((CustomEditText) getView().findViewById(R.id.edtMedicineName))
				.setTag(medicine);
		/* addData(getView()); */
	}

	/****
	 * The dialog for showing medicine Names
	 * 
	 * @author Diljith
	 *
	 */
	public class MedicineDialog extends Dialog {
		private ListView lstDialogConfirm;
		private ArrayList<String> finalArray = new ArrayList<String>();
		private ArrayList<MedicinsListModel> medList;
		private Context con;

		public MedicineDialog(Context context, ArrayList<String> finalArray,
				ArrayList<MedicinsListModel> medicineList) {
			super(context);
			// TODO Auto-generated constructor stub
			this.finalArray = finalArray;
			this.con = context;
			this.medList = medicineList;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.dialog_country_list);
			lstDialogConfirm = (ListView) findViewById(R.id.lstCountry);
			((EditText) findViewById(R.id.edtSearchName))
					.setVisibility(View.GONE);
			lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
					R.layout.spinner_item, finalArray));

			lstDialogConfirm.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					dismiss();
					setselectedMedicine(medList.get(position));
					/*
					 * selectedMedicineModel = medList.get(position);
					 * addedAlerieList.add(new
					 * AllergiMedicineModel(selectedMedicineModel.getName(),
					 * selectedMedicineModel.getId())); addMedicineToList();
					 */
				}
			});

		}

	}

}
