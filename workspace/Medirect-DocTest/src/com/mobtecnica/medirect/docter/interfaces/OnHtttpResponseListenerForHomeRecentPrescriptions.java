package com.mobtecnica.medirect.docter.interfaces;

import com.mobtecnica.medirect.docter.models.Profile_Model;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForHomeRecentPrescriptions {

	
	void onHttpSuccessPatientProfile(String response, boolean responseStatus,
			Profile_Model doctProf);

	void onHttpFailedPatientProfile(Throwable throwable, String response,
			boolean responseStatus);
}
