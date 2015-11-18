package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.Calendar;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.SplashScreen;
import com.mobtecnica.medirect.docter.adapters.PrescriptionDetailAdapterNew;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForPrescriptionDetailNew;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailViewModelnew;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PrescriptionDetailFragmentNew extends Fragment {

	private static String prescriptionsDetailTag = "PrescriptionDetail";
	private static String prescriptionsPatientProfileTag = "prescriptionsPatientProfile";
	private static String prescriptionsPatientHistoryTag = "prescriptionsPatientHistory";
	/* private ArrayList<MyMedicine> medicineList; */
	public ArrayList<PrescriptionDetailViewModelnew> presDetail;
	Spinner spinnerDisplayItems;
	TextView editTextFromDate, menuname;
	TextView editTextToDate;
	Button buttonSearch;
	ListView listviewPrescriptionItem;
	public PrescriptionDetailAdapterNew listadapter;

	int mYear;
	int mMonth;
	int mDay;
	boolean all_visible = true;
	boolean doctor_notes_visible = true;
	boolean medicines_visible = true;
	boolean diagnosis_visible = true;
	public boolean loadingMore = false;

	public static PrescriptionDetailFragmentNew newInstanceOfPrescriptionDetailFragmentNew(
			ArrayList<PrescriptionDetailViewModelnew> presDetail,
			Profile_Model profile, MyHistoryModel myHistory) {
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList(prescriptionsDetailTag, presDetail);
		bundle.putParcelable(prescriptionsPatientProfileTag, profile);
		bundle.putParcelable(prescriptionsPatientHistoryTag, myHistory);
		PrescriptionDetailFragmentNew fragment = new PrescriptionDetailFragmentNew();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * getActivity().setTitle("Prescription");
		 * MainActivity.setActionBarTitle("Prescription");
		 */
		View rootView = inflater.inflate(
				R.layout.fragment_precription_detailnew, container, false);
		initializeViews(rootView);
		buildUI(rootView);
		initializeListeners(rootView);
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_prescriptions));

		// display the current date (this method is below)
		updateDisplayFromdate();
		updateDisplayTodate();
		return rootView;
	}

	private void initializeListeners(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			((ImageButton) rootView.findViewById(R.id.imageViewuser_nav))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							SharedPreferences.Editor editor = getActivity()
									.getSharedPreferences(
											LoginActivity.PREFS_LOGIN_STATUS,
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
						.getParcelable(prescriptionsPatientProfileTag);
				final MyHistoryModel myHistory = extras
						.getParcelable(prescriptionsPatientHistoryTag);
				/*
				 * buttonSearch.setOnClickListener(new View.OnClickListener() {
				 * 
				 * @Override public void onClick(View v) { if
				 * (Utilities.getInstance(getActivity()) .isNetAvailable()) {
				 * HttpRequestHelperForPrescriptionDetailNew
				 * .prescriptionDetailNew( getActivity(), getActivity()
				 * .getSharedPreferences( LoginActivity.PREFS_LOGIN_STATUS,
				 * Context.MODE_PRIVATE) .getString( LoginActivity.PREFS_USERID,
				 * ""), editTextFromDate.getText() .toString(), editTextToDate
				 * .getText().toString(), "0", patient_prof, myHistory); } else
				 * { Toast.makeText(getActivity(), R.string.error_internet,
				 * Toast.LENGTH_SHORT) .show(); }
				 * 
				 * } });
				 */
				buttonSearch.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {
							HttpRequestHelperForPrescriptionDetailNew
									.prescriptionDetailNew(getActivity(),
											patient_prof.getId(),
											editTextFromDate.getText()
													.toString(), editTextToDate
													.getText().toString(), "0",
											patient_prof, myHistory, false);
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}

					}
				});
				editTextFromDate.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new DatePickerDialog(getActivity(),
								mDateSetListenerFromDate, mYear, mMonth, mDay)
								.show();
					}
				});
				editTextToDate.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new DatePickerDialog(getActivity(),
								mDateSetListenerToDate, mYear, mMonth, mDay)
								.show();
					}
				});
				spinnerDisplayItems
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								if (position == 0) {
									all_visible = true;
									doctor_notes_visible = true;
									medicines_visible = true;
									diagnosis_visible = true;
								} else if (position == 1) {
									all_visible = true;
									doctor_notes_visible = true;
									medicines_visible = false;
									diagnosis_visible = false;
								} else if (position == 2) {
									all_visible = true;
									doctor_notes_visible = false;
									medicines_visible = true;
									diagnosis_visible = false;
								} else if (position == 3) {
									all_visible = true;
									doctor_notes_visible = false;
									medicines_visible = false;
									diagnosis_visible = true;
								}
								if (!presDetail.isEmpty()) {
									listadapter = new PrescriptionDetailAdapterNew(
											getActivity(), presDetail,
											all_visible, doctor_notes_visible,
											medicines_visible,
											diagnosis_visible);
									listviewPrescriptionItem
											.setAdapter(listadapter);
									Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(listviewPrescriptionItem);
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								all_visible = true;
								doctor_notes_visible = true;
								medicines_visible = true;
								diagnosis_visible = true;

							}
						});
			}

		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/*
		 * getActivity().setTitle( "Prescription Details"); MainActivity
		 * .setActionBarTitle("Prescription Details");
		 */
	}

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle bun = getArguments();
			if (!bun.isEmpty()) {
				presDetail = bun.getParcelableArrayList(prescriptionsDetailTag);
			}
			if (!presDetail.isEmpty()) {
				listadapter = new PrescriptionDetailAdapterNew(getActivity(),
						presDetail, all_visible, doctor_notes_visible,
						medicines_visible, diagnosis_visible);
				listviewPrescriptionItem.setAdapter(listadapter);
				Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(listviewPrescriptionItem);
			}
			// if (presDetail != null) {
			// ((TextView) rootView.findViewById(R.id.txtDate))
			// .setText("Prescription : "
			// + presDetail.getAddedOn().toString().split(" ")[0]);
			// ((TextView) rootView.findViewById(R.id.txtType))
			// .setText("Type : " + presDetail.getType());
			// ((TextView) rootView.findViewById(R.id.txtDoctor))
			// .setText("Doctor : " + presDetail.getDoctorName());
			// }
			// if (medicineList != null) {
			// PrescriptionMedicineListAdapter adapter = new
			// PrescriptionMedicineListAdapter(
			// getActivity(), medicineList);
			// ((ListView) rootView.findViewById(R.id.lstPrescriptionDetail))
			// .setAdapter(adapter);
			// }

			if (bun != null) {
				final Profile_Model patient_prof = bun
						.getParcelable(prescriptionsPatientProfileTag);
				final MyHistoryModel myHistory = bun
						.getParcelable(prescriptionsPatientHistoryTag);
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
				if (!presDetail.isEmpty()) {
					listviewPrescriptionItem
							.setOnScrollListener(new OnScrollListener() {

								@Override
								public void onScrollStateChanged(
										AbsListView view, int scrollState) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onScroll(AbsListView view,
										int firstVisibleItem,
										int visibleItemCount, int totalItemCount) {
									int lastInScreen = firstVisibleItem
											+ visibleItemCount;
									if ((lastInScreen == presDetail.size())
											&& !(loadingMore)) {
										if (Utilities
												.getInstance(getActivity())
												.checkCondition(
														presDetail.size())) {
											loadingMore = true;
											if (Utilities.getInstance(
													getActivity())
													.isNetAvailable()) {
												HttpRequestHelperForPrescriptionDetailNew
														.prescriptionDetailNew(
																getActivity(),
																patient_prof
																		.getId(),
																editTextFromDate
																		.getText()
																		.toString(),
																editTextToDate
																		.getText()
																		.toString(),
																presDetail
																		.size()
																		+ "",
																patient_prof,
																myHistory, true);

											} else {
												Toast.makeText(
														getActivity(),
														R.string.error_internet,
														Toast.LENGTH_SHORT)
														.show();
											}
										}

									}
								}
							});
				}
			}
		}
	}

	private void initializeViews(View rootView) {
		spinnerDisplayItems = (Spinner) rootView
				.findViewById(R.id.spnDisplayItems);
		editTextFromDate = (TextView) rootView.findViewById(R.id.edtFromDate);
		editTextToDate = (TextView) rootView.findViewById(R.id.edtToDate);
		buttonSearch = (Button) rootView.findViewById(R.id.btnSearch);
		listviewPrescriptionItem = (ListView) rootView
				.findViewById(R.id.listviewPrescriptionItem);
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
		if (editTextFromDate != null) {
			editTextFromDate.setText(new StringBuilder()
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
		if (editTextToDate != null) {
			editTextToDate.setText(new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay).append(" "));
		}
	}

}
