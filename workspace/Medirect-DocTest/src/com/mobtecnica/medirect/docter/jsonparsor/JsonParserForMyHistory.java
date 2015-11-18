package com.mobtecnica.medirect.docter.jsonparsor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.utils.Config;



public class JsonParserForMyHistory {

	private String TAG = "JsonParserForMyHistory";

	public static JsonParserForMyHistory getNewInstance() {
		JsonParserForMyHistory objJsonParser = null;
		if (objJsonParser == null) {
			objJsonParser = new JsonParserForMyHistory();
		}
		return objJsonParser;
	}

	public MyHistoryModel getMyHistory(String response) {
		Config.LogError(TAG, "@getMyHistory");
		String message = "";
		JSONObject jsonOb;
		MyHistoryModel historyModel = null;
		if (response != null) {
			try {
				jsonOb = new JSONObject(response);
				JSONObject jsonObj = jsonOb.getJSONObject("data");
				String statusval = jsonObj.getString("status");

				if (statusval.equalsIgnoreCase("1")) {
					JSONArray jsonObjAllergy = jsonObj
							.getJSONArray("allergies");
					ArrayList<AllergiMedicineModel> allergies = new ArrayList<AllergiMedicineModel>();
					for (int i = 0; i < jsonObjAllergy.length(); i++) {
						JSONObject jsonAllergyItem = jsonObjAllergy
								.getJSONObject(i);
						allergies.add(new AllergiMedicineModel(jsonAllergyItem
								.getString("medicine_name"), jsonAllergyItem
								.getString("medicine_id")));
					}

					historyModel = new MyHistoryModel(jsonObj.getString("id"),
							jsonObj.getString("user_id"),
							jsonObj.getString("family_history"),
							jsonObj.getString("surgical_history"),
							jsonObj.getString("dm_status"),
							jsonObj.getString("htn_status"),
							jsonObj.getString("thyroid_status"),
							jsonObj.getString("asthma_status"),
							jsonObj.getString("tumor_status"),
							jsonObj.getString("ca_status"),
							/*jsonObj.getString("added_by"),
							jsonObj.getString("added_on"),
							jsonObj.getString("status"),*/
							jsonObj.getString("medical_history"), allergies);

				} else {

					message = jsonObj.getString("status");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			Config.LogError(TAG, "get_all_transaction_history message = "
					+ message);
		}

		return historyModel;
	}

	public String getAddtoCartMessage(String response) {
		Config.LogError(TAG, "@JsonParserForAddtoCart");
		JSONObject jsonObj;
		String message = "";
		if (response != null) {
			try {
				jsonObj = new JSONObject(response);
				String statusval = jsonObj.getString("status");

				if (statusval.equalsIgnoreCase("1")) {

					message = jsonObj.getString("message");

				} else {
					message = jsonObj.getString("message");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			Config.LogError(TAG, "message; = " + message);
		}

		return message;
	}

}
