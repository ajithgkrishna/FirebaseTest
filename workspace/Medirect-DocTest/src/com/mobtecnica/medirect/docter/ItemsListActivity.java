package com.mobtecnica.medirect.docter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.mobtecnica.medirect.docter.adapters.AppointmentsHomeAdapter;
import com.mobtecnica.medirect.docter.adapters.Inbox_Users_ListAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.connection.HttpRequesthelperForAppointments;
import com.mobtecnica.medirect.docter.fragments.AppointMentsFragment;
import com.mobtecnica.medirect.docter.fragments.DeliveryAddressFragment;
import com.mobtecnica.medirect.docter.fragments.EditProfileFragment;
import com.mobtecnica.medirect.docter.fragments.HomeFragment;
import com.mobtecnica.medirect.docter.fragments.InboxFragment;
import com.mobtecnica.medirect.docter.fragments.InboxViewMessage;
import com.mobtecnica.medirect.docter.fragments.ItemsListFragment;
import com.mobtecnica.medirect.doctor.ayncTask.LoadFrequenciesAsync;
import com.mobtecnica.medirect.doctor.ayncTask.LoadPaymentMethodsAsyc;
import com.mobtecnica.medirect.doctor.ayncTask.LoadPurchaseOrderList;
import com.mobtecnica.medirect.docter.fragments.AddDiagnosisFragment;
import com.mobtecnica.medirect.docter.fragments.AddNewPrescription;
import com.mobtecnica.medirect.docter.fragments.EducationDetailFragment;
import com.mobtecnica.medirect.docter.fragments.EducationFragment;
import com.mobtecnica.medirect.docter.fragments.NewMessageFragment;
import com.mobtecnica.medirect.docter.fragments.NewPatientFragment;
import com.mobtecnica.medirect.docter.fragments.AddMedicineFragment;
import com.mobtecnica.medirect.docter.fragments.AddMedicineFragment.MedicineListDialog;
import com.mobtecnica.medirect.docter.fragments.AddHistoryFragment;
import com.mobtecnica.medirect.docter.fragments.PatientsFragment;
import com.mobtecnica.medirect.docter.fragments.PaymentMethodFragment;
import com.mobtecnica.medirect.docter.fragments.PrescriptionDetailFragmentNew;
import com.mobtecnica.medirect.docter.fragments.PrescriptionListFragment;
import com.mobtecnica.medirect.docter.fragments.ProfileFragment;
import com.mobtecnica.medirect.docter.fragments.PurchaseOrderFragment;
import com.mobtecnica.medirect.docter.fragments.SettingsFragment;
import com.mobtecnica.medirect.docter.fragments.ViewPrescriptionFragment;
import com.mobtecnica.medirect.docter.fragments.WalletsFragment;
import com.mobtecnica.medirect.docter.fragments.ItemsListFragment.OnItemSelectedListener;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerForPaymentMethods;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforAppointments;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforDeliveryAddress;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforFrequencies;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPlaceOrder;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPrescriptionDetailNew;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPromotions;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPurchaseOrder;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforSaveShippingAddressFromProfile;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforUpdateAppointments;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforViewPromotion;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforEditProfile;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllCities;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllCountries;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllStates;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforSearchUsersInbox;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoMedicinsList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoMedicinsUnitList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoRecentMessage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPatient;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPrescription;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAllPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForChangePassword;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForDiagnosticsTests;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForHomeRecentPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForMessageItemList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForMyHistory;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForPatientListHomePage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForSaveHistory;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForSendMessage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForUpdateProfile;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForViewDetailPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForWalletHistory;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PaymentMethodModels;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailViewModelnew;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.MasterDataItem;
import com.mobtecnica.medirect.docter.models.PromotionModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;
import com.mobtecnica.medirect.docter.models.UnitsModel;
import com.mobtecnica.medirect.docter.models.ViewPromotionModel;
import com.mobtecnica.medirect.docter.models.WalletHistory;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.ShowProgresDialog;
import com.mobtecnica.medirect.docter.utils.Utilities;

