package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mobtecnica.medirect.docter.ItemDetailActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.AddDiagnosticAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForDiagnostics;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsTypeModel;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.mobtecnica.medirect.doctor.ayncTask.LoadDiagnosticsTypes;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddDiagnosisFragment extends Fragment {

	private static String AddDiagnosisFragmentTag = "AddDiagnosisFragment";
	private static String AddDiagnosisFragmentMyHistoryTag = "AddDiagnosisFragmentMyHistory";
	private static String AddDiagnosisFragmentTypesTag = "AddDiagnosisFragmentTypes";
	private static String AddDiagnosisFragmentDiagnosticsArrayTag = "AddDiagnosisFragmentDiagnosticsArray";
	private static String AddDiagnosisFragmentMedicinesArrayTag = "AddDiagnosisFragmentMedicinesArray";
	private DiagnosticsModelForAddPrescription diagnosticsModelForAddPrescription = new DiagnosticsModelForAddPrescription();
	private ArrayList<DiagnosticsModelForAddPrescription> diagnosticsList;

	/****
	 * The data passed while creating this fragment
	 * 
	 * @param profile
	 * @return
	 */
	public static AddDiagnosisFragment newInstanceOfAddDiagnosisFragment(
			Activity act, Profile_Model profile, MyHistoryModel myHistory,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(AddDiagnosisFragmentTag, profile);
		bundle.putParcelable(AddDiagnosisFragmentMyHistoryTag, myHistory);
		bundle.putParcelableArrayList(AddDiagnosisFragmentDiagnosticsArrayTag,
				diagNosticModel);
		bundle.putParcelableArrayList(AddDiagnosisFragmentMedicinesArrayTag,
				addedMedicinesList);
		AddDiagnosisFragment fragment = new AddDiagnosisFragment();
		LoadDiagnosticsTypes types = new LoadDiagnosticsTypes(act);
		ArrayList<DiagnosticsTypeModel> testList = null;
		try {
			testList = types.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bundle.putParcelableArrayList(AddDiagnosisFragmentTypesTag, testList);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.newprescription_diagnosis,
				container, false);

		TextView menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_Diagnostics));
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			if (extras != null) {
				Profile_Model patient_prof = extras
						.getParcelable(AddDiagnosisFragmentTag);
				MyHistoryModel myHistory = extras
						.getParcelable(AddDiagnosisFragmentMyHistoryTag);
				ArrayList<DiagnosticsTypeModel> testList = extras
						.getParcelableArrayList(AddDiagnosisFragmentTypesTag);
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
				if (testList != null) {
					((Spinner) rootView.findViewById(R.id.spnDiagnosticsType))
							.setAdapter(new ArrayAdapter<String>(getActivity(),
									R.layout.spinner_item,
									addDiagnosticsTypeName(testList)));
				}

			}
		}

	}

	private void initializeListeners(final View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
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
			if (extras != null) {
				final ArrayList<DiagnosticsTypeModel> testList = extras
						.getParcelableArrayList(AddDiagnosisFragmentTypesTag);
				final ArrayList<MedicinsModel> addedMedicineList = extras
						.getParcelableArrayList(AddDiagnosisFragmentMedicinesArrayTag);
				final Profile_Model patient_prof = extras
						.getParcelable(AddDiagnosisFragmentTag);
				final MyHistoryModel myHistory = extras
						.getParcelable(AddDiagnosisFragmentMyHistoryTag);
				diagnosticsList = extras
						.getParcelableArrayList(AddDiagnosisFragmentDiagnosticsArrayTag);

				((TextView) rootView.findViewById(R.id.txtTestName))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (Utilities.getInstance(getActivity())
										.isNetAvailable()) {
									String typeId = testList
											.get(((Spinner) rootView
													.findViewById(R.id.spnDiagnosticsType))
													.getSelectedItemPosition())
											.getid();
									HttpRequestHelperForDiagnostics
											.getDiagnostictsTests(
													getActivity(), typeId);
								} else {
									Toast.makeText(getActivity(),
											R.string.error_internet,
											Toast.LENGTH_SHORT).show();
								}
							}
						});
				((TextView) rootView.findViewById(R.id.txtAdd))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (!validateFields(rootView)) {
									String sample = ((EditText) rootView
											.findViewById(R.id.edtSample))
											.getText().toString();
									String typeName = testList
											.get(((Spinner) rootView
													.findViewById(R.id.spnDiagnosticsType))
													.getSelectedItemPosition())
											.getName();

									diagnosticsModelForAddPrescription.setSample(sample);
									diagnosticsModelForAddPrescription
											.setDiagnosticsType(typeName);
									if (diagnosticsList != null) {
										diagnosticsList.add(diagnosticsModelForAddPrescription);
									} else {
										diagnosticsList = new ArrayList<DiagnosticsModelForAddPrescription>();
										diagnosticsList.add(diagnosticsModelForAddPrescription);
									}
									Fragment frg = AddNewPrescription
											.newInstanceOfAddNewPrescription(
													patient_prof, myHistory,
													diagnosticsList,
													addedMedicineList);
									Utilities.getInstance(getActivity())
											.changeChildFragment(frg,
													"AddNewPrescription",
													getActivity());
								}

							}
						});

				((TextView) rootView.findViewById(R.id.txtCancel))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Fragment frg = AddNewPrescription
										.newInstanceOfAddNewPrescription(
												patient_prof, myHistory,
												diagnosticsList,
												addedMedicineList);
								Utilities.getInstance(getActivity())
										.changeChildFragment(frg,
												"AddNewPrescription",
												getActivity());
							}
						});
				((Spinner) rootView.findViewById(R.id.spnDiagnosticsType))
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								((TextView) rootView
										.findViewById(R.id.txtTestName))
										.setText("");
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});
			}
		}

	}

	private Boolean validateFields(View rootView) {

		String testName = ((TextView) rootView.findViewById(R.id.txtTestName))
				.getText().toString();
		String Sample = ((EditText) rootView.findViewById(R.id.edtSample))
				.getText().toString();

		View focusView = null;
		Boolean validation = false;

		if (TextUtils.isEmpty(testName)) {
			((TextView) rootView.findViewById(R.id.txtTestName))
					.setError(getString(R.string.error_field_required));
			focusView = ((TextView) rootView.findViewById(R.id.txtTestName));
			validation = true;
		}
		if (TextUtils.isEmpty(Sample)) {
			((EditText) rootView.findViewById(R.id.edtSample))
					.setError(getString(R.string.error_field_required));
			focusView = ((EditText) rootView.findViewById(R.id.edtSample));
			validation = true;
		}
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
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
	 */
	private ArrayList<String> addDiagnosticsTypeName(
			ArrayList<DiagnosticsTypeModel> test) {
		ArrayList<String> type = new ArrayList<String>();
		if (test != null) {
			if (!test.isEmpty()) {
				for (int i = 0; i < test.size(); i++) {
					type.add(test.get(i).getName());
				}
			}
		}

		return type;

	}

	private void AddingSelectedDiagnosticsName(DiagnosticsTestModel test) {
		diagnosticsModelForAddPrescription.setDiagnosticsTestId(test.getId());
		diagnosticsModelForAddPrescription.setDiagnosticsTestName(test.getTestname() + " ( "
				+ test.getTest() + " )");
		((TextView) getView().findViewById(R.id.txtTestName)).setText(test
				.getTestname() + " ( " + test.getTest() + " )");
	}

	/****
	 * The method for adding DiagnosticTypeDialog medicine dialog box
	 * 
	 * @param medicineList
	 */
	public void showDiagnosticsDialog(
			ArrayList<DiagnosticsTestModel> diagnosticsList) {
		DiagnosticTypeDialog alergiDialog = new DiagnosticTypeDialog(
				getActivity(), splitMedicineList(diagnosticsList),
				diagnosticsList);
		alergiDialog.show();
	}

	/****
	 * Method for looping Medicine list Model to string with medicine name only
	 * 
	 * @param medicineList
	 * @return
	 */
	private ArrayList<String> splitMedicineList(
			ArrayList<DiagnosticsTestModel> medicineList) {
		ArrayList<String> list = new ArrayList<String>();
		if (medicineList != null) {
			if (!medicineList.isEmpty()) {
				for (int i = 0; i < medicineList.size(); i++) {
					list.add(medicineList.get(i).getTestname() + " ( "
							+ medicineList.get(i).getTest() + " )");
				}
			}
		}
		return list;
	}

	/****
	 * The dialog for showing DiagnosticTypeDialog Names
	 * 
	 * @author Diljith
	 *
	 */
	public class DiagnosticTypeDialog extends Dialog {
		private ListView lstDialogConfirm;
		private ArrayList<String> finalArray = new ArrayList<String>();
		private ArrayList<DiagnosticsTestModel> digList;
		private Context con;

		public DiagnosticTypeDialog(Context context,
				ArrayList<String> finalArray,
				ArrayList<DiagnosticsTestModel> diagnosticList) {
			super(context);
			// TODO Auto-generated constructor stub
			this.finalArray = finalArray;
			this.con = context;
			this.digList = diagnosticList;
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
					AddingSelectedDiagnosticsName(digList.get(position));
				}
			});

		}

	}
}
