package com.mobtecnica.medirect.docter.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.xml.sax.DTDHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.R.string;
import com.mobtecnica.medirect.docter.fragments.ViewPrescriptionFragment;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerForPaymentMethods;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforAppointments;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforDeliveryAddress;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforFrequencies;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPlaceOrder;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPromotions;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforSaveShippingAddressFromProfile;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforUpdateAppointments;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforViewPromotion;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforEditProfile;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllCities;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllCountries;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforFetchAllStates;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforSearchAllUsers;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListnerforSearchUsersInbox;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoMedicinsList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoMedicinsUnitList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerFoRecentMessage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPatient;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPrescription;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAllPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForChangePassword;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForLogin;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForMessageItemList;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForPatientListHomePage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForHomeRecentPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForSendMessage;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForViewDetailPrescriptions;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForWalletHistory;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.DeliveryAddressModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PatientAddModel;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;

/**
 * @author MOBTECNICA DEV #114
 *
 */
public class HttpRequestHelper {
	private static String addressId = "";
	final static int DEFAULT_TIMEOUT = 20 * 1000;
/*	//public static final String URL_NAME = "http://119.18.54.23/~mobtest";
	 public static final String URL_NAME = "http://192.168.2.148";
	public static final String BASE_URL = URL_NAME + "/medirect/web/index.php";
	// public static final String BASE_IMAGE_URL = URL_NAME
	// + "/medirect/profile_pics/";
*//*	public static final String URL_NAME = "http://qa.mobtecnica.com";*/
	 public static final String URL_NAME = "http://192.168.2.124";
	 public static final String BASE_URL = URL_NAME + "/medirect/web/index.php";

	 

	/* public static final String BASE_URL = URL_NAME + "/medirect-app/v2/web/index.php";
	
	public static final String BASE_URL_CREATE_PATIENT = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/create-patient";
	public static final String BASE_URL_EDIT_PROFILE = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/update-profile";
	public static final String BASE_URL_PAYMENT_METHODS = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/payment-methods";
	public static final String BASE_URL_MEDICINE_FREQUENCIES = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/medicine-frequencies";
	public static final String BASE_URL_PURCHASE_ORDER_ITEMS = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/view-purchase-order-items";
	public static final String BASE_URL_GET_DIAGNOSTICS_TYPES = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/get-diagnostic-types";
	public static final String BASE_URL_GET_MY_HISTORY = URL_NAME
			+ "/medirect-app/v2/web/index.php?r=api/get-history";*/
	
	public static final String BASE_URL_CREATE_PATIENT = URL_NAME
			+ "/medirect/web/index.php?r=api/create-patient";
	public static final String BASE_URL_EDIT_PROFILE = URL_NAME
			+ "/medirect/web/index.php?r=api/update-profile";
	public static final String BASE_URL_PAYMENT_METHODS = URL_NAME
			+ "/medirect/web/index.php?r=api/payment-methods";
	public static final String BASE_URL_MEDICINE_FREQUENCIES = URL_NAME
			+ "/medirect/web/index.php?r=api/medicine-frequencies";
	public static final String BASE_URL_PURCHASE_ORDER_ITEMS = URL_NAME
			+ "/medirect/web/index.php?r=api/view-purchase-order-items";
	public static final String BASE_URL_GET_DIAGNOSTICS_TYPES = URL_NAME
			+ "/medirect/web/index.php?r=api/get-diagnostic-types";
	public static final String BASE_URL_GET_MY_HISTORY = URL_NAME
			+ "/medirect/web/index.php?r=api/get-history";
	
	
	public static AsyncHttpClient client = new AsyncHttpClient();
	public static String TAG = "HtttpRequestHelper";

	public static void post(Context context, RequestParams params,
			AsyncHttpResponseHandler responseHandler, String urlparams) {
		client.setTimeout(DEFAULT_TIMEOUT);

		try {
			System.out.println("Contents of params are :- "
					+ params.getEntity().getContent().toString());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.post(context, BASE_URL + "?r=" + urlparams, params,
				responseHandler);
	}

	/**
	 * Login
	 * 
	 * @param activity
	 * @param Username
	 * @param password
	 * @param type
	 */
	public static void LoginUser(final Activity activity, String Username,
			String password, String type) {
		Config.LogError(TAG, "@LoginUpUser");
		/* Log.e(TAG, "@LoginUpUser"); */
		RequestParams params = new RequestParams();

		params.put("email", Username);
		params.put("password", password);
		params.put("user_type_id", "6");
		Config.LogError("Login Creditionals ", Username + " " + password);
		/* Log.e("Login Creditionals ", Username + " " + password); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Log.e(TAG + "@Login", "FAILED  >" + response);
				Config.LogError(TAG + "@Login", "FAILED  >" + response);
				OnHtttpResponseListenerForLogin login = (OnHtttpResponseListenerForLogin) activity;
				login.onHttpFailedResponseLogin(throwable, response, false, "");
			}

			@Override
			public void onSuccess(String response) {

				/* super.onSuccess(response); */
				Config.LogError(TAG + "@signUpUser", "SUCCESS  >" + response);
				/* Log.e(TAG + "@signUpUser", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForLogin login = (OnHtttpResponseListenerForLogin) activity;
				login.onHttpSuccessfulResponseLogin(response, JsonParser
						.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parseloginResponse(response));
			}

			/*
			 * public void onFailure(int arg0, Header[] arg1, byte[] arg2,
			 * Throwable arg3) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * @Override public void onSuccess(int arg0, Header[] arg1, byte[]
			 * arg2) { // TODO Auto-generated method stub
			 * 
			 * }
			 */

		};
		post(activity, params, handler, "api/login");
	}

