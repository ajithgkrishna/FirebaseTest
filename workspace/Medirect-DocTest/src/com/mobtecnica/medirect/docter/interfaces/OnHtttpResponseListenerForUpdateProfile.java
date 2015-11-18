package com.mobtecnica.medirect.docter.interfaces;


public interface OnHtttpResponseListenerForUpdateProfile {

	void onHttpSuccessfulUpdateProfile(String response,
			boolean responseStatus, String responseResult);

	void onHttpFailedAddUpdateProfile(String response, Throwable throwable,
			boolean responseStatus);
}
