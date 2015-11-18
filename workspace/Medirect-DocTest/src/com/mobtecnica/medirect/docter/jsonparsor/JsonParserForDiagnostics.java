package com.mobtecnica.medirect.docter.jsonparsor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;
import com.mobtecnica.medirect.docter.models.DiagnosticsTypeModel;
import com.mobtecnica.medirect.docter.utils.Config;


public class JsonParserForDiagnostics {

	private String TAG = "JsonParserForDiagnostics";

	public static JsonParserForDiagnostics getNewInstance() {
		JsonParserForDiagnostics objJsonParser = null;
		if (objJsonParser == null) {
			objJsonParser = new JsonParserForDiagnostics();
		}
		return objJsonParser;
	}

	public ArrayList<DiagnosticsTypeModel> getDiagnosticsType(String response) {
		JSONObject jsonResponse = null;
		ArrayList<DiagnosticsTypeModel> speciaModel = new ArrayList<DiagnosticsTypeModel>();
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
					JSONArray resultArray = jsonResponse.getJSONArray("data");
					for (int i = 0; i < resultArray.length(); i++) {
						JSONObject result = resultArray.getJSONObject(i);
						speciaModel.add(new DiagnosticsTypeModel(result
								.getString("id"), result.getString("name")));
						Config.LogError(TAG + "getSpecializationsList", statusMessage);
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
			Config.LogError(TAG, "getSpecializationsList = " + statusMessage);
		}
		
		return speciaModel;

	}
	public ArrayList<DiagnosticsTestModel> getDiagnosticsTest(String response) {
		JSONObject jsonResponse = null;
		ArrayList<DiagnosticsTestModel> speciaModel = new ArrayList<DiagnosticsTestModel>();
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
					JSONArray resultArray = jsonResponse.getJSONArray("data");
					for (int i = 0; i < resultArray.length(); i++) {
						JSONObject result = resultArray.getJSONObject(i);
						speciaModel.add(new DiagnosticsTestModel(result
								.getString("id"), result.getString("type"), result.getString("test")
								, result.getString("test_name"), result.getString("unit"), result.getString("def_value")));
						Config.LogError(TAG + "getSpecializationsList", statusMessage);
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
			Config.LogError(TAG, "getSpecializationsList = " + statusMessage);
		}
		
		return speciaModel;

	}
}
