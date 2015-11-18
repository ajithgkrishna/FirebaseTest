package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.ItemDetailActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.AddedAlergiesAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForRecentPatients;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelperForSaveHistory;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.DiagnosticsTypeModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.CustomEditText;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.mobtecnica.medirect.docter.utils.CustomEditText.DrawableClickListener;
import com.mobtecnica.medirect.docter.utils.CustomEditText.DrawableClickListener.DrawablePosition;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddHistoryFragment extends Fragment {

	private static String AddHistoryFragmentTag = "AddHistoryFragment";
	private static String AddHistoryFragmentMyHistoryTag = "AddHistoryFragmentMyHistory";
	private static String AddHistoryFragmentAddedMedicineTag = "AddHistoryFragmentAddedMedicine";
	private static String AddHistoryFragmentdiagnosticsTag = "AddHistoryFragmentdiagnostics";
	private MedicinsListModel selectedMedicineModel;
	private AddedAlergiesAdapter addedAlergiesAdapter;
	private ArrayList<AllergiMedicineModel> addedAlerieList = new ArrayList<AllergiMedicineModel>();

	/****
	 * The data passed while creating this fragment
	 * 
	 * @param profile
	 * @return
	 */
	public static AddHistoryFragment newInstanceOfAddHistoryFragment(
			Profile_Model profile, MyHistoryModel myHistory,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(AddHistoryFragmentTag, profile);
		bundle.putParcelable(AddHistoryFragmentMyHistoryTag, myHistory);
		bundle.putParcelableArrayList(AddHistoryFragmentdiagnosticsTag,
				diagNosticModel);
		bundle.putParcelableArrayList(AddHistoryFragmentAddedMedicineTag,
				addedMedicinesList);
		AddHistoryFragment fragment = new AddHistoryFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.newprescription_history,
				container, false);
		TextView menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_History));
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		Bundle extras = getArguments();
		if (rootView != null) {
			if (extras != null) {
				Profile_Model patient_prof = extras
						.getParcelable(AddHistoryFragmentTag);
				MyHistoryModel myHistory = extras
						.getParcelable(AddHistoryFragmentMyHistoryTag);
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
					setDataToDisplayView(myHistory,rootView);
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
				final Profile_Model patient_prof = extras
						.getParcelable(AddHistoryFragmentTag);
				final ArrayList<DiagnosticsModelForAddPrescription> testList = extras
						.getParcelableArrayList(AddHistoryFragmentdiagnosticsTag);
				final MyHistoryModel myHistory = extras
						.getParcelable(AddHistoryFragmentMyHistoryTag);
				final ArrayList<MedicinsModel> addedMedicineList = extras
						.getParcelableArrayList(AddHistoryFragmentMyHistoryTag);
				((CustomEditText) rootView.findViewById(R.id.edtAllergies))
						.setDrawableClickListener(new DrawableClickListener() {

							@Override
							public void onClick(DrawablePosition target) {
								// TODO Auto-generated method stub
								switch (target) {
								case RIGHT:
									if (Utilities.getInstance(getActivity())
											.isNetAvailable()) {
										String key = ((CustomEditText) getView()
												.findViewById(R.id.edtAllergies))
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

				((TextView) rootView.findViewById(R.id.txtSaveAndNotify))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (!validateFields(rootView)) {
									if (Utilities.getInstance(getActivity())
											.isNetAvailable()) {
										HttpRequestHelperForSaveHistory
												.SaveHistory(
														getActivity(),
														addDataToMyHistoryModel(patient_prof
																.getId()),
														patient_prof, testList,
														addedMedicineList);
									} else {
										Toast.makeText(getActivity(),
												R.string.error_internet,
												Toast.LENGTH_SHORT).show();
									}
								}
								

							}
						});

				((TextView) rootView.findViewById(R.id.txtCancel))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Fragment frg = AddNewPrescription.newInstanceOfAddNewPrescription(
										patient_prof, myHistory,testList,addedMedicineList);
								Utilities.getInstance(getActivity()).changeChildFragment(
										frg, "AddNewPrescription", getActivity());

							}
						});
			}
		}

	}
	
	private Boolean validateFields(View rootView) {

		String familyHistory = ((TextView) rootView.findViewById(R.id.edtFamilyHistory))
				.getText().toString();
		String surgicalHistory = ((EditText) rootView.findViewById(R.id.edtSurgicalHistory))
				.getText().toString();
		View focusView = null;
		Boolean validation = false;

		if (TextUtils.isEmpty(familyHistory)) {
			((TextView) rootView.findViewById(R.id.edtFamilyHistory))
					.setError(getString(R.string.error_field_required));
			focusView = ((TextView) rootView.findViewById(R.id.edtFamilyHistory));
			validation = true;
		}
		if (TextUtils.isEmpty(surgicalHistory)) {
			((EditText) rootView.findViewById(R.id.edtSurgicalHistory))
					.setError(getString(R.string.error_field_required));
			focusView = ((EditText) rootView.findViewById(R.id.edtSurgicalHistory));
			validation = true;
		}
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

	/***
	 * the method for adding data to display
	 * @param myHistory
	 * @param rootView
	 */
	
	private void setDataToDisplayView(MyHistoryModel myHistory,View rootView){
		((EditText) rootView.findViewById(
				R.id.edtSurgicalHistory)).setText(myHistory.getSurgical_history());
		((EditText) rootView.findViewById(
				R.id.edtFamilyHistory)).setText(myHistory.getFamily_history());
		addedAlerieList = myHistory.getAllergies();
		if (addedAlerieList != null) {
			if (!addedAlerieList.isEmpty()) {
				addMedicineToList(rootView) ;
			}
		}
		if (myHistory.getDm_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.dm)).setChecked(true);
		}
		if (myHistory.getHtn_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.htn)).setChecked(true);
		}
		if (myHistory.getThyroid_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.thyroid)).setChecked(true);
		}
		if (myHistory.getAsthma_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.asthma)).setChecked(true);
		}
		if (myHistory.getTumor_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.tumors)).setChecked(true);
		}
		if (myHistory.getCa_status().equals("1")) {
			((CheckBox) rootView.findViewById(R.id.ca)).setChecked(true);
		}
	}
	
	/****
	 * The method for adding edited data to MyHistoryModel class
	 * 
	 * @param patientId
	 * @return
	 */
	public MyHistoryModel addDataToMyHistoryModel(String patientId) {
		String userId = getActivity().getSharedPreferences(
				LoginActivity.PREFS_LOGIN_STATUS, Context.MODE_PRIVATE)
				.getString(LoginActivity.PREFS_USERID, "");
		String surgicalHistory = ((EditText) getView().findViewById(
				R.id.edtSurgicalHistory)).getText().toString();
		String familyHistory = ((EditText) getView().findViewById(
				R.id.edtFamilyHistory)).getText().toString();
		String dm, htn, thyroid, asthma, tumors, ca;

		if (((CheckBox) getView().findViewById(R.id.dm)).isChecked()) {
			dm = "1";
		} else {
			dm = "0";
		}
		if (((CheckBox) getView().findViewById(R.id.htn)).isChecked()) {
			htn = "1";
		} else {
			htn = "0";
		}
		if (((CheckBox) getView().findViewById(R.id.thyroid)).isChecked()) {
			thyroid = "1";
		} else {
			thyroid = "0";
		}
		if (((CheckBox) getView().findViewById(R.id.asthma)).isChecked()) {
			asthma = "1";
		} else {
			asthma = "0";
		}
		if (((CheckBox) getView().findViewById(R.id.tumors)).isChecked()) {
			tumors = "1";
		} else {
			tumors = "0";
		}
		if (((CheckBox) getView().findViewById(R.id.ca)).isChecked()) {
			ca = "1";
		} else {
			ca = "0";
		}
		MyHistoryModel historyModel = new MyHistoryModel(userId, patientId,
				familyHistory, surgicalHistory, dm, htn, thyroid, asthma,
				tumors, ca, addedAlerieList);
		return historyModel;
	}

	/****
	 * The method for adding alegy medicine dialog box
	 * 
	 * @param medicineList
	 */
	public void showAlergyMedicineDialog(
			ArrayList<MedicinsListModel> medicineList) {
		AlergiMedicineDialog alergiDialog = new AlergiMedicineDialog(
				getActivity(), splitMedicineList(medicineList), medicineList);
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
	 * The method for adding selected medicine to list with a delete button
	 */
	private void addMedicineToList(View rootView) {
		addedAlergiesAdapter = new AddedAlergiesAdapter(getActivity(),
				addedAlerieList);
		((ListView) rootView.findViewById(R.id.lstaddedAlergies))
				.setAdapter(addedAlergiesAdapter);
		Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(
				((ListView) rootView.findViewById(R.id.lstaddedAlergies)));

	}

	/****
	 * The dialog for showing medicine Names
	 * 
	 * @author Diljith
	 *
	 */
	public class AlergiMedicineDialog extends Dialog {
		private ListView lstDialogConfirm;
		private ArrayList<String> finalArray = new ArrayList<String>();
		private ArrayList<MedicinsListModel> medList;
		private Context con;

		public AlergiMedicineDialog(Context context,
				ArrayList<String> finalArray,
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
					selectedMedicineModel = medList.get(position);
					addedAlerieList.add(new AllergiMedicineModel(
							selectedMedicineModel.getName(),
							selectedMedicineModel.getId()));
					addMedicineToList(getView());
				}
			});

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

}
