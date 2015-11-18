package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.AddNewPrescriptionAdapter;
import com.mobtecnica.medirect.docter.adapters.AddDiagnosticAdapter;
import com.mobtecnica.medirect.docter.adapters.PrescriptionListAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForDiagnostics;
import com.mobtecnica.medirect.docter.fragments.AddMedicineFragment.loadFrequencies;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewPrescription extends Fragment {
	private static String AddNewPrescriptionTag = "AddNewPrescription";
	private static String AddNewPrescriptionMyHistoryTag = "AddNewPrescriptionMyHistory";
	private static String AddNewPrescriptionDiagnosticsArrayTag = "AddNewPrescriptionDiagnosticsArray";
	private static String AddNewPrescriptionMedicineArrayTag = "AddNewPrescriptionMedicinesArray";
	private ArrayList<DiagnosticsModelForAddPrescription> diagnosticsList = new ArrayList<DiagnosticsModelForAddPrescription>();
	private ArrayList<MedicinsModel> addedMedicinesList = new ArrayList<MedicinsModel>();

	/****
	 * The data passed while creating this fragment
	 * 
	 * @param profile
	 * @return
	 */
	public static AddNewPrescription newInstanceOfAddNewPrescription(
			Profile_Model profile, MyHistoryModel myHistory,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(AddNewPrescriptionTag, profile);
		bundle.putParcelable(AddNewPrescriptionMyHistoryTag, myHistory);
		bundle.putParcelableArrayList(AddNewPrescriptionDiagnosticsArrayTag,
				diagNosticModel);
		bundle.putParcelableArrayList(AddNewPrescriptionMedicineArrayTag,
				addedMedicinesList);
		AddNewPrescription fragment = new AddNewPrescription();
		fragment.setArguments(bundle);
		return fragment;
	}
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.newprescription_medicine_diagnosis, container, false);
		TextView menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_prescriptions));
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
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
				final Profile_Model patient_prof = extras
						.getParcelable(AddNewPrescriptionTag);
				final MyHistoryModel myHistory = extras
						.getParcelable(AddNewPrescriptionMyHistoryTag);
				addedMedicinesList = extras
						.getParcelableArrayList(AddNewPrescriptionMedicineArrayTag);
				diagnosticsList = extras
						.getParcelableArrayList(AddNewPrescriptionDiagnosticsArrayTag);
				if (patient_prof != null) {
					((TextView) rootView.findViewById(R.id.txtAddMedicine))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									if (Utilities.getInstance(getActivity())
											.isNetAvailable()) {
										HttpRequestHelper.getAllFrequencies(
												getActivity(), patient_prof,
												myHistory, diagnosticsList,
												addedMedicinesList);
									} else {
										Toast.makeText(getActivity(),
												R.string.error_internet,
												Toast.LENGTH_SHORT).show();
									}

								}
							});
					((TextView) rootView.findViewById(R.id.txtAddDiagnosis))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Fragment

									fragmentItemDetail = AddDiagnosisFragment
											.newInstanceOfAddDiagnosisFragment(
													getActivity(),
													patient_prof, myHistory,
													diagnosticsList,
													addedMedicinesList);
									Utilities.getInstance(getActivity())
											.changeChildFragment(
													fragmentItemDetail,
													"AddDiagnosisFragment",
													getActivity());
								}
							});
					/****
					 * The button for adding history
					 */
					((TextView) rootView.findViewById(R.id.txtAddHistory))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Fragment

									fragmentItemDetail = AddHistoryFragment
											.newInstanceOfAddHistoryFragment(
													patient_prof, myHistory,
													diagnosticsList,
													addedMedicinesList);
									Utilities.getInstance(getActivity())
											.changeChildFragment(
													fragmentItemDetail,
													"AddHistoryFragment",
													getActivity());
								}
							});
					/***
					 * The button for saving prescription
					 */
					((TextView) rootView.findViewById(R.id.txtSaveAndNotify))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									String Diagnostics = ((EditText) rootView
											.findViewById(R.id.edtDiagnosis))
											.getText().toString();
									String Symptomns = ((EditText) rootView
											.findViewById(R.id.edtSymptoms))
											.getText().toString();
									if (!validateFields(rootView)) {
										if (Utilities
												.getInstance(getActivity())
												.isNetAvailable()) {
											if (addedMedicinesList != null) {
												/*if (!addedMedicinesList
														.isEmpty()) {*/
													HttpRequestHelper
															.add_prescriptions(
																	getActivity(),
																	getActivity()
																			.getSharedPreferences(
																					LoginActivity.PREFS_LOGIN_STATUS,
																					Context.MODE_PRIVATE)
																			.getString(
																					LoginActivity.PREFS_USERID,
																					""),
																	addedMedicinesList,
																	patient_prof
																			.getId(),
																	patient_prof,
																	Diagnostics,
																	Symptomns,
																	diagnosticsList);
												/*} else {
													Toast.makeText(
															getActivity(),
															"Add medicine",
															Toast.LENGTH_SHORT)
															.show();
												}*/
											} else {
												Toast.makeText(getActivity(),
														"Add medicine",
														Toast.LENGTH_SHORT)
														.show();
											}
										} else {
											Toast.makeText(getActivity(),
													R.string.error_internet,
													Toast.LENGTH_SHORT).show();

										}
									}

								}
							});
					/***
					 * the button for generating invoice.
					 */
					((TextView) rootView.findViewById(R.id.txtGenerateInvoice))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									String Diagnostics = ((EditText) rootView
											.findViewById(R.id.edtDiagnosis))
											.getText().toString();
									String Symptomns = ((EditText) rootView
											.findViewById(R.id.edtSymptoms))
											.getText().toString();
									if (!validateFields(rootView)) {
										if (Utilities
												.getInstance(getActivity())
												.isNetAvailable()) {
											if (addedMedicinesList != null) {
												if (!addedMedicinesList
														.isEmpty()) {
													HttpRequestHelper
															.addPrescriptionsReturnsId(
																	getActivity(),
																	getActivity()
																			.getSharedPreferences(
																					LoginActivity.PREFS_LOGIN_STATUS,
																					Context.MODE_PRIVATE)
																			.getString(
																					LoginActivity.PREFS_USERID,
																					""),
																	addedMedicinesList,
																	patient_prof
																			.getId(),
																	patient_prof,
																	Diagnostics,
																	Symptomns,
																	diagnosticsList);
												} else {
													Toast.makeText(
															getActivity(),
															"Add medicine",
															Toast.LENGTH_SHORT)
															.show();
												}
											} else {
												Toast.makeText(getActivity(),
														"Add medicine",
														Toast.LENGTH_SHORT)
														.show();
											}

										} else {
											Toast.makeText(getActivity(),
													R.string.error_internet,
													Toast.LENGTH_SHORT).show();

										}
									}

								}
							});
				}
			}

		}
	}

	private void buildUI(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			if (extras != null) {
				Profile_Model patient_prof = extras
						.getParcelable(AddNewPrescriptionTag);
				MyHistoryModel myHistory = extras
						.getParcelable(AddNewPrescriptionMyHistoryTag);
				diagnosticsList = extras
						.getParcelableArrayList(AddNewPrescriptionDiagnosticsArrayTag);
				addedMedicinesList = extras
						.getParcelableArrayList(AddNewPrescriptionMedicineArrayTag);
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
				if (diagnosticsList != null) {
					if (!diagnosticsList.isEmpty()) {
						((ListView) rootView
								.findViewById(R.id.lstDiagnosisList))
								.setAdapter(new AddDiagnosticAdapter(
										getActivity(), diagnosticsList));
						Utilities
								.getInstance(getActivity())
								.setListViewHeightBasedOnChildren(
										((ListView) rootView
												.findViewById(R.id.lstDiagnosisList)));
					}
				}
				if (addedMedicinesList != null) {
					if (!addedMedicinesList.isEmpty()) {
						((ListView) rootView
								.findViewById(R.id.lstNewPrescriptionsList))
								.setAdapter(new AddNewPrescriptionAdapter(
										getActivity(), addedMedicinesList));
						Utilities
								.getInstance(getActivity())
								.setListViewHeightBasedOnChildren(
										((ListView) rootView
												.findViewById(R.id.lstNewPrescriptionsList)));
					}
				}
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

	private Boolean validateFields(View rootView) {

		String diagnostics = ((EditText) rootView
				.findViewById(R.id.edtDiagnosis)).getText().toString();
		/* String dose_per_day = txtDosePerDay.getText().toString(); */
		String Symptoms = ((EditText) rootView.findViewById(R.id.edtSymptoms))
				.getText().toString();

		View focusView = null;
		Boolean validation = false;
		if (TextUtils.isEmpty(diagnostics)) {
			((EditText) rootView.findViewById(R.id.edtDiagnosis))
					.setError(getString(R.string.error_field_required));
			focusView = ((EditText) rootView.findViewById(R.id.edtDiagnosis));
			validation = true;
		}
		if (TextUtils.isEmpty(Symptoms)) {
			((EditText) rootView.findViewById(R.id.edtSymptoms))
					.setError(getString(R.string.error_field_required));
			focusView = ((EditText) rootView.findViewById(R.id.edtSymptoms));
			validation = true;
		}

		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

}
