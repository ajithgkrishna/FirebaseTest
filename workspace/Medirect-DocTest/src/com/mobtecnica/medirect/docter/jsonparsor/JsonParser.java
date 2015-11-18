package com.mobtecnica.medirect.docter.jsonparsor;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.PaymentMethodModels;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.PromotionModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;
import com.mobtecnica.medirect.docter.models.UnitsModel;
import com.mobtecnica.medirect.docter.models.ViewPromotionModel;
import com.mobtecnica.medirect.docter.models.WalletHistory;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.content.Context;

public class JsonParser {
	private static JsonParser objJsonParser;

	private String TAG = "JsonParser";
	public Context con;

	public JsonParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static JsonParser getInstance() {
		if (objJsonParser == null) {
			objJsonParser = new JsonParser();

		}
		return objJsonParser;
	}

	/**
	 * checkloginstatus true or false
	 * 
	 * @param response
	 * @return
	 */
	public boolean checkresponsestatus(String response) {

		JSONObject jsonResponse = null;

		try {

			jsonResponse = new JSONObject(response);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (NullPointerException e) {

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * parseloginResponse returns userIdorfailmessage or userIdorfailmessage
	 * 
	 * @param response
	 * @return
	 */
	public String parseloginResponse(String response) {
		JSONObject jsonResponse = null;
		String userIdorfailmessage = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			JSONObject dataJson = null;
			try {
				dataJson = jsonResponse.getJSONObject("user");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				userIdorfailmessage = dataJson.getString("id");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userIdorfailmessage;
		} else {

			try {
				userIdorfailmessage = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userIdorfailmessage;
		}

	}

	/**
	 * parsegetprofileResponse-->> DoctorProfile profile
	 * 
	 * @param response
	 * @return
	 */
	public Profile_Model parsegetprofileResponse(String response) {
		Config.LogError(TAG, "@parsegetprofileResponse");
		String message = "";
		JSONObject jsonObj;
		Profile_Model doctorProfile = null;
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONObject result = jsonObj.getJSONObject("data");
				doctorProfile = new Profile_Model(result.getString("id"),
						result.getString("account_no"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("address"), result.getString("email"),
						result.getString("phone"),
						result.getString("registration_date"),
						result.getString("dob"), result.getString("gender"),
						result.getString("medical_council"),
						result.getString("registration_number"),
						result.getString("qualification"),
						result.getString("university"),
						result.getString("photo"), result.getString("credits"),
						result.getString("user_status_id"),
						result.getString("added_by"),
						result.getString("added_on"),
						result.getString("modified_by"),
						result.getString("modified_on"),
						result.getString("user_type_id"),
						result.getString("address1"),
						result.getString("address2"),
						result.getString("country_name"),
						result.getString("state_name"),
						result.getString("city_name"),
						result.getString("country_id"),
						result.getString("state_id"),
						result.getString("city_id"),
						result.getString("pincode"), result.getString("age"));

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetprofileResponse message = " + message);
		return doctorProfile;
	}

	/**
	 * parsegetallProfileResponse
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<Profile_Model> parsegetallProfileResponse(String response) {
		Config.LogError(TAG, "@parsegetallProfileResponse");
		String message = "";
		JSONObject jsonObj;
		ArrayList<Profile_Model> doctorProfile = new ArrayList<Profile_Model>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					doctorProfile.add(new Profile_Model(result.getString("id"),
							result.getString("account_no"), result
									.getString("first_name"), result
									.getString("last_name"), result
									.getString("address"), result
									.getString("email"), result
									.getString("phone"), result
									.getString("registration_date"), result
									.getString("dob"), result
									.getString("gender"), result
									.getString("medical_council"), result
									.getString("registration_number"), result
									.getString("qualification"), result
									.getString("university"), result
									.getString("photo"), result
									.getString("credits"), result
									.getString("user_status_id"), result
									.getString("added_by"), result
									.getString("added_on"), result
									.getString("modified_by"), result
									.getString("modified_on"), result
									.getString("user_type_id"), result
									.getString("age")));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetallProfileResponse message = " + message);
		return doctorProfile;
	}

	/**
	 * parsegetallPrescriptionofPatientsResponse
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<PrescriptionDetailModel> parsegetallPrescriptionofPatientsResponse(
			String response) {
		Config.LogError(TAG, "@parsegetallPrescriptionofPatientsResponse");
		String message = "";
		JSONObject jsonObj;
		ArrayList<PrescriptionDetailModel> prescriptionDetail = new ArrayList<PrescriptionDetailModel>();

		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArrayPatient = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArrayPatient.length(); i++) {
					JSONObject result = resultArrayPatient.getJSONObject(i);

					JSONArray resultArraymedicines = result
							.getJSONArray("medicines");
					ArrayList<MedicinsModel> medicinsModel = new ArrayList<MedicinsModel>();
					for (int j = 0; j < resultArraymedicines.length(); j++) {
						JSONObject resultMed = resultArraymedicines
								.getJSONObject(j);
						medicinsModel
								.add(new MedicinsModel(
										resultMed.getString("id"),
										resultMed.getString("medicine_id"),
										resultMed.getString("medicine_name"),
										resultMed.getString("prescription_id"),
										resultMed
												.getString("medicines_per_dose"),
										resultMed
												.getString("medicine_unit_name"),
										resultMed.getString("dose_per_day")
												+ "/day",
										resultMed.getString("total_day")
												+ " days",
										resultMed.getString("refill_number")
												+ " refill",
										resultMed.getString("notes"),
										resultMed.getString("consumption_mode"),
										resultMed.getString("after_food"),
										resultMed
												.getString("initial_dispatch_quantity")));
					}
					prescriptionDetail.add(new PrescriptionDetailModel(result
							.getString("id"), result.getString("doctor_id"),
							result.getString("patient_id"), result
									.getString("prescription_type_id"), result
									.getString("chronic"), result
									.getString("expiry_date"), result
									.getString("added_by"), result
									.getString("added_on"), result
									.getString("modified_by"), result
									.getString("modified_on"), result
									.getString("prescription_status_id"),
							result.getString("doctor_name"), medicinsModel));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG,
				"parsegetallPrescriptionofPatientsResponse message = "
						+ message);
		return prescriptionDetail;
	}

	/**
	 * parsegetRecentMessagesResponseHome
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<RecentPrescriptions> parsegetRecentMessagesResponseHome(
			String response) {
		Config.LogError(TAG, "@parsegetallProfileResponse");
		String message = "";
		JSONObject jsonObj;
		ArrayList<RecentPrescriptions> decentmsg = new ArrayList<RecentPrescriptions>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					decentmsg.add(new RecentPrescriptions(result
							.getString("prescription_id"), result
							.getString("patient_name"), result.getString(
							"date_time").split(" ")[1], result.getString(
							"date_time").split(" ")[0]));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		Config.LogError(TAG, "parsegetRecentMessagesResponseHome message = "
				+ message);
		return decentmsg;
	}

	/**
	 * parseget All Medicins
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<MedicinsListModel> parsegetAllMedicins(String response) {
		Config.LogError(TAG, "@parsegetAllMedicins");
		String message = "";
		JSONObject jsonObj;
		ArrayList<MedicinsListModel> medicinsListModel = new ArrayList<MedicinsListModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
//					medicinsListModel.add(new MedicinsListModel(result
//							.getString("id"), result.getString("name"), result
//							.getString("generic_name"), result
//							.getString("brand_name"), result
//							.getString("company_name"), result
//							.getString("chemical_name"), result
//							.getString("introduction"), result
//							.getString("indication"), result
//							.getString("contra_indication"), result
//							.getString("special_precaution"), result
//							.getString("side_effects"), result
//							.getString("drug_interaction"), result
//							.getString("prescription_required"), result
//							.getString("dosage"), result
//							.getString("measurement"), result
//							.getString("perunit"), result
//							.getString("medicine_unit_name"), result
//							.getString("original_price"), result
//							.getString("discounted_price"), result
//							.getString("image"), result
//							.getString("stock_quantity"), result
//							.getString("status"), result
//							.getString("doctor_commission"), result
//							.getString("medicine_category_id")));
					medicinsListModel.add(new MedicinsListModel(result
							.getString("id"), result.getString("value"), result
							.getString("medicine_unit_id"), result
							.getString("medicine_unit_name")));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetAllMedicins message = " + message);
		return medicinsListModel;
	}

	/**
	 * parseaddPrescriptionResponse
	 * 
	 * @param response
	 * @return
	 */
	public HashMap<String, String> parseaddPrescriptionResponse(String response) {
		JSONObject jsonResponse = null;
		HashMap<String, String> responseMsgAndId = new HashMap<String, String>();
		String resposeResultmsg = "", responseId = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				resposeResultmsg = jsonResponse.getString("message");
				responseId = jsonResponse.getString("id");
				responseMsgAndId.put("id", responseId);
				responseMsgAndId.put("messgae", resposeResultmsg);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "parseaddPrescriptionResponse message = "
				+ resposeResultmsg);
		return responseMsgAndId;

	}

	/**
	 * parsegetAllUnits
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<UnitsModel> parsegetAllUnits(String response) {
		Config.LogError(TAG, "@parsegetAllUnits");
		String message = "";
		JSONObject jsonObj;
		ArrayList<UnitsModel> UnitsModelList = new ArrayList<UnitsModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					UnitsModelList.add(new UnitsModel(result.getString("id"),
							result.getString("name")));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		Config.LogError(TAG, "parsegetAllUnits message = " + message);
		return UnitsModelList;
	}

	public ArrayList<RecentMessageModel> parsegetRecentMessages(
			String response, Context con) {
		Config.LogError(TAG, "@parsegetAllUnits");
		String message = "";
		JSONObject jsonObj;
		ArrayList<RecentMessageModel> recentMessageModelList = new ArrayList<RecentMessageModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					JSONObject messageOb = result.getJSONObject("message");
					JSONObject UserObject = result
							.getJSONObject("User Details");
					int ageV = Utilities.getInstance(con).getAge(
							Integer.parseInt(UserObject.getString("dob").split(
									"-")[0]),
							Integer.parseInt(UserObject.getString("dob").split(
									"-")[1]),
							Integer.parseInt(UserObject.getString("dob").split(
									"-")[2]));
					recentMessageModelList.add(new RecentMessageModel(messageOb
							.getString("id"), messageOb
							.getString("primary_message_id"), messageOb
							.getString("message_type_id"), messageOb
							.getString("date"), messageOb.getString("content"),
							messageOb.getString("from_user_id"), messageOb
									.getString("to_user_id"), messageOb
									.getString("message_status_id"), result
									.getString("new-messages"), UserObject
									.getString("first_name")
									+ " "
									+ UserObject.getString("last_name"),
							UserObject.getString("address"), "" + ageV,
							UserObject.getString("account_no"), UserObject
									.getString("email"), UserObject
									.getString("phone"), UserObject
									.getString("photo")));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetRecentMessages message = " + message);
		return recentMessageModelList;
	}

	/**
	 * parsegetAllRecentMessageItemList
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<RecentMessageModel> parsegetAllRecentMessageItemList(
			String response) {
		Config.LogError(TAG, "@parsegetAllRecentMessageItemList");
		String message = "";
		JSONObject jsonObj;
		ArrayList<RecentMessageModel> recentMessageModelList = new ArrayList<RecentMessageModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					recentMessageModelList.add(new RecentMessageModel(result
							.getString("id"), result
							.getString("primary_message_id"), result
							.getString("message_type_id"), result
							.getString("date"), result.getString("content"),
							result.getString("from_user_id"), result
									.getString("to_user_id"), result
									.getString("message_status_id"), ""));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetAllUnits message = " + message);
		return recentMessageModelList;
	}

	/**
	 * parse send Message Response
	 * 
	 * @param response
	 * @return
	 */
	public String parsesendMessageResponse(String response) {
		JSONObject jsonResponse = null;
		String resposeResultmsg = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "parsesendMessageResponse message = "
				+ resposeResultmsg);
		return resposeResultmsg;

	}

	/**
	 * parsepasswordchangeResponse
	 * 
	 * @param response
	 * @return
	 */
	public String parsepasswordchangeResponse(String response) {
		JSONObject jsonResponse = null;
		String resposeResultmsg = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "parsepasswordchangeResponse message = "
				+ resposeResultmsg);
		return resposeResultmsg;

	}

	/**
	 * get All coutriesResponse
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<GetAllCountryModel> getAllcoutriesResponse(String response) {
		Config.LogError(TAG, "@getAllcoutriesResponse");
		JSONObject jsonObj;
		ArrayList<GetAllCountryModel> getAllcountrylist = new ArrayList<GetAllCountryModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					// JSONObject messageOb = result.getJSONObject();
					// JSONObject messageOb =new JSONObject();

					getAllcountrylist.add(new GetAllCountryModel(result
							.getString("id"), result.getString("name"), result
							.getString("name_english"), result
							.getString("show_in_list"), result
							.getString("show_order_engine"), result
							.getString("country_code"), result
							.getString("code")));

				}

			} else {

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		Config.LogError(TAG, "All countries  list country code = "
				+ getAllcountrylist.get(0).getCountry_code());
		return getAllcountrylist;
	}

	/**
	 * getAllstates Response
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<GetAllStateModel> getAllstatesResponse(String response) {
		Config.LogError(TAG, "@getAllstatesResponse");
		JSONObject jsonObj;
		ArrayList<GetAllStateModel> getAllstateslist = new ArrayList<GetAllStateModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {
				JSONObject resultAr = jsonObj.getJSONObject("data");
				JSONArray resultArray = resultAr.getJSONArray("states");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					// JSONObject messageOb = result.getJSONObject();
					// JSONObject messageOb =new JSONObject();

					getAllstateslist.add(new GetAllStateModel(result
							.getString("id"), result.getString("country_id"),
							result.getString("name")));

				}

			} else {

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "getAllstateslist = " + getAllstateslist);
		return getAllstateslist;
	}

	/**
	 * get All cities Response
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<GetAllCitiesModel> getAllcitiesResponse(String response) {
		Config.LogError(TAG, "@getAllcitiesResponse");
		JSONObject jsonObj;
		ArrayList<GetAllCitiesModel> getAllCitiesModellist = new ArrayList<GetAllCitiesModel>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {
				JSONObject resultAr = jsonObj.getJSONObject("data");
				JSONArray resultArray = resultAr.getJSONArray("cities");
				for (int i = 0; i < resultArray.length(); i++) {

					JSONObject result = resultArray.getJSONObject(i);
					// JSONObject messageOb = result.getJSONObject();
					// JSONObject messageOb =new JSONObject();

					getAllCitiesModellist.add(new GetAllCitiesModel(result
							.getString("id"), result.getString("state_id"),
							result.getString("name")));

				}

			} else {

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "getAllcitiesResponse = " + getAllCitiesModellist);
		return getAllCitiesModellist;
	}

	/**
	 * parseaddPatientResponse
	 * 
	 * @param response
	 * @return
	 */
	public String parseaddPatientResponse(String response) {
		JSONObject jsonResponse = null;
		String resposeResultmsg = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "parseaddPatientResponse message = "
				+ resposeResultmsg);
		return resposeResultmsg;

	}

	/**
	 * get_all_transaction_history
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<WalletHistory> get_all_transaction_history(String response) {
		Config.LogError(TAG, "@get_all_transaction_history");
		String message = "";
		JSONObject jsonObj;
		ArrayList<WalletHistory> WalletHistoryList = new ArrayList<WalletHistory>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("transactions");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					WalletHistoryList.add(new WalletHistory(result
							.getString("id"), result.getString("user_id"),
							result.getString("transaction_type_id"), result
									.getString("datetime"), result
									.getString("amount"), result
									.getString("balance"), result
									.getString("transaction_type_name")));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "get_all_transaction_history message = " + message);
		return WalletHistoryList;
	}

	/**
	 * parsefinalBalanceResponse
	 * 
	 * @param response
	 * @return
	 */
	public String parsefinalBalanceResponse(String response) {
		JSONObject jsonResponse = null;
		String balance_resposeResultmsg = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = null;
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				balance_resposeResultmsg = jsonResponse.getString("balance");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				balance_resposeResultmsg = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "parsefinalBalanceResponse message = "
				+ balance_resposeResultmsg);
		return balance_resposeResultmsg;

	}