public class ItemsListActivity extends FragmentActivity implements
		OnItemSelectedListener,
		OnHtttpResponseListenerForHomeRecentPrescriptions,
		OnHtttpResponseListenerForAllPrescriptions,
		OnHtttpResponseListenerForViewDetailPrescriptions,
		OnHtttpResponseListenerForPatientListHomePage,
		OnHtttpResponseListenerFoMedicinsList,
		OnHttpResponseListnerforEditProfile,
		OnHtttpResponseListenerForAddPrescription,
		OnHtttpResponseListenerFoRecentMessage,
		OnHtttpResponseListenerForMessageItemList,
		OnHtttpResponseListenerForSendMessage,
		OnHtttpResponseListenerForChangePassword,
		OnHttpResponseListnerforFetchAllCountries,
		OnHttpResponseListnerforFetchAllStates,
		OnHttpResponseListnerforFetchAllCities,
		OnHtttpResponseListenerForAddPatient,
		OnHtttpResponseListenerForUpdateProfile,
		OnHttpResponseListnerforSearchUsersInbox,
		OnHtttpResponseListenerForWalletHistory,
		OnHttpResponseListenerforDeliveryAddress,
		OnHttpResponseListenerForPaymentMethods,
		OnHttpResponseListenerforPlaceOrder,
		OnHttpResponseListenerforFrequencies,
		OnHttpResponseListenerforAppointments,
		OnHttpResponseListenerforUpdateAppointments,
		OnHttpResponseListenerforPurchaseOrder,
		OnHttpResponseListenerforSaveShippingAddressFromProfile,
		OnHttpResponseListenerforPromotions,
		OnHttpResponseListenerforViewPromotion,
		OnHtttpResponseListenerForSaveHistory,
		OnHtttpResponseListenerForMyHistory,
		OnHtttpResponseListenerForDiagnosticsTests,OnHttpResponseListenerforPrescriptionDetailNew {
	private boolean isTwoPane = false;
	Fragment fragmentItemDetail;

	@Override
	public void onBackPressed() {
		Fragment currentFragment = getSupportFragmentManager()
				.findFragmentById(R.id.flDetailContainer);
		if ((currentFragment instanceof HomeFragment)
				|| (currentFragment instanceof HomeFragment)
				|| (currentFragment instanceof ProfileFragment)
				|| (currentFragment instanceof PatientsFragment)
				|| (currentFragment instanceof AppointMentsFragment)
				|| (currentFragment instanceof PrescriptionListFragment)
				|| (currentFragment instanceof WalletsFragment)
				|| (currentFragment instanceof InboxFragment)
				|| (currentFragment instanceof SettingsFragment)
				|| (currentFragment instanceof EducationFragment)) {
			final AlertDialog alertDialog = new AlertDialog.Builder(this)
					.create();
			alertDialog.setTitle("Exit!");
			alertDialog.setMessage("Do you want to Exit..?");
			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(Intent.ACTION_MAIN);
							intent.addCategory(Intent.CATEGORY_HOME);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
							finish();
						}
					});
			alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.dismiss();
						}
					});
			// Set the Icon for the Dialog
			alertDialog.setIcon(R.drawable.ic_launcher);
			alertDialog.show();

		} else {
			super.onBackPressed();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		makeFullScreen();

		setContentView(R.layout.activity_items);
		determinePaneLayout();

		if (isTwoPane) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				/*
				 * HttpRequestHelper.getHomeRecentPrescription(
				 * ItemsListActivity.this,
				 * getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
				 * Context.MODE_PRIVATE).getString( LoginActivity.PREFS_USERID,
				 * ""));
				 */
				HttpRequesthelperForAppointments.getTodaysAppointments(
						ItemsListActivity.this,
						getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""),
						""
								+ new SimpleDateFormat("yyyy-MM-dd")
										.format(Calendar.getInstance()
												.getTime()), ""
														+ new SimpleDateFormat("yyyy-MM-dd")
												.format(Calendar.getInstance()
														.getTime()),true);
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	public void makeFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void determinePaneLayout() {
		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		if (fragmentItemDetail != null) {
			isTwoPane = true;
			ItemsListFragment fragmentItemsList = (ItemsListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.fragmentItemsList);
			fragmentItemsList.setActivateOnItemClick(true);

			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		return true;
	}

	@Override
	public void onItemSelected(MasterDataItem item) {
		if (isTwoPane) { // single activity with list and detail
			// Replace frame layout with correct detail fragment

			if (item.getTitle().equalsIgnoreCase("Home")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequesthelperForAppointments.getTodaysAppointments(
							ItemsListActivity.this,
							getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""),
							""
									+ new SimpleDateFormat("yyyy-MM-dd")
											.format(Calendar.getInstance()
													.getTime()),""
															+ new SimpleDateFormat("yyyy-MM-dd")
													.format(Calendar.getInstance()
															.getTime()), true);
					/*
					 * HttpRequestHelper.getHomeRecentPrescription(
					 * ItemsListActivity.this, getSharedPreferences(
					 * LoginActivity.PREFS_LOGIN_STATUS,
					 * Context.MODE_PRIVATE).getString(
					 * LoginActivity.PREFS_USERID, ""));
					 */
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}

			} else if (item.getTitle().equalsIgnoreCase("Profile")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequestHelper.getProfile(
							ItemsListActivity.this,
							getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""));
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}

			} /*else if (item.getTitle().equalsIgnoreCase("Patients")) {
				fragmentItemDetail = new PatientsFragment();
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.replace(R.id.flDetailContainer, fragmentItemDetail);
				ft.commit();
			} */else if (item.getTitle().equalsIgnoreCase("Appointments")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {

				
						HttpRequestHelper.getAllAppointments(
								ItemsListActivity.this,
								getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, "")/*,
								""
										+ new SimpleDateFormat("yyyy-MM-dd")
												.format(Calendar.getInstance().getTime())*/,false,"","");
					
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}

			} else if (item.getTitle().equalsIgnoreCase("Patients")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequestHelper.getPrescriptionList(
							ItemsListActivity.this, "", "", "", "", "",false,"0");
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}
			} else if (item.getTitle().equalsIgnoreCase("Wallet")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequestHelper.getWalletHistory(
							ItemsListActivity.this,
							getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""), "", "");
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}
			} else if (item.getTitle().equalsIgnoreCase("Inbox")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequestHelper.getRecent_messages(
							ItemsListActivity.this,
							getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""));
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}
			} else if (item.getTitle().equalsIgnoreCase("Settings")) {
				fragmentItemDetail = new SettingsFragment();
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.replace(R.id.flDetailContainer, fragmentItemDetail);
				ft.commit();
			} else if (item.getTitle().equalsIgnoreCase("Education")) {
				if (Utilities.getInstance(ItemsListActivity.this)
						.isNetAvailable()) {
					HttpRequestHelper.getAllPromotions(
							ItemsListActivity.this,
							getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""));
				} else {
					Toast.makeText(ItemsListActivity.this,
							R.string.error_internet, Toast.LENGTH_SHORT).show();
				}
			}
		} else { // separate activities
			// launch detail activity using intent
			Intent i = new Intent(this, ItemDetailActivity.class);
			i.putExtra("item", item);
			startActivity(i);
		}
	}

	@Override
	public void onHttpSuccessPatientProfile(String response,
			boolean responseStatus, Profile_Model doctProf) {
		if (responseStatus) {
			Bundle bundle = new Bundle();
			bundle.putParcelable("Profile_Model", doctProf);
			fragmentItemDetail = new ProfileFragment();
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"ProfileFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedPatientProfile(Throwable throwable,
			String response, boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulPrescriptions(String response,
			boolean response_status, ArrayList<Profile_Model> patient_profile,Boolean isScroll) {
		if (response_status) {
		/*	Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("Profile_Model", patient_profile);
			fragmentItemDetail = new PrescriptionListFragment();
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"PrescriptionListFragment", this);*/
			if (isScroll) {
				Fragment f = getSupportFragmentManager().findFragmentById(
					    R.id.flDetailContainer);
				if (f instanceof PrescriptionListFragment ) {
					PrescriptionListFragment frg = null;
					frg = (PrescriptionListFragment) getSupportFragmentManager()
							.findFragmentByTag("PrescriptionListFragment");
					frg.loading = false;
					frg.pat_modelList.addAll(patient_profile);
					frg.adapter.notifyDataSetChanged();
				} 
			}else {
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("Profile_Model", patient_profile);
				fragmentItemDetail = new PrescriptionListFragment();
				fragmentItemDetail.setArguments(bundle);
				Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
						"PrescriptionListFragment", this);
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedPrescriptions(Throwable throwable, String response,
			boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulViewDetailPrescriptions(String response,
			boolean response_status,
			ArrayList<PrescriptionDetailModel> prescriptiondetail,
			Profile_Model patModel) {
		if (response_status) {
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("prescriptiondetail",
					prescriptiondetail);
			bundle.putParcelable("patModel", patModel);
			fragmentItemDetail = new ViewPrescriptionFragment();
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"ViewPrescriptionFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedViewDetailPrescriptions(Throwable throwable,
			String response, boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onSuccessResponseListenerForPatientListHomePage(
			String response, boolean response_status,
			ArrayList<Profile_Model> recentPrescriptions) {
		if (response_status) {
			/*
			 * Bundle bundle = new Bundle();
			 * bundle.putParcelableArrayList("recentPrescriptions",
			 * recentPrescriptions); fragmentItemDetail = new HomeFragment();
			 * fragmentItemDetail.setArguments(bundle);
			 * Utilities.getInstance(this).changeCurrentFragment(
			 * fragmentItemDetail, "HomeFragment", this);
			 */

			HomeFragment frg = null;
			frg = (HomeFragment) getSupportFragmentManager().findFragmentByTag(
					"HomeFragment");
			frg.addDataToPatientListView(recentPrescriptions);

		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onFailedResponseListenerForPatientListHomePage(
			Throwable throwable, String response, boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	// @Override
	// public void onHttpSuccessfulMedicinsList(String response,
	// Boolean responseStatus, ArrayList<MedicinsListModel> medicinslist) {
	//
	// }

	@Override
	public void onHttpFailedMedicinsList(String response, Throwable throwable,
			boolean responseStatus) {

		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulMedicinsList(String response,
			Boolean responseStatus, ArrayList<MedicinsListModel> medicinslist) {

		if (responseStatus) {
			Fragment f = getSupportFragmentManager().findFragmentById(
				    R.id.flDetailContainer);
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				if (f instanceof AddHistoryFragment) {
					AddHistoryFragment frg = (AddHistoryFragment) getSupportFragmentManager()
							.findFragmentByTag("AddHistoryFragment");
					frg.showAlergyMedicineDialog(medicinslist);
				}else if (f instanceof AddMedicineFragment) {
					AddMedicineFragment frg = (AddMedicineFragment) getSupportFragmentManager()
							.findFragmentByTag("AddMedicineFragment");
					frg.showMedicineDialog(medicinslist);
				}
				
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Failed to  MedicinList",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpSuccessfulResponseEditProfile(String response,
			boolean responseStatus, String responseResultMsg) {
		if (responseStatus) {
			Toast.makeText(ItemsListActivity.this, "Profile Updated",
					Toast.LENGTH_SHORT).show();
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				/*
				 * HttpRequestHelper.getHomeRecentPrescription(
				 * ItemsListActivity.this,
				 * getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
				 * Context.MODE_PRIVATE).getString( LoginActivity.PREFS_USERID,
				 * ""));
				 */
				HttpRequesthelperForAppointments.getTodaysAppointments(
						ItemsListActivity.this,
						getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""),
						""
								+ new SimpleDateFormat("yyyy-MM-dd")
										.format(Calendar.getInstance()
												.getTime()),""
														+ new SimpleDateFormat("yyyy-MM-dd")
												.format(Calendar.getInstance()
														.getTime()), true);
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, responseResultMsg,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponseEditProfile(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage) {
		Toast.makeText(ItemsListActivity.this, "Failed to  update profile",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onHttpSuccessfulAddPrescription(String response,
			boolean responseStatus, String responseResult, String patid,
			Profile_Model pat_prof) {
		if (responseStatus) {
			Toast.makeText(ItemsListActivity.this, "Prescription Added",
					Toast.LENGTH_SHORT).show();
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				startActivity(new Intent(ItemsListActivity.this,
						ItemsListActivity.class));
				finish();
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, responseResult,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedAddPrescription(String response,
			Throwable throwable, boolean responseStatus, ProgressDialog progress) {
		ShowProgresDialog.dismissProress(progress);
		Toast.makeText(ItemsListActivity.this, "Failed to  add prescription",
				Toast.LENGTH_SHORT).show();

	}

	// @Override
	// public void onHttpFailedMedicinsUnitList(String response,
	// Throwable throwable, boolean responseStatus) {
	// Toast.makeText(ItemsListActivity.this,
	// "Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
	// .show();
	//
	// }
	//
	// @Override
	// public void onHttpSuccessfulMedicinsUnitList(String response,
	// Boolean responseStatus, ArrayList<UnitsModel> unitsModelList,
	// Profile_Model profile,
	// ArrayList<MedicinsListModel> MedicinsModelList) {
	// if (responseStatus) {
	//
	// NewPreScriptionFragment frg = null;
	// frg = (NewPreScriptionFragment) getSupportFragmentManager()
	// .findFragmentByTag("NewPreScriptionFragment");
	// // frg.allunits = unitsModelList;
	// // Spinner rootLayout = (Spinner) frg.getView().findViewById(
	// // R.id.spnDose);
	// ArrayList<String> strUnitList = new ArrayList<String>();
	// for (int i = 0; i < unitsModelList.size(); i++) {
	// strUnitList.add(unitsModelList.get(i).getName());
	// }
	// ArrayAdapter<String> quantityAdapter = new ArrayAdapter<String>(
	// getApplicationContext(), R.layout.spinner_item, strUnitList);
	// quantityAdapter.setDropDownViewResource(R.layout.spinner_item);
	// rootLayout.setAdapter(quantityAdapter);
	//
	// } else {
	// Toast.makeText(ItemsListActivity.this, "Request Failed",
	// Toast.LENGTH_SHORT).show();
	// }
	//
	// }

	@Override
	public void onHttpSuccessfulRecentMessage(String response,
			Boolean responseStatus,
			ArrayList<RecentMessageModel> recentMessageModellist) {
		if (responseStatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Bundle bundle = new Bundle();
				fragmentItemDetail = new InboxFragment();
				bundle.putParcelableArrayList("recentMessageModellist",
						recentMessageModellist);
				fragmentItemDetail.setArguments(bundle);

				Utilities.getInstance(this).changeChildFragment(
						fragmentItemDetail, "InboxFragment", this);
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Loading failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedRecentMessage(String response, Throwable throwable,
			boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulMessageItemList(String response,
			Boolean responseStatus,
			ArrayList<RecentMessageModel> RecentMessageItemModelList,
			RecentMessageModel profile, int clickedpos) {
		if (responseStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Bundle bundle = new Bundle();
				fragmentItemDetail = new InboxViewMessage();
				bundle.putParcelableArrayList("RecentMessageItemModelList",
						RecentMessageItemModelList);
				bundle.putParcelable("RecentMessageModelProf", profile);
				fragmentItemDetail.setArguments(bundle);
				Utilities.getInstance(this).changeFragmentWithoutAddingBack(
						fragmentItemDetail, "InboxViewMessage", this);
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					" Sorry,Please Try again later ! ", Toast.LENGTH_SHORT)
					.show();
		}

	}

	@Override
	public void onHttpFailedMessageItemList(String response,
			Throwable throwable, boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulSendMessage(String response,
			boolean responsestatus, String responseResult, String reply_msg) {
		if (responsestatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Toast.makeText(ItemsListActivity.this,
						"Message Sent Successfully", Toast.LENGTH_SHORT).show();

				if (responsestatus) {

					InboxViewMessage frg = null;
					frg = (InboxViewMessage) getSupportFragmentManager()
							.findFragmentByTag("InboxViewMessage");
					LinearLayout doctor_reply = new LinearLayout(
							ItemsListActivity.this);
					LinearLayout rootLayout = (LinearLayout) frg.getView()
							.findViewById(R.id.messageListId);
					LinearLayout contentBasease = null;
					contentBasease = new LinearLayout(ItemsListActivity.this);
					LayoutParams layoutparms = new LayoutParams(
							WindowManager.LayoutParams.MATCH_PARENT,
							WindowManager.LayoutParams.WRAP_CONTENT);

					contentBasease.setOrientation(LinearLayout.VERTICAL);

					contentBasease.setLayoutParams(layoutparms);
					doctor_reply = new LinearLayout(ItemsListActivity.this);
					doctor_reply.setBackgroundDrawable(getResources()
							.getDrawable(R.color.from_col));
					doctor_reply.setPadding(10, 10, 5, 5);

					doctor_reply.setOrientation(LinearLayout.VERTICAL);
					// Defining the RelativeLayout layout parameters.
					// In this case I want to fill its parent
					LinearLayout.LayoutParams docc_params = new LinearLayout.LayoutParams(
							WindowManager.LayoutParams.WRAP_CONTENT,
							WindowManager.LayoutParams.WRAP_CONTENT);
					// dentist_params.addRule(RelativeLayout.BELOW,
					// dentist_comment[i].getId());
					docc_params.gravity = Gravity.RIGHT;
					docc_params.setMargins(100, 10, 5, 10);
					doctor_reply.setLayoutParams(docc_params);

					// Creating a new TextView

					TextView message = new TextView(ItemsListActivity.this);

					message.setText(reply_msg);
					message.setPadding(5, 5, 5, 5);
					// Defining the layout parameters of the TextView
					LinearLayout.LayoutParams doc_right = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);

					doc_right.setMargins(25, 0, 15, 5);
					// Setting the parameters on the TextView
					message.setLayoutParams(doc_right);
					doc_right.gravity = Gravity.RIGHT;
					doctor_reply.addView(message);

					View view = new View(ItemsListActivity.this);
					LinearLayout.LayoutParams viewPar = new LinearLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT, 1);
					viewPar.gravity = Gravity.RIGHT;
					viewPar.setMargins(5, 0, 5, 5);
					doctor_reply.addView(view);
					view.setLayoutParams(viewPar);
					view.setBackgroundColor(getResources().getColor(
							R.color.message_devider));
					TextView textDateTime = new TextView(ItemsListActivity.this);

					textDateTime.setLayoutParams(doc_right);
					doctor_reply.addView(textDateTime);

					int mYear = Calendar.getInstance().get(Calendar.YEAR);
					int mMonth = Calendar.getInstance().get(Calendar.MONTH);
					int mDay = Calendar.getInstance()
							.get(Calendar.DAY_OF_MONTH);
					int mHour = Calendar.getInstance().get(Calendar.HOUR);
					int mMinute = Calendar.getInstance().get(Calendar.MINUTE);
					int mSec = Calendar.getInstance().get(Calendar.SECOND);
					textDateTime.setText("" + mYear + "-" + mMonth + "-" + mDay
							+ " " + mHour + "-" + mMinute + "-" + mSec);
					// Adding the TextView to the RelativeLayout as a child

					contentBasease.addView(doctor_reply);

					rootLayout.addView(contentBasease);

				} else {
					Toast.makeText(
							getApplicationContext(),
							responseResult + " Sorry,Please Try again later ! ",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, responseResult,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedSendMessage(String response, Throwable throwable,
			boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulChangePassword(String response,
			boolean responsestatus, String responseResult, Profile_Model docter) {
		if (responsestatus) {
			Bundle bundle = new Bundle();
			bundle.putParcelable("Profile_Model", docter);
			fragmentItemDetail = new ProfileFragment();
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeCurrentFragment(
					fragmentItemDetail, "ProfileFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, responseResult,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedChangePassword(String response,
			Throwable throwable, boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpFailedResponseFetchAllCountries(Throwable throwable,
			String response, boolean resposeStatus) {
		// TODO Auto-generated method stub
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseFetchAllCities(String response,
			boolean responseStatus,
			ArrayList<GetAllCitiesModel> rescentcityList, Boolean showDialog) {
		if (responseStatus) {
			Fragment currentFragment = getSupportFragmentManager()
					.findFragmentById(R.id.flDetailContainer);
			if (currentFragment instanceof EditProfileFragment) {

				EditProfileFragment frg = null;
				frg = (EditProfileFragment) getSupportFragmentManager()
						.findFragmentByTag("EditProfileFragment");
				frg.allCitiesList = rescentcityList;
				EditText rootLayout = (EditText) frg.getView().findViewById(
						R.id.editTextcity);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);
				if (showDialog) {
					frg.showCityDialog(rescentcityList);
				}

			} else if (currentFragment instanceof DeliveryAddressFragment) {
				DeliveryAddressFragment frg = null;
				frg = (DeliveryAddressFragment) getSupportFragmentManager()
						.findFragmentByTag("DeliveryAddressFragment");
				frg.allCitiesList = rescentcityList;
				TextView rootLayout = (TextView) frg.getView().findViewById(
						R.id.txtCity);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);
			} else {
				NewPatientFragment frg = null;
				frg = (NewPatientFragment) getSupportFragmentManager()
						.findFragmentByTag("NewPatientFragment");
				frg.allCitiesList = rescentcityList;
				EditText rootLayout = (EditText) frg.getView().findViewById(
						R.id.spnCity);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);

			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedResponseFetchAllCities(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseFetchAllCountriesForEditProfile(
			String response, boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList,
			Profile_Model doctor) {
		// TODO Auto-generated method stub
		if (responseStatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Bundle bundle = new Bundle();
				fragmentItemDetail = new EditProfileFragment();
				bundle.putParcelableArrayList("allcountries",
						rescentCountryList);
				bundle.putParcelable("DoctorDetails", doctor);
				fragmentItemDetail.setArguments(bundle);

				Utilities.getInstance(this).changeChildFragment(
						fragmentItemDetail, "EditProfileFragment", this);

			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Countries added",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpSuccessfulResponseFetchAllStates(String response,
			boolean responseStatus,
			ArrayList<GetAllStateModel> rescentStatesList, boolean showDialog) {
		if (responseStatus) {

			Fragment currentFragment = getSupportFragmentManager()
					.findFragmentById(R.id.flDetailContainer);
			if (currentFragment instanceof EditProfileFragment) {

				EditProfileFragment frg = null;
				frg = (EditProfileFragment) getSupportFragmentManager()
						.findFragmentByTag("EditProfileFragment");
				frg.allStatesList = rescentStatesList;
				EditText rootLayout = (EditText) frg.getView().findViewById(
						R.id.editTextstate);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);
				if (showDialog) {
					frg.showStateDialog(rescentStatesList);
				}
			} else if (currentFragment instanceof DeliveryAddressFragment) {
				DeliveryAddressFragment frg = null;
				frg = (DeliveryAddressFragment) getSupportFragmentManager()
						.findFragmentByTag("DeliveryAddressFragment");
				frg.allStatesList = rescentStatesList;
				TextView rootLayout = (TextView) frg.getView().findViewById(
						R.id.txtState);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);
			} else {
				NewPatientFragment frg = null;
				frg = (NewPatientFragment) getSupportFragmentManager()
						.findFragmentByTag("NewPatientFragment");
				frg.allStatesList = rescentStatesList;
				EditText rootLayout = (EditText) frg.getView().findViewById(
						R.id.spnState);
				rootLayout.setEnabled(true);
				rootLayout.setClickable(true);
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedResponseFetchAllStates(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseFetchAllCountries(String response,
			boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList) {
		// TODO Auto-generated method stub
		if (responseStatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Fragment currentFragment = getSupportFragmentManager()
						.findFragmentById(R.id.flDetailContainer);
				if (currentFragment instanceof ProfileFragment) {

					Bundle bundle = new Bundle();
					fragmentItemDetail = new EditProfileFragment();
					bundle.putParcelableArrayList("allcountries",
							rescentCountryList);
					fragmentItemDetail.setArguments(bundle);
					Utilities.getInstance(this).changeChildFragment(
							fragmentItemDetail, "EditProfileFragment", this);

				} else if (currentFragment instanceof DeliveryAddressFragment) {
					DeliveryAddressFragment frg = null;
					frg = (DeliveryAddressFragment) getSupportFragmentManager()
							.findFragmentByTag("DeliveryAddressFragment");
					frg.allCountriesList = rescentCountryList;
					frg.showCountryDialog();
				} else {

					Bundle bundle = new Bundle();
					fragmentItemDetail = new NewPatientFragment();
					bundle.putParcelableArrayList("allcountries",
							rescentCountryList);
					fragmentItemDetail.setArguments(bundle);
					Utilities.getInstance(this).changeChildFragment(
							fragmentItemDetail, "NewPatientFragment", this);
				}
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Countries added",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpSuccessfulAddPatient(String response,
			boolean responseStatus, String responseResult) {
		if (responseStatus) {
			Toast.makeText(ItemsListActivity.this, "Patient Added",
					Toast.LENGTH_SHORT).show();
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				startActivity(new Intent(ItemsListActivity.this,
						ItemsListActivity.class));
				finish();
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, responseResult,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedAddPatient(String response, Throwable throwable,
			boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulUpdateProfile(String response,
			boolean responseStatus, String responseResult) {
		if (responseStatus) {
			Toast.makeText(ItemsListActivity.this, "Profile Updated",
					Toast.LENGTH_SHORT).show();
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				HttpRequestHelper.getProfile(
						ItemsListActivity.this,
						getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""));
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, response, Toast.LENGTH_SHORT)
					.show();
		}

	}

	@Override
	public void onHttpFailedAddUpdateProfile(String response,
			Throwable throwable, boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulResponseSearchUsersInbox(String response,
			boolean responseStatus, ArrayList<Profile_Model> profile_model_list) {

		if (responseStatus) {

			NewMessageFragment frg = null;
			frg = (NewMessageFragment) getSupportFragmentManager()
					.findFragmentByTag("NewMessageFragment");
			frg.prof_serch_resultlist = profile_model_list;
			ListView expandableListViewSearchUsersList = (ListView) frg
					.getView().findViewById(
							R.id.expandableListViewSearchUsersList);
			Inbox_Users_ListAdapter inboxAdapter = new Inbox_Users_ListAdapter(
					ItemsListActivity.this, profile_model_list);
			expandableListViewSearchUsersList.setAdapter(inboxAdapter);
			frg.linearList.setVisibility(View.VISIBLE);
			frg.linearNodataView.setVisibility(View.GONE);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedResponseSearchUsersInbox(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulSendNewMessage(String response,
			boolean responsestatus, String responseResult, String reply_message) {
		if (responsestatus) {
			Toast.makeText(ItemsListActivity.this, "Message Sent Successfully",
					Toast.LENGTH_SHORT).show();

		} else {
			Toast.makeText(ItemsListActivity.this, responseResult,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpSuccessfulWalletHistory(String response,
			boolean responseStatus, String final_balance,
			ArrayList<WalletHistory> walletHistory_list) {
		if (responseStatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				Bundle bundle = new Bundle();
				fragmentItemDetail = new WalletsFragment();
				bundle.putParcelableArrayList("walletHistory_list",
						walletHistory_list);
				bundle.putString("final_balance", final_balance);
				fragmentItemDetail.setArguments(bundle);

				Utilities.getInstance(this).changeChildFragment(
						fragmentItemDetail, "WalletsFragment", this);
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Loading failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedWalletHistory(String response, Throwable throwable,
			boolean responseStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpSuccessfulAddPrescriptionreturnsId(String response,
			boolean responseStatus, String responseResult, String pat_id,
			Profile_Model pat_prof, String pres_id, ProgressDialog progress) {
		if (responseStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				LoadPurchaseOrderList purchaseList = new LoadPurchaseOrderList(
						ItemsListActivity.this, progress);
				try {

					purchaseList.execute(pres_id);
					/* ArrayList<PurchaseOrderModel> data */
					/*
					 * HashMap<PurchaseOrderPatientDetails,
					 * ArrayList<PurchaseOrderModel>> data = purchaseList
					 * .execute(pres_id).get(); Config.LogError("purchase List",
					 * data.toString());
					 * 
					 * fragmentItemDetail = PurchaseOrderFragment
					 * .PurchaseOrderFragmentNewInstance(data.values(),
					 * pres_id);
					 * Utilities.getInstance(this).changeCurrentFragment(
					 * fragmentItemDetail, "PurchaseOrderFragment", this);
					 */
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*
				 * bundle.putString("prescriptionId", pres_id);
				 * fragmentItemDetail = new DeliveryAddressFragment();
				 * fragmentItemDetail.setArguments(bundle);
				 * Utilities.getInstance(this).changeCurrentFragment(
				 * fragmentItemDetail, "DeliveryAddressFragment", this);
				 */
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this,
					"Failed to add prescription", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpSuccessfulResponseFetchAllCountriesForDeliveryAddress(
			String response, boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList,
			String prescription_id) {
		if (responseStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	public void onHttpSuccessfulResponseDeliveryAddress(String response,
			boolean responseStatus, String responseResultMsg,
			String prescriptionId, String addressId) {
		if (responseStatus) {
			if (responseStatus) {

			}
		}

	}

	@Override
	public void onHttpFailedResponseDeliveryAddress(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponsePaymentMethod(String response,
			boolean responseStatus,
			HashMap<String, String> prescriptionAddressPaymentId,
			ArrayList<PaymentMethodModels> paymentMethodLists) {
		// TODO Auto-generated method stub

		if (responseStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				PaymentMethodFragment frg = null;
				frg = (PaymentMethodFragment) getSupportFragmentManager()
						.findFragmentByTag("PaymentMethodFragment");
				frg.datalist = paymentMethodLists;
				frg.loadDataToListView();
			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	public void onHttpFailedResponsePaymentMethod(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpSuccessfulResponseDeliveryAddressData(String addressId,
			String PrescriptionId) {
		if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
			Bundle bundle = new Bundle();
			bundle.putString("prescriptionId", PrescriptionId);
			bundle.putString("addressId", addressId);
			fragmentItemDetail = new PaymentMethodFragment();
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"PaymentMethodFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, R.string.error_internet,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpSuccessfulResponsePlaceOrder(String response,
			boolean responseStatus, String responseResultMsg,
			String purchaseOrderId, String transactionId) {
		if (responseStatus) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					responseResultMsg + "\n" + "Transaction id: "
							+ transactionId)
					.setCancelable(false)
					.setTitle("Order Status")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									if (Utilities.getInstance(
											ItemsListActivity.this)
											.isNetAvailable()) {
										startActivity(new Intent(
												ItemsListActivity.this,
												ItemsListActivity.class));
										finish();
									} else {
										Toast.makeText(ItemsListActivity.this,
												R.string.error_internet,
												Toast.LENGTH_SHORT).show();
									}
								}
							});
			AlertDialog alert = builder.create();
			alert.show();

		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage("Failed to Place Order")
					.setTitle("Order Status")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});

		}
	}

	@Override
	public void onHttpFailedResponsePlaceOrder(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage) {
		// TODO Auto-generated method stub
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseFrequencies(String response,
			boolean responseStatus, ArrayList<FrequencyModel> frList,
			Profile_Model profile,MyHistoryModel myHistory,ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,ArrayList<MedicinsModel> addedMedicinesList) {
		// TODO Auto-generated method stub
		if (responseStatus) {

			Fragment fragmentItemDetail = AddMedicineFragment
					.newInstanceOfAddMedicineFragment(profile, myHistory, frList,diagNosticModel,addedMedicinesList);
			Utilities.getInstance(ItemsListActivity.this)
					.changeChildFragment(
							fragmentItemDetail,
							"AddMedicineFragment",
							ItemsListActivity.this);
			
			/*fragmentItemDetail = new AddNewPrescription();

			Bundle bundle = new Bundle();
			// bundle.putParcelableArrayList("allmedicins", medicinslist);
			bundle.putParcelable("pat_profile", profile);

			bundle.putParcelableArrayList("Frequencies", frList);
			bundle.putParcelableArrayList("prescriptionMedicineList", null);
			fragmentItemDetail.setArguments(bundle);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"AddNewPrescription_Medicine_Diagnosis_Fragment", this);*/
		} else {
			Toast.makeText(ItemsListActivity.this, "Failed to add frequencies",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponsefrequencies(Throwable throwable,
			String response, boolean resposeStatus) {
		// TODO Auto-generated method stub
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseAppointments(String response,
			boolean responseStatus, ArrayList<Appointments> appointmentslist,
		/*	String Date,*/ Boolean isHomePage) {

		if (responseStatus) {
			if (isHomePage) {
				if (responseStatus) {

					fragmentItemDetail = HomeFragment
							.newInstanceOfHomeFragment(appointmentslist);
					Utilities.getInstance(this).changeCurrentFragment(
							fragmentItemDetail, "HomeFragment", this);

				} else {
					Toast.makeText(ItemsListActivity.this, "Request Failed",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Fragment f = getSupportFragmentManager().findFragmentById(
					    R.id.flDetailContainer);
				if (f instanceof AppointMentsFragment) {
					AppointMentsFragment frg = null;
					frg = (AppointMentsFragment) getSupportFragmentManager()
							.findFragmentByTag("AppointMentsFragment");
					frg.appointments_list = appointmentslist;
					
					frg.addDataToListView();
				}else {
					fragmentItemDetail = AppointMentsFragment.newInstanceOFAppointMentsFragment(appointmentslist);
					Utilities.getInstance(this).changeCurrentFragment(
							fragmentItemDetail, "AppointMentsFragment", this);
					
				}
			
			}

		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponseAppointments(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onHttpFailedResponseUpdateAppointments(Throwable throwable,
			String response, boolean resposeStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpSuccessfulResponseUpdateAppointments(String date,
			int possition, boolean resposeStatus, String appoinmentStatusId,
			String appointmentStatusMessage, String message) {
		if (resposeStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				AppointMentsFragment frg = null;
				frg = (AppointMentsFragment) getSupportFragmentManager()
						.findFragmentByTag("AppointMentsFragment");
				frg.appointments_list.get(possition).setAppointment_status_id(
						appoinmentStatusId);
				frg.appointments_list.get(possition).setAppointment_status(
						appointmentStatusMessage);
				frg.appoAdapter.notifyDataSetChanged();

				Toast.makeText(ItemsListActivity.this, message,
						Toast.LENGTH_SHORT).show();

				/*
				 * fragmentItemDetail = new AppointMentsFragment();
				 * Utilities.getInstance
				 * (this).changeChildFragment(fragmentItemDetail,
				 * "AppointMentsFragment", this);
				 */

			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpSuccessfulResponseUpdateAppointmentsClose() {
		fragmentItemDetail = new AppointMentsFragment();
		Utilities.getInstance(this).changeCurrentFragment(fragmentItemDetail,
				"AppointMentsFragment", this);
	}

	@Override
	public void onHttpSuccessfulResponsePurchaseOrder(String response,
			boolean responseStatus, String PrescriptionId,
			ArrayList<PurchaseOrderModel> purchaseOrderModel,
			PurchaseOrderPatientDetails purchasePAtientDetails) {

		if (responseStatus) {
			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {
				fragmentItemDetail = PurchaseOrderFragment
						.PurchaseOrderFragmentNewInstance(purchaseOrderModel,
								PrescriptionId, purchasePAtientDetails);
				Utilities.getInstance(this).changeChildFragment(
						fragmentItemDetail, "PurchaseOrderFragment", this);

				/*
				 * fragmentItemDetail = new AppointMentsFragment();
				 * Utilities.getInstance
				 * (this).changeChildFragment(fragmentItemDetail,
				 * "AppointMentsFragment", this);
				 */

			} else {
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponsePurchaseOrder(Throwable throwable,
			String response, boolean resposeStatus) {
		// TODO Auto-generated method stub
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseShippingAddressFromProfile(
			String response, boolean responseStatus, String PrescriptionId,
			ArrayList<PurchaseOrderModel> purchaseOrderModel,
			String shippingId, ProgressDialog progress) {
		if (responseStatus) {

			if (Utilities.getInstance(ItemsListActivity.this).isNetAvailable()) {

				LoadPaymentMethodsAsyc paymentMethods = new LoadPaymentMethodsAsyc(
						ItemsListActivity.this);
				try {
					ArrayList<PaymentMethodModels> paymentMethodList = paymentMethods
							.execute().get();
					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("purchaseOrder",
							purchaseOrderModel);
					bundle.putString("prescriptionId", PrescriptionId);
					bundle.putString("addressId", shippingId);
					bundle.putParcelableArrayList("paymentMethods",
							paymentMethodList);
					fragmentItemDetail = new PaymentMethodFragment();
					fragmentItemDetail.setArguments(bundle);
					Utilities.getInstance(this).changeChildFragment(
							fragmentItemDetail, "PaymentMethodFragment", this);
					ShowProgresDialog.dismissProress(progress);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					ShowProgresDialog.dismissProress(progress);
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					ShowProgresDialog.dismissProress(progress);
					e.printStackTrace();
				}

			} else {
				ShowProgresDialog.dismissProress(progress);
				Toast.makeText(ItemsListActivity.this, R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			ShowProgresDialog.dismissProress(progress);
			Toast.makeText(ItemsListActivity.this, R.string.error_internet,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponseShippingAddressFromProfile(
			Throwable throwable, String response, boolean resposeStatus,
			ProgressDialog progress) {
		// TODO Auto-generated method stub
		ShowProgresDialog.dismissProress(progress);
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponsePromotions(String response,
			boolean responseStatus, ArrayList<PromotionModel> promotionsList) {
		if (responseStatus) {
			fragmentItemDetail = EducationFragment
					.newInstanceEducationFragment(promotionsList);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"EducationFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedResponsePromotions(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulResponseViewPromotions(String response,
			boolean responseStatus, ViewPromotionModel promotion) {
		if (responseStatus) {
			fragmentItemDetail = EducationDetailFragment
					.newInstanceEducationFragment(promotion);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"EducationDetailFragment", this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedResponseViewPromotions(Throwable throwable,
			String response, boolean resposeStatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulSaveHistory(String response,
			boolean responsestatus, String responseMessage,
			MyHistoryModel myHistory, Profile_Model profile,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList) {
		if (responsestatus) {
			Toast.makeText(ItemsListActivity.this, "History Saved!",
					Toast.LENGTH_SHORT).show();
			Fragment frg = AddNewPrescription.newInstanceOfAddNewPrescription(
					profile, myHistory,diagNosticModel,addedMedicinesList);
			Utilities.getInstance(ItemsListActivity.this).changeChildFragment(
					frg, "AddNewPrescription", ItemsListActivity.this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedSaveHistory(String response, Throwable throwable,
			boolean responsestatus, String responseMessage) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulMyHistory(String response,
			boolean responsestatus, MyHistoryModel myHistory,
			Profile_Model profile) {
		if (responsestatus) {
			Fragment frg = AddNewPrescription.newInstanceOfAddNewPrescription(
					profile, myHistory,null,null);
			Utilities.getInstance(ItemsListActivity.this).changeChildFragment(
					frg, "AddNewPrescription", ItemsListActivity.this);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onHttpFailedMyHistory(String response, Throwable throwable,
			boolean responsestatus) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onHttpSuccessfulDiagnosticsTests(String response,
			boolean responseStatus,
			ArrayList<DiagnosticsTestModel> diagnosticsTypeLis) {
		if (responseStatus) {
			AddDiagnosisFragment frg = (AddDiagnosisFragment) getSupportFragmentManager()
					.findFragmentByTag("AddDiagnosisFragment");
			frg.showDiagnosticsDialog(diagnosticsTypeLis);
		} else {
			Toast.makeText(ItemsListActivity.this, "Request Failed",
					Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onHttpSuccessfulResponsePrescriptionDetailNew(String response,
			boolean responseStatus, String responseResultMsg,
			ArrayList<PrescriptionDetailViewModelnew> prescriptionDetailViewList,Profile_Model profile, MyHistoryModel myHistory,Boolean isScroll) {
		if (responseStatus) {
/*
			PrescriptionDetailFragmentNew fragmentItemDetail = PrescriptionDetailFragmentNew
					.newInstanceOfPrescriptionDetailFragmentNew(prescriptionDetailViewList,profile,myHistory);
			Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
					"PrescriptionDetailFragmentNew", this);
*/
			 if (responseStatus) {
				   if (!isScroll) {
				   PrescriptionDetailFragmentNew fragmentItemDetail = PrescriptionDetailFragmentNew
				     .newInstanceOfPrescriptionDetailFragmentNew(prescriptionDetailViewList,profile,myHistory);
				   Utilities.getInstance(this).changeChildFragment(fragmentItemDetail,
				     "PrescriptionDetailFragmentNew", this);

				   }
				   else
				   {
				    PrescriptionDetailFragmentNew frg = null;
				    frg = (PrescriptionDetailFragmentNew) getSupportFragmentManager()
				      .findFragmentByTag("PrescriptionDetailFragmentNew");
				 /*   PrescriptionDetailFragmentNew fragmentItemDetail = PrescriptionDetailFragmentNew
				      .newInstanceOfPrescriptionDetailFragmentNew(prescriptionDetailViewList,profile,myHistory);*/
				    if (frg.presDetail != null) {
				     frg.presDetail.addAll(prescriptionDetailViewList);
				     frg.listadapter.notifyDataSetChanged();
				     frg.loadingMore = false;
				    } 
				   }

				  }
		} else {
			Toast.makeText(ItemsListActivity.this,
					"Request Failed, Please Try again later !",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onHttpFailedResponsePrescriptionDetailNew(Throwable throwable,
			boolean resposeStatus, String responseResultMessage) {
		Toast.makeText(ItemsListActivity.this,
				"Request Failed, Please Try again later !", Toast.LENGTH_SHORT)
				.show();

	}

}
