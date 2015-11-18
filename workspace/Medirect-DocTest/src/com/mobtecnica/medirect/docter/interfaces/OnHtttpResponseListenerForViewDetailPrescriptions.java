package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForViewDetailPrescriptions {

	void onHttpSuccessfulViewDetailPrescriptions(String response,
			boolean response_status,
			ArrayList<PrescriptionDetailModel> prescriptiondetail,Profile_Model patModel);

	void onHttpFailedViewDetailPrescriptions(Throwable throwable,
			String response, boolean responsestatus);
}
