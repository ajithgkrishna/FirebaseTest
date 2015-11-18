package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;



public interface OnHtttpResponseListenerForSaveHistory {

	void onHttpSuccessfulSaveHistory(String response, boolean responsestatus,
			String responseMessage,MyHistoryModel myHistory,Profile_Model profile,
			ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			ArrayList<MedicinsModel> addedMedicinesList);

	/****
	 * 
	 * @param response
	 * @param throwable
	 * @param responsestatus
	 */
	void onHttpFailedSaveHistory(String response, Throwable throwable,
			boolean responsestatus, String responseMessage);
}