	/**
	 * parsegetAllPaymentMethods
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<PaymentMethodModels> getAllPaymentMethods(String response) {
		Config.LogError(TAG, "@parsegetAllPaymentMethods");
		String message = "";
		JSONObject jsonObj;
		ArrayList<PaymentMethodModels> paymentMethodList = new ArrayList<PaymentMethodModels>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("payment-methods");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					paymentMethodList.add(new PaymentMethodModels(result
							.getString("id"), result.getString("name"), result
							.getString("status_id")));
					Config.LogError(TAG + "paymentList", message);
				}

			} else {

				message = jsonObj.getString("status");
				Config.LogError(TAG, message);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Config.LogError(TAG, "parsegetAllPaymentMethods message = " + message);
		return paymentMethodList;
	}

	/**
	 * PlaceOrder Response
	 * 
	 * @param response
	 * @return
	 */
	public HashMap<String, String> getPlaceOrderResponse(String response) {
		JSONObject jsonResponse = null;
		HashMap<String, String> responseMsgAndId = new HashMap<String, String>();
		String resposeResultmsgs = "", responsePurchaseOrderId = "", responseTransactionId = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				resposeResultmsgs = jsonResponse.getString("message");
				responsePurchaseOrderId = jsonResponse
						.getString("purchase_order_id");
				responseTransactionId = jsonResponse
						.getString("transaction_id");
				responseMsgAndId.put("messgae_type", resposeResultmsgs);
				responseMsgAndId.put("purchase_order_id",
						responsePurchaseOrderId);
				responseMsgAndId.put("transaction_id", responseTransactionId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				resposeResultmsgs = jsonResponse.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "placeOrder message = " + resposeResultmsgs);
		return responseMsgAndId;

	}

