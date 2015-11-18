package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;


public interface OnHttpResponseListenerforFrequencies {

	/*****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param prescriptionAddressPaymentId
	 */
	void onHttpSuccessfulResponseFrequencies(String response,
			boolean responseStatus,
			ArrayList<FrequencyModel> paymentMethodLists,Profile_Model profile,MyHistoryModel myHistory,ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,ArrayList<MedicinsModel> addedMedicinesList);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponsefrequencies(Throwable throwable,
			String response, boolean resposeStatus);
}
