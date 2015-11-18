package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Profile_Model;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForAllPrescriptions {

	void onHttpSuccessfulPrescriptions(String response,
			boolean response_status, ArrayList<Profile_Model> patient_profile,Boolean isScroll);

	void onHttpFailedPrescriptions(Throwable throwable, String response,
			boolean responsestatus);
}