	/**
	 * Frequency Response
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<FrequencyModel> getAllFrequencies(String response) {
		JSONObject jsonResponse = null;
		ArrayList<FrequencyModel> frequencyModel = new ArrayList<FrequencyModel>();
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				JSONArray resultArray = jsonResponse.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					frequencyModel.add(new FrequencyModel(result
							.getString("id"), result.getString("name")));
					Config.LogError(TAG + "Frequency list", statusMessage);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "frequency list = " + statusMessage);
		return frequencyModel;

	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<Appointments> getAllAppoArrayList(String response) {
		JSONObject jsonResponse = null;
		ArrayList<Appointments> appointmentModel = new ArrayList<Appointments>();
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				JSONArray resultArray = jsonResponse.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					appointmentModel.add(new Appointments(result
							.getString("id"), result.getString("doctor_id"),
							result.getString("clinic_id"), result
									.getString("patient_id"), result
									.getString("datetime"), result
									.getString("appointment_status_id"), result
									.getString("added_by"), result
									.getString("added_on"), result
									.getString("modified_by"), result
									.getString("modified_on"), result
									.getString("appointment_status"), result
									.getString("patient_name"), result
									.getString("note"), result
									.getString("patient_gender"), result
									.getString("patient_last_visit"), result
									.getString("patient_age"), result
									.getString("patient_phone")
									, result
									.getString("patient_email"), result
									.getString("photo"), result
									.getString("patient_account_no"), result
									.getString("patient_address")));
					Config.LogError(TAG + "Frequency list", statusMessage);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
  
			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "appointmentModel list = " + statusMessage);
		return appointmentModel;

	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public String getUpdatedAppoinmentStatusId(String response) {
		JSONObject jsonResponse = null;
		String appoinmentStatus = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {
			String appoinmentJSON = null;
			try {
				appoinmentJSON = jsonResponse.getString("appointment");
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				JSONObject jsonAppoinment = new JSONObject(appoinmentJSON);
				appoinmentStatus = jsonAppoinment
						.getString("appointment_status_id");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "appointmentModel list = " + statusMessage);
		return appoinmentStatus;

	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public String getUpdatedAppoinmentStatusMessage(String response) {
		JSONObject jsonResponse = null;
		String appoinmentStatus = "";
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {
			String appoinmentJSON = null;
			try {
				appoinmentJSON = jsonResponse.getString("appointment");
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				JSONObject jsonAppoinment = new JSONObject(appoinmentJSON);
				appoinmentStatus = jsonAppoinment
						.getString("appointment_status");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "appointmentModel list = " + statusMessage);
		return appoinmentStatus;

	}

	/**
	 * PurchaseOrderModel Response
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<PurchaseOrderModel> getAllPurchaseOrderItems(
			String response) {
		JSONObject jsonResponse = null;
		ArrayList<PurchaseOrderModel> PurchaseModel = new ArrayList<PurchaseOrderModel>();
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				String data = jsonResponse.getString("data");
				JSONObject jsonData = new JSONObject(data);

				JSONArray resultArray = jsonData.getJSONArray("medicines");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					PurchaseModel.add(new PurchaseOrderModel(result
							.getString("id"), result.getString("name"), result
							.getString("image"), result
							.getString("introduction"), result
							.getString("unit_price"), "1"));
					Config.LogError(TAG + "Purchase Order list", statusMessage);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "Purchase list = " + statusMessage);
		return PurchaseModel;

	}

	/**
	 * PurchaseOrderModel Response
	 * 
	 * @param response
	 * @return
	 */
	public PurchaseOrderPatientDetails getAllPurchaseOrderPatientAddress(
			String response) {
		JSONObject jsonResponse = null;
		PurchaseOrderPatientDetails patientDeatils = null;
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				String data = jsonResponse.getString("data");
				JSONObject jsonData = new JSONObject(data);

				String patientData = jsonData.getString("patient_details");
				JSONObject jsonPatientData = new JSONObject(patientData);

				patientDeatils = new PurchaseOrderPatientDetails(
						jsonPatientData.getString("id"),
						jsonPatientData.getString("name"),
						jsonPatientData.getString("address"),
						jsonPatientData.getString("phone"));
				
				Config.LogError(TAG + "Purchase Order patient Address", patientData);
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "Purchase list = " + statusMessage);
		return patientDeatils;

	}
	
	/**
	 * save shipping address id
	 * 
	 * @param response
	 * @return
	 */
	public String getShippingAddressId(String response) {
		JSONObject jsonResponse = null;
		String shippingId = null;
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {

				shippingId = jsonResponse.getString("id");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "shippingId = " + shippingId);
		return shippingId;

	}
	
	/**
	 * 
	 * @param get all promotions
	 * @return
	 */
	public ArrayList<PromotionModel> getPromotionsList(String response) {
		JSONObject jsonResponse = null;
		ArrayList<PromotionModel> appointmentModel = new ArrayList<PromotionModel>();
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				JSONArray resultArray = jsonResponse.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					appointmentModel.add(new PromotionModel(result
							.getString("id"), result.getString("title"),
							result.getString("datetime"), result
									.getString("read_status")));
					Config.LogError(TAG + "Promotion list", result.toString());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "Promotion list status= " + statusMessage);
		return appointmentModel;

	}
	

	/**
	 * 
	 * @param get view promotion details
	 * @return
	 */
	public ViewPromotionModel getPromotionDetails(String response) {
		JSONObject jsonResponse = null;
		ViewPromotionModel viewPromotionModdel = null;
		try {
			jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusMessage = "";
		try {
			statusMessage = jsonResponse.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (statusMessage.equalsIgnoreCase("1")) {

			try {
				JSONObject result = jsonResponse.getJSONObject("data");

					viewPromotionModdel = new ViewPromotionModel(result
							.getString("title"), result.getString("datetime"),
							result.getString("content"));
					Config.LogError(TAG + "Promotion view", result.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				statusMessage = jsonResponse.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Config.LogError(TAG, "Promotion view= " + statusMessage);
		return viewPromotionModdel;

	}
	public String getResponseStringMessage(String response) {
		   JSONObject jsonResponse = null;
		   String userIdorfailmessage = "";
		   if (response != null) {
		    try {
		     jsonResponse = new JSONObject(response);
		    } catch (JSONException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    String statusMessage = "";
		    try {
		     statusMessage = jsonResponse.getString("status");
		    } catch (JSONException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    if (statusMessage.equalsIgnoreCase("1")) {
		     try {
		      userIdorfailmessage = jsonResponse.getString("message");
		     } catch (JSONException e) {
		      // TODO Auto-generated catch block
		     }
		     return userIdorfailmessage;
		    } else {
		     
		     try {
		      userIdorfailmessage = jsonResponse.getString("message");
		     } catch (JSONException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }
		     return userIdorfailmessage;
		    }
		   }else {
		    return userIdorfailmessage;
		   }
		   

		  }
}
