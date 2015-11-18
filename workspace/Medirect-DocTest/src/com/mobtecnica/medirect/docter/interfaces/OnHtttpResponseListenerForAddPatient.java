package com.mobtecnica.medirect.docter.interfaces;


public interface OnHtttpResponseListenerForAddPatient {

	void onHttpSuccessfulAddPatient(String response,
			boolean responseStatus, String responseResult);

	void onHttpFailedAddPatient(String response, Throwable throwable,
			boolean responseStatus);
}
