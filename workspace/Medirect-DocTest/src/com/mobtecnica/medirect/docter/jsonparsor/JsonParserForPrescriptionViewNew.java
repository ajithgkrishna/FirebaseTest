package com.mobtecnica.medirect.docter.jsonparsor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobtecnica.medirect.docter.models.DiagnosticModel;
import com.mobtecnica.medirect.docter.models.MedicineModelinPrescriptionDetail;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailViewModelnew;
import com.mobtecnica.medirect.docter.utils.Config;



public class JsonParserForPrescriptionViewNew {

	private String TAG = "JsonParserForChangePassword";

	public static JsonParserForPrescriptionViewNew getNewInstance() {
		JsonParserForPrescriptionViewNew objJsonParser = null;
		if (objJsonParser == null) {
			objJsonParser = new JsonParserForPrescriptionViewNew();

		}
		return objJsonParser;
	}

	/**
	 * passwordchangeResponse
	 * 
	 * @param response
	 * @return
	 */
	public ArrayList<PrescriptionDetailViewModelnew> getPrescriptionViewNew(
			String response) {
		Config.LogError(TAG, "@JsonParserForPrescriptionList");
		JSONObject jsonObj;
		ArrayList<PrescriptionDetailViewModelnew> prescreptionDetList = new ArrayList<PrescriptionDetailViewModelnew>();
		if (response != null) {
			try {
				jsonObj = new JSONObject(response);
				String statusval = jsonObj.getString("status");

				if (statusval.equalsIgnoreCase("1")) {
					System.out.println();
					JSONArray dataArray = jsonObj.getJSONArray("data");
					for (int i = 0; i < dataArray.length(); i++) {
						JSONObject prescrJsonObject = dataArray
								.getJSONObject(i);

						JSONArray medicinJsonArray = prescrJsonObject
								.getJSONArray("medicines");
						ArrayList<MedicineModelinPrescriptionDetail> medicinesArray = new ArrayList<MedicineModelinPrescriptionDetail>();
						for (int j = 0; j < medicinJsonArray.length(); j++) {
							JSONObject medicinObject = medicinJsonArray
									.getJSONObject(j);
							medicinesArray
									.add(new MedicineModelinPrescriptionDetail(
											medicinObject.getString("id"),
											medicinObject
													.getString("medicine_id"),
											medicinObject
													.getString("prescription_id"),
											medicinObject
													.getString("medicines_per_dose"),
											medicinObject
													.getString("medicine_unit"),
											medicinObject
													.getString("medicine_frequency_id"),
											medicinObject
													.getString("morning_dose"),
											medicinObject
													.getString("afternoon_dose"),
											medicinObject
													.getString("evening_dose"),
											medicinObject
													.getString("night_dose"),
											medicinObject
													.getString("total_day"),
											medicinObject
													.getString("refill_number"),
											medicinObject.getString("notes"),
											medicinObject
													.getString("consumption_mode"),
											medicinObject
													.getString("after_food"),
											medicinObject
													.getString("initial_dispatch_quantity"),
											medicinObject
													.getString("medicine_name"),
											medicinObject
													.getString("medicine_unit_name"),medicinObject
													.getString("timings"),medicinObject
													.getString("food_preference")));
						}
					
						JSONArray diagnosisJsonArray = prescrJsonObject
								.getJSONArray("diagnostics");
						ArrayList<DiagnosticModel> diagnosisArray = new ArrayList<DiagnosticModel>();
						for (int j = 0; j < diagnosisJsonArray.length(); j++) {
							JSONObject diaObject = diagnosisJsonArray
									.getJSONObject(j);
							diagnosisArray.add(new DiagnosticModel(diaObject
									.getString("id"), diaObject
									.getString("prescription_id"), diaObject
									.getString("diagnostic_id"), diaObject
									.getString("value"), diaObject
									.getString("diagnostic_type"), diaObject
									.getString("test_name"), diaObject
									.getString("sample"), diaObject
									.getString("result"), diaObject
									.getString("def_value")));

						}

						prescreptionDetList
								.add(new PrescriptionDetailViewModelnew(
										prescrJsonObject.getString("id"),
										prescrJsonObject.getString("doctor_id"),
										prescrJsonObject
												.getString("patient_id"),
										prescrJsonObject
												.getString("prescription_type_id"),
										prescrJsonObject.getString("symptoms"),
										prescrJsonObject.getString("diagnosis"),
										prescrJsonObject.getString("chronic"),
										prescrJsonObject
												.getString("expiry_date"),
										prescrJsonObject.getString("added_by"),
										prescrJsonObject.getString("added_on"),
										prescrJsonObject
												.getString("modified_by"),
										prescrJsonObject
												.getString("modified_on"),
										prescrJsonObject
												.getString("prescription_status_id"),
										prescrJsonObject
												.getString("doctor_name"),
										medicinesArray, diagnosisArray));

					}

				} else {

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			Config.LogError(TAG, "All PrescriptionList = "
					+ prescreptionDetList.toString());
		}

		return prescreptionDetList;
	}
}