	/**
	 * 
	 * @param activity
	 * @param id
	 */
	public static void getProfile(final Activity activity, String id) {
		Config.LogError(TAG, "@getProfile");
		/* Log.e(TAG, "@getProfile"); */
		RequestParams params = new RequestParams();
		params.put("id", id);

		/* Log.e("Profile Details", "id: " + id); */
		Config.LogError("Profile Details", "id: " + id);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				/* Log.e(TAG + "@api/view-profile", "FAILED  >" + response); */
				Config.LogError(TAG + "@api/view-profile", "FAILED  >"
						+ response);
				OnHtttpResponseListenerForHomeRecentPrescriptions login = (OnHtttpResponseListenerForHomeRecentPrescriptions) activity;
				login.onHttpFailedPatientProfile(throwable, response,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/view-profile", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/view-profile", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForHomeRecentPrescriptions login = (OnHtttpResponseListenerForHomeRecentPrescriptions) activity;
				login.onHttpSuccessPatientProfile(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetprofileResponse(
								response));
			}

		};
		post(activity, params, handler, "api/view-profile");
	}

	/**
	 * get Prescription List of all patients
	 * 
	 * @param activity
	 * @param account_no
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param phone
	 */
	public static void getPrescriptionList(final Activity activity,
			String account_no, String first_name, String last_name,
			String email, String phone,final Boolean isScroll,String pageNo) {
		Config.LogError(TAG, "@getPrescriptionList");
		/* Log.e(TAG, "@getPrescriptionList"); */
		RequestParams params = new RequestParams();
		params.put("account_no", account_no);
		params.put("first_name", first_name);
		params.put("last_name", last_name);
		params.put("email", email);
		params.put("phone", phone);
		params.put("page", pageNo);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/patients", "FAILED  >" + response);
				/* Log.e(TAG + "@api/patients", "FAILED  >" + response); */
				OnHtttpResponseListenerForAllPrescriptions login = (OnHtttpResponseListenerForAllPrescriptions) activity;
				login.onHttpFailedPrescriptions(throwable, response, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/patients", "SUCCESS  >" + response);
				/* Log.e(TAG + "@api/patients", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForAllPrescriptions login = (OnHtttpResponseListenerForAllPrescriptions) activity;

				login.onHttpSuccessfulPrescriptions(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetallProfileResponse(
								response),isScroll);
			}

		};
		post(activity, params, handler, "api/patients");
	}

	public static void getEditProfile(final Activity activity, String id,
			String firstname, String lastname, String dob, String address1,
			String address2, String city, String state, String country,
			String gender, String imgPath) {
		Config.LogError(TAG, "@Editprofile");
		/* Log.e(TAG, "@Editprofile"); */
		File file1;

		RequestParams params = new RequestParams();
		/*
		 * editTextFirstName.getText().toString(),
		 * editTextLastName.getText().toString(), editTextDOB
		 * .getText().toString(), editTextAddress1 .getText().toString(),
		 * editTextAddress2 .getText().toString(), editTextcity
		 * .getText().toString(), editTextstate .getText().toString(),
		 * editTextcountry
		 */

		// httppost = new HttpPost(HtttpRequestHelper.BASE_URL);
		params.put("id", "13");

		params.put("User[first_name]", firstname);
		params.put("User[last_name] ", lastname);
		params.put("User[address1]", dob);
		params.put("User[address2] ", address1);
		params.put("User[country_id]", address2);
		params.put("User[state_id]", city);
		params.put("User[city_id]", state);
		params.put("User[dob]", country);
		params.put(" User[gender] ", gender);

		params.put("ImageUpload[imageFile]", id);
		HttpPost httppost = new HttpPost(HttpRequestHelper.BASE_URL);

		/*
		 * MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		 * builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		 */
		file1 = new File(imgPath);
		ContentBody cbFile1 = new FileBody(file1);
		// ContentResolver.openInputStream(imageUri);
		MultipartEntity multipartEntity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		multipartEntity.addPart("image", cbFile1);

		httppost.setEntity(multipartEntity);

		httppost.setEntity(multipartEntity);

		Config.LogError("Edit the Profile Details", "id: " + id);
		/* Log.e("Edit the Profile Details", "id: " + id); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/update-profile", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/update-profile", "FAILED  >" + response); */
				OnHttpResponseListnerforEditProfile edit = (OnHttpResponseListnerforEditProfile) activity;

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/view-profile", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/view-profile", "SUCCESS  >" + response); */
				OnHttpResponseListnerforEditProfile edit = (OnHttpResponseListnerforEditProfile) activity;

				// edit.onHttpSuccessfulResponseEditProfile(response, JsonParser
				// .getInstance().checkresponsestatus(response), JsonParser
				// .getInstance().parsegetEditProfile(response));

			}

		};
		post(activity, params, handler, "api/update-profile");
	}

	/**
	 * get view Single Patient Prescription List
	 * 
	 * @param activity
	 * @param id
	 */
	public static void viewSinglePatientPrescriptionList(
			final Activity activity, String patient_id,
			final Profile_Model patModel) {
		Config.LogError(TAG, "@viewSinglePatientPrescriptionList");
		/* Log.e(TAG, "@viewSinglePatientPrescriptionList"); */
		RequestParams params = new RequestParams();
		params.put("id", patient_id);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/prescriptions", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/prescriptions", "FAILED  >" + response); */
				OnHtttpResponseListenerForViewDetailPrescriptions login = (OnHtttpResponseListenerForViewDetailPrescriptions) activity;
				login.onHttpFailedViewDetailPrescriptions(throwable, response,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/prescriptions", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/prescriptions", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForViewDetailPrescriptions login = (OnHtttpResponseListenerForViewDetailPrescriptions) activity;

				login.onHttpSuccessfulViewDetailPrescriptions(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance()
								.parsegetallPrescriptionofPatientsResponse(
										response), patModel);

			}

		};
		post(activity, params, handler, "api/prescriptions");
	}

	/**
	 * getHomeRecentPrescription
	 * 
	 * @param activity
	 * @param id
	 */
	/*public static void getHomeRecentPrescription(final Activity activity,
			String id) {
		Config.LogError(TAG, "@getHomeRecentPrescription");
		 Log.e(TAG, "@getHomeRecentPrescription"); 
		RequestParams params = new RequestParams();
		params.put("id", id);

		Config.LogError("getHomeRecentPrescription", "id: " + id);
		 Log.e("getHomeRecentPrescription", "id: " + id); 

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/recent-prescriptions", "FAILED  >"
						+ response);
				
				 * Log.e(TAG + "@api/recent-prescriptions", "FAILED  >" +
				 * response);
				 
				OnHtttpResponseListenerForPatientListHomePage login = (OnHtttpResponseListenerForPatientListHomePage) activity;
				login.onFailedResponseListenerForPatientListHomePage(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/recent-prescriptions", "SUCCESS  >"
						+ response);
				
				 * Log.e(TAG + "@api/recent-prescriptions", "SUCCESS  >" +
				 * response);
				 
				OnHtttpResponseListenerForPatientListHomePage login = (OnHtttpResponseListenerForPatientListHomePage) activity;
				login.onSuccessResponseListenerForPatientListHomePage(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance()
								.parsegetRecentMessagesResponseHome(response));

			}

		};
		post(activity, params, handler, "api/recent-prescriptions");
	}*/

	/**
	 * get All Medicins
	 * 
	 * @param activity
	 * @param id
	 */
	/****
	 * get all frequencies
	 * 
	 * @param activity
	 * @param idSets
	 *            contains userId,PrescriptionId,AddressId
	 */
	public static void getAllFrequencies(final Activity activity,
			final Profile_Model profile,final MyHistoryModel myHistory,final ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,final ArrayList<MedicinsModel> addedMedicinesList) {

		RequestParams params = new RequestParams();

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + " api/medicine-frequencies", "FAILED  >"
						+ response);

				OnHttpResponseListenerforFrequencies allcoutries = (OnHttpResponseListenerforFrequencies) activity;

				allcoutries.onHttpFailedResponsefrequencies(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + " api/medicine-frequencies", "SUCCESS  >"
						+ response);

				OnHttpResponseListenerforFrequencies allcoutries = (OnHttpResponseListenerforFrequencies) activity;
				allcoutries.onHttpSuccessfulResponseFrequencies(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getAllFrequencies(response),
						profile,myHistory,diagNosticModel,addedMedicinesList);

			}

		};
		post(activity, params, handler, "api/medicine-frequencies");

	}

	public static void getAllMedicins(final Activity activity,
			 String key) {
		Config.LogError(TAG, "@getAllMedicins");
		/* Log.e(TAG, "@getAllMedicins"); */
		RequestParams params = new RequestParams();
		params.put("key", key);
		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();

				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}

			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/medicines", "FAILED  >" + response);
				/* Log.e(TAG + "@api/medicines", "FAILED  >" + response); */
				OnHtttpResponseListenerFoMedicinsList login = (OnHtttpResponseListenerFoMedicinsList) activity;
				login.onHttpFailedMedicinsList(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);

				Config.LogError(TAG + "@api/medicines", "SUCCESS  >" + response);
				/* Log.e(TAG + "@api/medicines", "SUCCESS  >" + response); */
				OnHtttpResponseListenerFoMedicinsList login = (OnHtttpResponseListenerFoMedicinsList) activity;
				login.onHttpSuccessfulMedicinsList(response, JsonParser
						.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetAllMedicins(response)
						 );

			}

		};
		post(activity, params, handler, "api/medicines");
	}

	/**
	 * get All Units
	 * 
	 * @param activity
	 * @param profile
	 */
	// public static void getAllUnits(final Activity activity,
	// final Profile_Model profile,
	// final ArrayList<MedicinsListModel> medicinslist) {
	// Log.e(TAG, "@getAllUnits");
	// RequestParams params = new RequestParams();
	//
	// AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
	// private ProgressDialog progress;
	//
	// @Override
	// public void onStart() {
	//
	// super.onStart();
	// progress = ProgressDialog.show(activity, "Loading",
	// "Loading...", true, true,
	// new DialogInterface.OnCancelListener() {
	//
	// @Override
	// public void onCancel(DialogInterface dialog) {
	//
	// client.cancelRequests(activity, true);
	//
	// }
	// });
	// }
	//
	// @Override
	// public void onFinish() {
	//
	// super.onFinish();
	// if (progress != null) {
	// if (progress.isShowing()) {
	// progress.cancel();
	// progress.dismiss();
	// }
	// }
	// }
	//
	// @Override
	// public void onFailure(Throwable throwable, String response) {
	// super.onFailure(throwable, response);
	//
	// Log.e(TAG + "@api/medicine-units", "FAILED  >" + response);
	// OnHtttpResponseListenerFoMedicinsUnitList login =
	// (OnHtttpResponseListenerFoMedicinsUnitList) activity;
	// login.onHttpFailedMedicinsUnitList(response, throwable,
	// JsonParser.getInstance().checkresponsestatus(response));
	// }
	//
	// @Override
	// public void onSuccess(String response) {
	//
	// super.onSuccess(response);
	// Log.e(TAG + "@api/medicine-units", "SUCCESS  >" + response);
	// OnHtttpResponseListenerFoMedicinsUnitList login =
	// (OnHtttpResponseListenerFoMedicinsUnitList) activity;
	// login.onHttpSuccessfulMedicinsUnitList(response, JsonParser
	// .getInstance().checkresponsestatus(response),
	// JsonParser.getInstance().parsegetAllUnits(response),
	// profile, medicinslist);
	//
	// }
	//
	// };
	// post(activity, params, handler, "api/medicine-units");
	// }

	/**
	 * add prescriptions
	 * 
	 * @param activity
	 * @param id
	 * @param med_list
	 * @param patientid
	 */

	public static String addPrescriptionsReturnsId(final Activity activity,
			String id, final ArrayList<MedicinsModel> med_list,
			final String patientid, final Profile_Model profileModel,String diagnostics,String symptomns,ArrayList<DiagnosticsModelForAddPrescription> diagnosticsList) {

		Config.LogError(TAG, "@add_prescriptions");
		/* Log.e(TAG, "@add_prescriptions"); */
		RequestParams params = new RequestParams();
		params.put("patient_id", patientid);
		params.put("id", id);
		params.put("symptoms", symptomns);
		params.put("diagnosis", diagnostics);
		if (med_list != null) {
			for (int i = 0; i < med_list.size(); i++) {
				params.put("medicine[" + i + "][medicine_id]", med_list.get(i)
						.getMedicine_id());
				params.put("medicine[" + i + "][note]", med_list.get(i).getNotes());
				params.put("medicine[" + i + "][medicine_per_dose]", med_list
						.get(i).getMedicines_per_dose());
				/*params.put("medicine[" + i + "][dose_per_day]", med_list.get(i)
						.getDose_per_day());*/
				params.put("medicine[" + i + "][medicine_frequency_id]", med_list
						.get(i).getFrequencyId());
				params.put("medicine[" + i + "][total_days]", med_list.get(i)
						.getTotal_day());
				params.put("medicine[" + i + "][refill_no]", med_list.get(i)
						.getRefill_number());
				params.put("medicine[" + i + "][medicine_unit]", med_list.get(i)
						.getMedicine_unit());
				params.put("medicine[" + i + "][after_food]", med_list.get(i)
						.getAfter_food());
				params.put("medicine[" + i + "][morning_dose]", med_list.get(i)
						.getMornig());
				params.put("medicine[" + i + "][evening_dose]", med_list.get(i)
						.getEvening());
				params.put("medicine[" + i + "][night_dose]", med_list.get(i)
						.getNight());
				params.put("medicine[" + i + "][afternoon_dose]", med_list.get(i)
						.getAfterNoon());
			}
		}
		if (diagnosticsList != null) {
			for (int i = 0; i < diagnosticsList.size(); i++) {
				params.put("diagnostic[" + i + "][diagnostic_id]", diagnosticsList.get(i)
						.getDiagnosticsTestId());
				params.put("diagnostic[" + i + "][value]", diagnosticsList.get(i)
						.getSample());
			}
		}
		
		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				/*
				 * if (progress != null) { if (progress.isShowing()) {
				 * progress.cancel(); progress.dismiss();
				 * 
				 * 
				 * } }
				 */
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);
System.out.println(response);
				Config.LogError(TAG + "@api/create-prescription", "FAILED  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/create-prescription", "FAILED  >" +
				 * response);
				 */
				OnHtttpResponseListenerForAddPrescription login = (OnHtttpResponseListenerForAddPrescription) activity;
				login.onHttpFailedAddPrescription(response, throwable,
						JsonParser.getInstance().checkresponsestatus(response),
						progress);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/create-prescription", "SUCCESS  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/create-prescription", "SUCCESS  >" +
				 * response);
				 */
				HashMap<String, String> responseMsgAndId;
				responseMsgAndId = (JsonParser.getInstance()
						.parseaddPrescriptionResponse(response));
				OnHtttpResponseListenerForAddPrescription login = (OnHtttpResponseListenerForAddPrescription) activity;
				login.onHttpSuccessfulAddPrescriptionreturnsId(response,
						JsonParser.getInstance().checkresponsestatus(response),
						responseMsgAndId.get("message"), patientid,
						profileModel, responseMsgAndId.get("id"), progress);

				/*
				 * Config.LogError("Prescription message  = ",responseMsgAndId.get
				 * ("message"));
				 * Config.LogError("Prescription id value = ",responseMsgAndId
				 * .get("id"));
				 */
			}

		};
		post(activity, params, handler, "api/create-prescription");
		return patientid;
	}

	/**
	 * add prescriptions
	 * 
	 * @param activity
	 * @param id
	 * @param med_list
	 * @param patientid
	 */
	public static void add_prescriptions(final Activity activity, String id,
			final ArrayList<MedicinsModel> med_list, final String patientid,
			final Profile_Model profileModel,String diagnostics,String symptomns,ArrayList<DiagnosticsModelForAddPrescription> diagnosticsList) {
		Config.LogError(TAG, "@add_prescriptions");
		/* Log.e(TAG, "@add_prescriptions"); */
		RequestParams params = new RequestParams();
		params.put("patient_id", patientid);
		params.put("id", id);
		params.put("symptoms", symptomns);
		params.put("diagnosis", diagnostics);
		if (med_list != null) {
			for (int i = 0; i < med_list.size(); i++) {
				params.put("medicine[" + i + "][medicine_id]", med_list.get(i)
						.getMedicine_id());
				params.put("medicine[" + i + "][note]", med_list.get(i).getNotes());
				params.put("medicine[" + i + "][medicine_per_dose]", med_list
						.get(i).getMedicines_per_dose());
				/*params.put("medicine[" + i + "][dose_per_day]", med_list.get(i)
						.getDose_per_day());*/
				params.put("medicine[" + i + "][medicine_frequency_id]", med_list
						.get(i).getFrequencyId());
				params.put("medicine[" + i + "][total_days]", med_list.get(i)
						.getTotal_day());
				params.put("medicine[" + i + "][refill_no]", med_list.get(i)
						.getRefill_number());
				params.put("medicine[" + i + "][medicine_unit]", med_list.get(i)
						.getMedicine_unit());
				params.put("medicine[" + i + "][after_food]", med_list.get(i)
						.getAfter_food());
				params.put("medicine[" + i + "][morning_dose]", med_list.get(i)
						.getMornig());
				params.put("medicine[" + i + "][evening_dose]", med_list.get(i)
						.getEvening());
				params.put("medicine[" + i + "][night_dose]", med_list.get(i)
						.getNight());
				params.put("medicine[" + i + "][afternoon_dose]", med_list.get(i)
						.getAfterNoon());
			}
		}
		if (diagnosticsList != null) {
			for (int i = 0; i < diagnosticsList.size(); i++) {
				params.put("diagnostic[" + i + "][diagnostic_id]", diagnosticsList.get(i)
						.getDiagnosticsTestId());
				params.put("diagnostic[" + i + "][value]", diagnosticsList.get(i)
						.getSample());
			}
		}
		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();

					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/create-prescription", "FAILED  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/create-prescription", "FAILED  >" +
				 * response);
				 */
				OnHtttpResponseListenerForAddPrescription login = (OnHtttpResponseListenerForAddPrescription) activity;
				login.onHttpFailedAddPrescription(response, throwable,
						JsonParser.getInstance().checkresponsestatus(response),
						progress);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/create-prescription", "SUCCESS  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/create-prescription", "SUCCESS  >" +
				 * response);
				 */

				OnHtttpResponseListenerForAddPrescription login = (OnHtttpResponseListenerForAddPrescription) activity;
				login.onHttpSuccessfulAddPrescription(response, JsonParser
						.getInstance().checkresponsestatus(response),
						(JsonParser.getInstance()
								.parseaddPrescriptionResponse(response))
								.get("message"), patientid, profileModel);

			}

		};
		post(activity, params, handler, "api/create-prescription");
	}

	/**
	 * getRecent messages
	 * 
	 * @param activity
	 * @param id
	 */
	public static void getRecent_messages(final Activity activity, String id) {
		Config.LogError(TAG, "@getRecent_messages");
		/* Log.e(TAG, "@getRecent_messages"); */
		RequestParams params = new RequestParams();
		params.put("id", id);
		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/recent-messages", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/recent-messages", "FAILED  >" + response); */
				OnHtttpResponseListenerFoRecentMessage login = (OnHtttpResponseListenerFoRecentMessage) activity;
				login.onHttpFailedRecentMessage(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/recent-messages", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/recent-messages", "SUCCESS  >" + response); */
				OnHtttpResponseListenerFoRecentMessage login = (OnHtttpResponseListenerFoRecentMessage) activity;
				login.onHttpSuccessfulRecentMessage(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetRecentMessages(
								response, activity));

			}

		};
		post(activity, params, handler, "api/recent-messages");
	}

	/**
	 * getMessages item
	 * 
	 * @param activity
	 * @param profile
	 * @param medicinslist
	 */
	public static void getMessages_item(final Activity activity,
			final RecentMessageModel profile, String id, String to_id,
			final int clickedposition) {
		Config.LogError(TAG, "@getMessages_item");
		/* Log.e(TAG, "@getMessages_item"); */
		RequestParams params = new RequestParams();

		params.put("id", id);
		params.put("to_id", to_id);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);
				Config.LogError(TAG + "@api/get-messages", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/get-messages", "FAILED  >" + response); */
				OnHtttpResponseListenerForMessageItemList login = (OnHtttpResponseListenerForMessageItemList) activity;
				login.onHttpFailedMessageItemList(response, throwable,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/get-messages", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/get-messages", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForMessageItemList login = (OnHtttpResponseListenerForMessageItemList) activity;
				login.onHttpSuccessfulMessageItemList(response, JsonParser
						.getInstance().checkresponsestatus(response),
						JsonParser.getInstance()
								.parsegetAllRecentMessageItemList(response),
						profile, clickedposition);

			}

		};
		post(activity, params, handler, "api/get-messages");
	}

	/**
	 * 
	 * @param activity
	 * @param to_id
	 * @param from_id
	 * @param content
	 * @param from_new_msg
	 */
	public static void sendMessage(final Activity activity, String to_id,
			String from_id, final String content, final String from_new_msg) {
		Config.LogError(TAG, "@sendMessage");
		/* Log.e(TAG, "@sendMessage"); */
		RequestParams params = new RequestParams();

		params.put("content", content);
		params.put("from_id", from_id);
		params.put("to_id", to_id);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/send-message", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/send-message", "FAILED  >" + response); */
				OnHtttpResponseListenerForSendMessage login = (OnHtttpResponseListenerForSendMessage) activity;
				login.onHttpFailedSendMessage(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/send-message", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/send-message", "SUCCESS  >" + response); */
				if (from_new_msg.equalsIgnoreCase("from_new_msg")) {
					OnHtttpResponseListenerForSendMessage login = (OnHtttpResponseListenerForSendMessage) activity;
					login.onHttpSuccessfulSendNewMessage(
							response,
							JsonParser.getInstance().checkresponsestatus(
									response),
							JsonParser.getInstance().parsesendMessageResponse(
									response), content);
				} else {
					OnHtttpResponseListenerForSendMessage login = (OnHtttpResponseListenerForSendMessage) activity;
					login.onHttpSuccessfulSendMessage(
							response,
							JsonParser.getInstance().checkresponsestatus(
									response),
							JsonParser.getInstance().parsesendMessageResponse(
									response), content);

				}

			}

		};
		post(activity, params, handler, "api/send-message");
	}

	public static void change_password(final Activity activity, String id,
			String old_password, String new_password, String repeat_password,
			final Profile_Model docter) {
		Config.LogError(TAG, "@change_password");
		/* Log.e(TAG, "@change_password"); */
		RequestParams params = new RequestParams();

		params.put("Password[old_password]", old_password);
		params.put("Password[new_password]", new_password);
		params.put("Password[repeat_password]", repeat_password);
		params.put("id", id);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/change-password", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/change-password", "FAILED  >" + response); */
				OnHtttpResponseListenerForChangePassword login = (OnHtttpResponseListenerForChangePassword) activity;
				login.onHttpFailedChangePassword(response, throwable,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/change-password", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/change-password", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForChangePassword login = (OnHtttpResponseListenerForChangePassword) activity;
				login.onHttpSuccessfulChangePassword(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsepasswordchangeResponse(
								response), docter);

			}

		};
		post(activity, params, handler, "api/change-password");
	}

	/**
	 * get All Countries
	 * 
	 * @param activity
	 * @param userid
	 */
	public static void getAllCountries(final Activity activity) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();

		Config.LogError("fetching  all countries", "All countries");
		/* Log.e("fetching  all countries", "All countries"); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/get all countries", "FAILED  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/get all countries", "FAILED  >" +
				 * response);
				 */
				OnHttpResponseListnerforFetchAllCountries allcoutries = (OnHttpResponseListnerforFetchAllCountries) activity;

				allcoutries.onHttpFailedResponseFetchAllCountries(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

				/*
				 * login.onHttpFailedResponseFetchAllCountries(response,
				 * throwable,
				 * JsonParser.getInstance().checkresponsestatus(response));
				 */
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/get all countries", "SUCCESS  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/get all countries", "SUCCESS  >" +
				 * response);
				 */
				OnHttpResponseListnerforFetchAllCountries allcoutries = (OnHttpResponseListnerforFetchAllCountries) activity;
				allcoutries.onHttpSuccessfulResponseFetchAllCountries(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getAllcoutriesResponse(
								response));
			}

		};
		post(activity, params, handler, "api/countries");

	}

	/**
	 * getAllStates_Details
	 * 
	 * @param activity
	 * @param id
	 */
	/**
	 * getAllStates_Details
	 * 
	 * @param activity
	 * @param id
	 */
	public static void getAllStates_Details(final Activity activity, String id,
			final Boolean showDialog) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("id", id);
		Config.LogError("getAllCities_Details", "getAllStates_Details");
		/* Log.e("getAllCities_Details", "getAllStates_Details"); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/country-details", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/country-details", "FAILED  >" + response); */
				OnHttpResponseListnerforFetchAllStates allcoutries = (OnHttpResponseListnerforFetchAllStates) activity;

				allcoutries.onHttpFailedResponseFetchAllStates(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));
				/*
				 * login.onHttpFailedResponseFetchAllCountries(response,
				 * throwable,
				 * JsonParser.getInstance().checkresponsestatus(response));
				 */
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/country-details", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/country-details", "SUCCESS  >" + response); */
				OnHttpResponseListnerforFetchAllStates allcoutries = (OnHttpResponseListnerforFetchAllStates) activity;
				allcoutries
						.onHttpSuccessfulResponseFetchAllStates(
								response,
								JsonParser.getInstance().checkresponsestatus(
										response), JsonParser.getInstance()
										.getAllstatesResponse(response),
								showDialog);
			}

		};
		post(activity, params, handler, "api/country-details");

	}

	/**
	 * getAllCities_Details
	 * 
	 * @param activity
	 * @param id
	 */
	public static void getAllCities_Details(final Activity activity, String id,
			final Boolean showDialog) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("id", id);
		Config.LogError("getAllCities_Details", "getAllCities_Details");
		/* Log.e("getAllCities_Details", "getAllCities_Details"); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/state-details", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/state-details", "FAILED  >" + response); */
				OnHttpResponseListnerforFetchAllCities allcoutries = (OnHttpResponseListnerforFetchAllCities) activity;

				allcoutries.onHttpFailedResponseFetchAllCities(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));
				/*
				 * login.onHttpFailedResponseFetchAllCountries(response,
				 * throwable,
				 * JsonParser.getInstance().checkresponsestatus(response));
				 */
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/state-details", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/state-details", "SUCCESS  >" + response); */
				OnHttpResponseListnerforFetchAllCities allcoutries = (OnHttpResponseListnerforFetchAllCities) activity;
				allcoutries
						.onHttpSuccessfulResponseFetchAllCities(
								response,
								JsonParser.getInstance().checkresponsestatus(
										response), JsonParser.getInstance()
										.getAllcitiesResponse(response),
								showDialog);
			}

		};
		post(activity, params, handler, "api/state-details");

	}

	/**
	 * add_patient
	 * 
	 * @param activity
	 * @param id
	 * @param med_list
	 * @param patientid
	 * @param profileModel
	 */
	public static void add_patient(final Activity activity, String id,
			final PatientAddModel profileModel) {
		Config.LogError(TAG, "@add_patient");
		/* Log.e(TAG, "@add_patient"); */
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("User[first_name]", profileModel.getFirst_name());
		if (!profileModel.getImageFile().equalsIgnoreCase("")) {
			try {
				params.put("ImageUpload[imageFile]",
						new File(profileModel.getImageFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		params.put("User[last_name]", profileModel.getLast_name());
		params.put("User[password]", profileModel.getPassword());
		params.put("User[email]", profileModel.getEmail());
		params.put("User[phone]", profileModel.getPhone());
		params.put("User[address1]", profileModel.getAddress1());
		params.put("User[address2]", profileModel.getAddress2());
		params.put("User[city_id]", profileModel.getCity_id());
		params.put("User[state_id]", profileModel.getState_id());
		params.put("User[country_id]", profileModel.getCountry_id());
		params.put("User[pincode]", profileModel.getPincode());
		params.put("User[dob]", profileModel.getDob().toString().trim());
		params.put("User[gender]", profileModel.getGender());

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();

					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/create-patient", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/create-patient", "FAILED  >" + response); */
				OnHtttpResponseListenerForAddPatient login = (OnHtttpResponseListenerForAddPatient) activity;
				login.onHttpFailedAddPatient(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/create-patient", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/create-patient", "SUCCESS  >" + response); */

				OnHtttpResponseListenerForAddPatient login = (OnHtttpResponseListenerForAddPatient) activity;
				login.onHttpSuccessfulAddPatient(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parseaddPatientResponse(
								response));
			}

		};
		post(activity, params, handler, "api/create-patient");
	}

	/**
	 * user search message page
	 * 
	 * @param activity
	 * @param email
	 * @param first_name
	 * @param last_name
	 * @param acc_no
	 * @param phone
	 */
	public static void user_search_all(final Activity activity, String email,
			String first_name, String last_name, String account_no, String phone) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("email", email);
		params.put("first_name", first_name);
		params.put("last_name", last_name);
		params.put("account_no", account_no);
		params.put("phone", phone);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/user-search", "FAILED  >"
						+ response);
				/* Log.e(TAG + "@api/user-search", "FAILED  >" + response); */
				OnHttpResponseListnerforSearchAllUsers allcoutries = (OnHttpResponseListnerforSearchAllUsers) activity;

				allcoutries.onHttpFailedResponseSearchAllUsers(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/user-search", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@api/user-search", "SUCCESS  >" + response); */
				OnHttpResponseListnerforSearchAllUsers allcoutries = (OnHttpResponseListnerforSearchAllUsers) activity;
				allcoutries.onHttpSuccessfulResponseSearchAllUsers(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetallProfileResponse(
								response));
			}

		};
		post(activity, params, handler, "api/user-search");

	}

	public static void user_search_inbox(final Activity activity,
			String key_value) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("key", key_value);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/users", "FAILED  >" + response);
				/* Log.e(TAG + "@api/users", "FAILED  >" + response); */
				OnHttpResponseListnerforSearchUsersInbox allcoutries = (OnHttpResponseListnerforSearchUsersInbox) activity;

				allcoutries.onHttpFailedResponseSearchUsersInbox(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/users", "SUCCESS  >" + response);
				/* Log.e(TAG + "@api/users", "SUCCESS  >" + response); */
				OnHttpResponseListnerforSearchUsersInbox allcoutries = (OnHttpResponseListnerforSearchUsersInbox) activity;
				allcoutries.onHttpSuccessfulResponseSearchUsersInbox(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsegetallProfileResponse(
								response));
			}

		};
		post(activity, params, handler, "api/users");

	}

	/**
	 * get All Countries
	 * 
	 * @param activity
	 * @param userid
	 */
	public static void getAllCountriesForEditProfile(final Activity activity,
			final Profile_Model doctor) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();

		Config.LogError("fetching  all countries", "All countries");
		/* Log.e("fetching  all countries", "All countries"); */

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/get all countries", "FAILED  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/get all countries", "FAILED  >" +
				 * response);
				 */
				OnHttpResponseListnerforFetchAllCountries allcoutries = (OnHttpResponseListnerforFetchAllCountries) activity;

				allcoutries.onHttpFailedResponseFetchAllCountries(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

				/*
				 * login.onHttpFailedResponseFetchAllCountries(response,
				 * throwable,
				 * JsonParser.getInstance().checkresponsestatus(response));
				 */
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/get all countries", "SUCCESS  >"
						+ response);
				/*
				 * Log.e(TAG + "@api/get all countries", "SUCCESS  >" +
				 * response);
				 */
				OnHttpResponseListnerforFetchAllCountries allcoutries = (OnHttpResponseListnerforFetchAllCountries) activity;
				allcoutries
						.onHttpSuccessfulResponseFetchAllCountriesForEditProfile(
								response, JsonParser.getInstance()
										.checkresponsestatus(response),
								JsonParser.getInstance()
										.getAllcoutriesResponse(response),
								doctor);
			}

		};
		post(activity, params, handler, "api/countries");

	}

	/**
	 * get All Countries
	 * 
	 * @param activity
	 * @param userid
	 */
	/*
	 * public static void getAllCountriesForDeliveryAddress(final Activity
	 * activity) { // TODO Auto-generated method stub // api/country-details
	 * RequestParams params = new RequestParams();
	 * 
	 * 
	 * Config.LogError("fetching  all countries", "All countries");
	 * Log.e("fetching  all countries", "All countries");
	 * 
	 * AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
	 * private ProgressDialog progress;
	 * 
	 * @Override public void onStart() { super.onStart(); progress =
	 * ProgressDialog.show(activity, "Loading", "Loading...", true, true, new
	 * DialogInterface.OnCancelListener() {
	 * 
	 * @Override public void onCancel(DialogInterface dialog) {
	 * 
	 * client.cancelRequests(activity, true);
	 * 
	 * } }); }
	 * 
	 * @Override public void onFinish() { super.onFinish(); if (progress !=
	 * null) { if (progress.isShowing()) { progress.cancel();
	 * progress.dismiss();
	 * 
	 * } } }
	 * 
	 * @Override public void onFailure(Throwable throwable, String response) {
	 * super.onFailure(throwable, response);
	 * 
	 * Config.LogError(TAG + "@api/get all countries", "FAILED  >" + response);
	 * Log.e(TAG + "@api/get all countries", "FAILED  >" + response);
	 * OnHttpResponseListnerforFetchAllCountries allcoutries =
	 * (OnHttpResponseListnerforFetchAllCountries) activity;
	 * 
	 * allcoutries.onHttpFailedResponseFetchAllCountries(throwable, response,
	 * JsonParser.getInstance().checkresponsestatus(response));
	 * 
	 * 
	 * login.onHttpFailedResponseFetchAllCountries(response, throwable,
	 * JsonParser.getInstance().checkresponsestatus(response));
	 * 
	 * }
	 * 
	 * @Override public void onSuccess(String response) {
	 * 
	 * super.onSuccess(response); Config.LogError(TAG +
	 * "@api/get all countries", "SUCCESS  >" + response); Log.e(TAG +
	 * "@api/get all countries", "SUCCESS  >" + response);
	 * 
	 * 
	 * 
	 * OnHttpResponseListnerforFetchAllCountries allcoutries =
	 * (OnHttpResponseListnerforFetchAllCountries) activity; allcoutries
	 * .onHttpSuccessfulResponseFetchAllCountriesForDeliveryAddress( response,
	 * JsonParser.getInstance() .checkresponsestatus(response),
	 * JsonParser.getInstance() .getAllcoutriesResponse(response),
	 * prescription_id); }
	 * 
	 * }; post(activity, params, handler, "api/countries");
	 * 
	 * }
	 */
	public static void getWalletHistory(final Activity activity, String id,
			String from_date, String to_date) {
		Config.LogError(TAG, "@getWalletHistory");
		/* Log.e(TAG, "@getWalletHistory"); */
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("from_date", from_date);
		params.put("to_date", to_date);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + "@api/wallet", "FAILED  >" + response);
				/* Log.e(TAG + "@api/wallet", "FAILED  >" + response); */
				OnHtttpResponseListenerForWalletHistory login = (OnHtttpResponseListenerForWalletHistory) activity;
				login.onHttpFailedWalletHistory(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@api/wallet", "SUCCESS  >" + response);
				/* Log.e(TAG + "@api/wallet", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForWalletHistory login = (OnHtttpResponseListenerForWalletHistory) activity;

				login.onHttpSuccessfulWalletHistory(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().parsefinalBalanceResponse(
								response), JsonParser.getInstance()
								.get_all_transaction_history(response));
			}

		};
		post(activity, params, handler, "api/wallet");
	}

	/**
	 * saveShippingAddress
	 * 
	 * @param activity
	 * @param Username
	 * @param password
	 * @param type
	 */
	public static void saveDeliveryAddress(final Activity activity,
			DeliveryAddressModel addressModel, final String prescriptionId,
			final ArrayList<PurchaseOrderModel> purchaseOrderModel) {
		Config.LogError(TAG, "@LoginUpUser");
		/* Log.e(TAG, "@LoginUpUser"); */
		RequestParams params = new RequestParams();

		params.put("id", addressModel.getId());
		params.put("PurchaseShippingAddress[name]", addressModel.getName());
		params.put("PurchaseShippingAddress[address_1]",
				addressModel.getAddress1());
		params.put("PurchaseShippingAddress[address_2]",
				addressModel.getAddress2());
		params.put("PurchaseShippingAddress[city_id]", addressModel.getCityId());
		params.put("PurchaseShippingAddress[state_id]",
				addressModel.getStateId());
		params.put("PurchaseShippingAddress[country_id]",
				addressModel.getCountryId());
		params.put("PurchaseShippingAddress[pincode]",
				addressModel.getPinCode());
		params.put("PurchaseShippingAddress[phone]", addressModel.getPhone());
		params.put("PurchaseShippingAddress[landmark]",
				addressModel.getLandMark());

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				/*
				 * if (progress != null) { if (progress.isShowing()) {
				 * progress.cancel(); progress.dismiss();
				 * 
				 * 
				 * 
				 * } }
				 */

			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				/* Log.e(TAG + "@Login", "FAILED  >" + response); */
				Config.LogError(TAG + "@Shipping Address", "FAILED  >"
						+ response);
				OnHttpResponseListenerforSaveShippingAddressFromProfile login = (OnHttpResponseListenerforSaveShippingAddressFromProfile) activity;
				login.onHttpFailedResponseShippingAddressFromProfile(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						progress);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@Shipping Address", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@signUpUser", "SUCCESS  >" + response); */

				OnHttpResponseListenerforSaveShippingAddressFromProfile allcoutries = (OnHttpResponseListenerforSaveShippingAddressFromProfile) activity;
				allcoutries.onHttpSuccessfulResponseShippingAddressFromProfile(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						prescriptionId, purchaseOrderModel, (JsonParser
								.getInstance()
								.parseaddPrescriptionResponse(response))
								.get("id"), progress);
				/*
				 * if (JsonParser.getInstance().checkresponsestatus(response)) {
				 * 
				 * OnHttpResponseListenerforDeliveryAddress login =
				 * (OnHttpResponseListenerforDeliveryAddress) activity;
				 * login.onHttpSuccessfulResponseDeliveryAddressData( addressId,
				 * prescriptionId); HashMap<String, String>
				 * responseAddressMsgAndId; responseAddressMsgAndId =
				 * (JsonParser.getInstance()
				 * .parseaddPrescriptionResponse(response)); addressId =
				 * responseAddressMsgAndId.get("id"); }
				 */

				/*
				 * OnHttpResponseListenerforDeliveryAddress login =
				 * (OnHttpResponseListenerforDeliveryAddress) activity;
				 * login.onHttpSuccessfulResponseDeliveryAddress(response,
				 * JsonParser.getInstance().checkresponsestatus(response),
				 * responseAddressMsgAndId.get("message"), prescriptionId,
				 * responseAddressMsgAndId.get("id"));
				 */

			}

		};
		post(activity, params, handler, "api/save-shipping-address");

	}

	/****
	 * payment Methods
	 * 
	 * @param activity
	 * @param idSets
	 *            contains userId,PrescriptionId,AddressId
	 */
	public static void getPaymentMethods(final Activity activity,
			final HashMap<String, String> idSets) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("id", idSets.get("userId"));

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + " api/payment-methods", "FAILED  >"
						+ response);

				HashMap<String, String> responseMsgAndId;
				responseMsgAndId = (JsonParser.getInstance()
						.parseaddPrescriptionResponse(response));
				OnHttpResponseListenerForPaymentMethods allcoutries = (OnHttpResponseListenerForPaymentMethods) activity;

				allcoutries.onHttpFailedResponsePaymentMethod(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						responseMsgAndId.get("message"));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + " api/payment-methods", "SUCCESS  >"
						+ response);

				OnHttpResponseListenerForPaymentMethods allcoutries = (OnHttpResponseListenerForPaymentMethods) activity;
				allcoutries
						.onHttpSuccessfulResponsePaymentMethod(
								response,
								JsonParser.getInstance().checkresponsestatus(
										response),
								idSets,
								JsonParser.getInstance().getAllPaymentMethods(
										response));
			}

		};
		post(activity, params, handler, "api/payment-methods");

	}

	/****
	 * payment Methods
	 * 
	 * @param activity
	 * @param idSets
	 *            contains userId,PrescriptionId,AddressId
	 */
	public static void placeOrder(final Activity activity,
			final HashMap<String, String> idSets,
			ArrayList<PurchaseOrderModel> purchaseOrderModel) {
		// TODO Auto-generated method stub
		// api/country-details
		RequestParams params = new RequestParams();
		params.put("id", idSets.get("userId"));
		params.put("prescription_id", idSets.get("prescription_id"));
		params.put("payment_method_id", idSets.get("payment_method_id"));
		params.put("shipping_id", idSets.get("shipping_id"));
		for (int i = 0; i < purchaseOrderModel.size(); i++) {
			params.put("medicine_data[" + i + "][id]", purchaseOrderModel
					.get(i).getId());
			params.put("medicine_data[" + i + "][quantity]", purchaseOrderModel
					.get(i).getQuantity());
		}

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + " api/place-order", "FAILED  >"
						+ response);

				HashMap<String, String> responseMsgAndId;
				responseMsgAndId = (JsonParser.getInstance()
						.getPlaceOrderResponse(response));
				OnHttpResponseListenerforPlaceOrder allcoutries = (OnHttpResponseListenerforPlaceOrder) activity;

				allcoutries.onHttpFailedResponsePlaceOrder(throwable, response,
						JsonParser.getInstance().checkresponsestatus(response),
						responseMsgAndId.get("message"));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + " api/place-order", "SUCCESS  >"
						+ response);
				HashMap<String, String> responseMsgAndId;
				responseMsgAndId = (JsonParser.getInstance()
						.getPlaceOrderResponse(response));
				OnHttpResponseListenerforPlaceOrder allcoutries = (OnHttpResponseListenerforPlaceOrder) activity;
				allcoutries.onHttpSuccessfulResponsePlaceOrder(response,
						JsonParser.getInstance().checkresponsestatus(response),
						responseMsgAndId.get("messgae_type"),
						responseMsgAndId.get("purchase_order_id"),
						responseMsgAndId.get("transaction_id"));
			}

		};
		post(activity, params, handler, "api/place-order");

	}

	/**
	 * 
	 * @param activity
	 * @param id
	 * @param date
	 */
	public static void getAllAppointments(final Activity activity, String id,
			/*final String date,*/final Boolean isHomePage,String fromDate,String toDate) {

		RequestParams params = new RequestParams();
		params.put("id", id);
		if (fromDate != null) {
			params.put("from_date", fromDate);
		}
		if (toDate != null) {
			params.put("to_date", toDate);
		}
		

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + " api/appointments", "FAILED  >"
						+ response);

				OnHttpResponseListenerforAppointments appointments = (OnHttpResponseListenerforAppointments) activity;

				appointments.onHttpFailedResponseAppointments(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + " api/appointments", "SUCCESS  >"
						+ response);

				OnHttpResponseListenerforAppointments allcoutries = (OnHttpResponseListenerforAppointments) activity;
				allcoutries.onHttpSuccessfulResponseAppointments(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getAllAppoArrayList(response),
						/*date,*/isHomePage);

			}

		};
		post(activity, params, handler, "api/appointments");

	}

	/**
	 * 
	 * @param activity
	 * @param id
	 * @param date
	 */
	public static void updateAppoinment(final Activity activity, String id,
			String appointmentId, String statusId, final String date,
			final int possition) {

		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("appointment_id", appointmentId);
		params.put("status_id", statusId);
		if (!TextUtils.isEmpty(date)) {
			params.put("datetime", date);
		}

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();

						if (SUCCESS_MESSAGE == 0) {
							OnHttpResponseListenerforUpdateAppointments allcoutries = (OnHttpResponseListenerforUpdateAppointments) activity;
							allcoutries
									.onHttpSuccessfulResponseUpdateAppointmentsClose();
						}

					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(TAG + " api/appointments", "FAILED  >"
						+ response);

				OnHttpResponseListenerforUpdateAppointments appointments = (OnHttpResponseListenerforUpdateAppointments) activity;

				appointments.onHttpFailedResponseUpdateAppointments(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + " api/appointments", "SUCCESS  >"
						+ response);
				OnHttpResponseListenerforUpdateAppointments allcoutries = (OnHttpResponseListenerforUpdateAppointments) activity;
				allcoutries.onHttpSuccessfulResponseUpdateAppointments(
						date,
						possition,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getUpdatedAppoinmentStatusId(
								response),
						JsonParser.getInstance()
								.getUpdatedAppoinmentStatusMessage(response),
						JsonParser.getInstance().parsesendMessageResponse(
								response));

			}

		};
		post(activity, params, handler, "api/update-appointment-status");

	}

	/**
	 * get all promotions
	 * 
	 * @param activity
	 * @param Username
	 * @param password
	 * @param type
	 */
	public static void getAllPromotions(final Activity activity, String userId) {
		Config.LogError(TAG, "@getAllPromotions");
		/* Log.e(TAG, "@LoginUpUser"); */
		RequestParams params = new RequestParams();

		params.put("id", userId);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();

					}
				}

			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				/* Log.e(TAG + "@Login", "FAILED  >" + response); */
				Config.LogError(TAG + "@Promotions", "FAILED  >" + response);
				OnHttpResponseListenerforPromotions login = (OnHttpResponseListenerforPromotions) activity;
				login.onHttpFailedResponsePromotions(throwable, response,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@Promotions", "SUCCESS  >" + response);
				/* Log.e(TAG + "@signUpUser", "SUCCESS  >" + response); */
				OnHttpResponseListenerforPromotions allcoutries = (OnHttpResponseListenerforPromotions) activity;
				allcoutries.onHttpSuccessfulResponsePromotions(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getPromotionsList(response));

			}

		};
		post(activity, params, handler, "api/promotions");

	}

	/**
	 * saveShippingAddress from profile
	 * 
	 * @param activity
	 * @param Username
	 * @param password
	 * @param type
	 */
	public static void saveDeliveryAddressFromProfile(final Activity activity,
			String patientId, String userId, final String prescriptionId,
			final ArrayList<PurchaseOrderModel> purchaseOrderModel) {
		Config.LogError(TAG, "@LoginUpUser");
		/* Log.e(TAG, "@LoginUpUser"); */
		RequestParams params = new RequestParams();

		params.put("id", userId);
		params.put("patient_id", patientId);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				/*
				 * if (progress != null) { if (progress.isShowing()) {
				 * progress.cancel(); progress.dismiss();
				 * 
				 * } }
				 */

			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				/* Log.e(TAG + "@Login", "FAILED  >" + response); */
				Config.LogError(TAG + "@Shipping Address", "FAILED  >"
						+ response);
				OnHttpResponseListenerforSaveShippingAddressFromProfile login = (OnHttpResponseListenerforSaveShippingAddressFromProfile) activity;
				login.onHttpFailedResponseShippingAddressFromProfile(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						progress);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@Shipping Address", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@signUpUser", "SUCCESS  >" + response); */
				OnHttpResponseListenerforSaveShippingAddressFromProfile allcoutries = (OnHttpResponseListenerforSaveShippingAddressFromProfile) activity;
				allcoutries.onHttpSuccessfulResponseShippingAddressFromProfile(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						prescriptionId, purchaseOrderModel, JsonParser
								.getInstance().getShippingAddressId(response),
						progress);

			}

		};
		post(activity, params, handler,
				"api/save-shipping-address-from-profile");

	}

	/**
	 * get all promotions
	 * 
	 * @param activity
	 * @param Username
	 * @param password
	 * @param type
	 */
	public static void getViewPromotion(final Activity activity, String userId,
			String promotionId) {
		Config.LogError(TAG, "@getViewPromotions");
		/* Log.e(TAG, "@LoginUpUser"); */
		RequestParams params = new RequestParams();

		params.put("id", userId);
		params.put("promotion_id", promotionId);

		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {

				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {

				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();

					}
				}

			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				/* Log.e(TAG + "@Login", "FAILED  >" + response); */
				Config.LogError(TAG + "@View Promotions", "FAILED  >"
						+ response);
				OnHttpResponseListenerforViewPromotion login = (OnHttpResponseListenerforViewPromotion) activity;
				login.onHttpFailedResponseViewPromotions(throwable, response,
						JsonParser.getInstance().checkresponsestatus(response));
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(TAG + "@View Promotions", "SUCCESS  >"
						+ response);
				/* Log.e(TAG + "@signUpUser", "SUCCESS  >" + response); */
				OnHttpResponseListenerforViewPromotion allcoutries = (OnHttpResponseListenerforViewPromotion) activity;
				allcoutries.onHttpSuccessfulResponseViewPromotions(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getPromotionDetails(response));

			}

		};
		post(activity, params, handler, "api/view-promotion");

	}

}
