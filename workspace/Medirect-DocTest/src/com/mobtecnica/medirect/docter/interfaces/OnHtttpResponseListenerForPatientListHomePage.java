package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForPatientListHomePage {
	void onSuccessResponseListenerForPatientListHomePage(String response,
			boolean response_status,
			ArrayList<Profile_Model> patientProfileList);

	void onFailedResponseListenerForPatientListHomePage(Throwable throwable,
			String response, boolean responsestatus);
}
