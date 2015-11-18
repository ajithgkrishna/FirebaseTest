package com.mobtecnica.medirect.docter.interfaces;

import android.app.ProgressDialog;

import com.mobtecnica.medirect.docter.models.Profile_Model;

public interface OnHtttpResponseListenerForAddPrescription {

	void onHttpSuccessfulAddPrescription(String response,
			boolean responseStatus, String responseResult, String pat_id,
			Profile_Model pat_prof);

	void onHttpSuccessfulAddPrescriptionreturnsId(String response,
			boolean responseStatus, String responseResult, String pat_id,
			Profile_Model pat_prof,String pres_id,ProgressDialog progress);
	
	void onHttpFailedAddPrescription(String response, Throwable throwable,
			boolean responseStatus,ProgressDialog progress);
}
