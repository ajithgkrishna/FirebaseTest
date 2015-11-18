package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.HomeAppoinmentAdapter;
import com.mobtecnica.medirect.docter.adapters.PatientListHomeAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForPrescriptionDetailNew;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForRecentPatients;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;
import com.mobtecnica.medirect.docter.utils.CustomEditText;
import com.mobtecnica.medirect.docter.utils.CustomEditText.DrawableClickListener;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.mobtecnica.medirect.doctor.ayncTask.LoadMyHistory;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	TextView menuname;
	private ArrayList<RecentPrescriptions> recent_prescriptions;
	private ArrayList<Appointments> appointmentList;
	private final String PREFS_LOGIN_STATUS = "LOGIN";
	public final static String HOME_PAGE_APPOINMENT_TAG = "HomePage";


	public HomeFragment() {
	}

	public static HomeFragment newInstanceOfHomeFragment(
			ArrayList<Appointments> appoinmentList) {
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList(HOME_PAGE_APPOINMENT_TAG, appoinmentList);
		HomeFragment fragment = new HomeFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_home));

		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle extras = getArguments();
			if (extras != null) {
				appointmentList = extras
						.getParcelableArrayList(HOME_PAGE_APPOINMENT_TAG);
				if (appointmentList != null) {
					/*
					 * PatientListHomeAdapter adapterPalistPatient = new
					 * PatientListHomeAdapter( getActivity(),
					 * recent_prescriptions); ((ListView) rootView
					 * .findViewById(
					 * R.id.listViewPatientList)).setAdapter(adapterPalistPatient
					 * ); Utilities.getInstance(getActivity()).
					 * setListViewHeightBasedOnChildren( ((ListView) rootView
					 * .findViewById(R.id.listViewPatientList)));
					 */
					HomeAppoinmentAdapter appointmentAdapter = new HomeAppoinmentAdapter(
							getActivity(), appointmentList);
					((ListView) rootView
							.findViewById(R.id.lstTodaysAppointMents))
							.setAdapter(appointmentAdapter);
					Utilities
							.getInstance(getActivity())
							.setListViewHeightBasedOnChildren(
									((ListView) rootView
											.findViewById(R.id.lstTodaysAppointMents)));
				}

			}

		}
	}

	public void addDataToPatientListView(
			final ArrayList<Profile_Model> patientProfileList) {
		if (patientProfileList != null) {
			if (!patientProfileList.isEmpty()) {
				((LinearLayout) getView().findViewById(R.id.linearPatientList))
						.setVisibility(View.VISIBLE);
				PatientListHomeAdapter adapterPalistPatient = new PatientListHomeAdapter(
						getActivity(), patientProfileList);
				((ListView) getView().findViewById(R.id.listViewPatientList))
						.setAdapter(adapterPalistPatient);
				Utilities.getInstance(getActivity())
						.setListViewHeightBasedOnChildren(
								((ListView) getView().findViewById(
										R.id.listViewPatientList)));
			
				((ListView) getView().findViewById(R.id.listViewPatientList))
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								if (Utilities.getInstance(getActivity())
										.isNetAvailable()) {
									LoadMyHistory myHistory = new LoadMyHistory(getActivity(), patientProfileList.get(position).getId());
									MyHistoryModel myHistoryModel = null;
									try {
										
										myHistoryModel = myHistory.execute().get();
										
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									HttpRequestHelperForPrescriptionDetailNew
									.prescriptionDetailNew(
											getActivity(),
											 patientProfileList.get(position).getId(), "", "", "0", patientProfileList.get(position),myHistoryModel,true);
								} else {
									Toast.makeText(getActivity(),
											R.string.error_internet,
											Toast.LENGTH_SHORT).show();
								}

							}
						});
					
			} else {
				Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();
		}
	}

	private void initializeListeners(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			((CustomEditText) rootView.findViewById(R.id.edtSearchKeyWord))
					.setDrawableClickListener(new DrawableClickListener() {

						@Override
						public void onClick(DrawablePosition target) {
							// TODO Auto-generated method stub
							switch (target) {
							case RIGHT:
								if (Utilities.getInstance(getActivity())
										.isNetAvailable()) {
									String key = ((CustomEditText) getView()
											.findViewById(R.id.edtSearchKeyWord))
											.getText().toString();
									if (key != null) {
										if (!key.equals("")) {
											HttpRequestHelperForRecentPatients
													.getSearchPatients(
															getActivity(), key);
										}
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

			((ImageButton) rootView.findViewById(R.id.imageViewuser_nav))
					.setOnClickListener(new View.OnClickListener() {

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
			((TextView) rootView.findViewById(R.id.textViewAddPatients))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							if (Utilities.getInstance(getActivity())
									.isNetAvailable()) {
								HttpRequestHelper
										.getAllCountries(getActivity());
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}

						}
					});
			/*
			 * textViewAddNewPrescription .setOnClickListener(new
			 * View.OnClickListener() {
			 * 
			 * @Override public void onClick(View v) { if
			 * (Utilities.getInstance(getActivity()) .isNetAvailable()) {
			 * HttpRequestHelper.getPrescriptionList( getActivity(), "", "", "",
			 * "", ""); } else { Toast.makeText(getActivity(),
			 * R.string.error_internet, Toast.LENGTH_SHORT) .show(); } } });
			 */
			
			if (extras != null) {
				appointmentList = extras
						.getParcelableArrayList(HOME_PAGE_APPOINMENT_TAG);
				if (appointmentList != null) {
					((ListView) rootView
							.findViewById(R.id.lstTodaysAppointMents))
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									Appointments appointments = appointmentList.get(position);
									Profile_Model patientProfile = new Profile_Model(appointments.getPatient_id(), 
											appointments.getPatient_accountNo(), appointments.getPatient_name(), "",
											appointments.getAddress(), appointments.getPatientEmail(), appointments.getPatientPhone(),
											appointments.getPatientPhone(), appointments.getAge(),appointments.getLastVisit(),appointments.getPhoto());
									LoadMyHistory myHistory = new LoadMyHistory(getActivity(), patientProfile.getId());
									MyHistoryModel myHistoryModel = null;
									try {
										
										myHistoryModel = myHistory.execute().get();
										
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									HttpRequestHelperForPrescriptionDetailNew
									.prescriptionDetailNew(
											getActivity(),
											patientProfile.getId(), "", "", "0",patientProfile,myHistoryModel,false);
								}
							});
				}
			}
			((TextView) rootView.findViewById(R.id.buttonSeeMorePatientList))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							if (Utilities.getInstance(getActivity())
									.isNetAvailable()) {
								HttpRequestHelper.getPrescriptionList(
										getActivity(), "", "", "", "", "",false,"0");
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}

						}
					});
			((TextView) rootView
					.findViewById(R.id.buttonSeeMoreTodaysAppointMents))
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							if (Utilities.getInstance(getActivity())
									.isNetAvailable()) {
								HttpRequestHelper.getPrescriptionList(
										getActivity(), "", "", "", "", "",false,"0");
							} else {
								Toast.makeText(getActivity(),
										R.string.error_internet,
										Toast.LENGTH_SHORT).show();
							}

						}
					});
		}

	}

}
