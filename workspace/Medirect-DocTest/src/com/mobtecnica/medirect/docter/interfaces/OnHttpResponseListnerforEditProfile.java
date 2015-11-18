package com.mobtecnica.medirect.docter.interfaces;

public interface OnHttpResponseListnerforEditProfile {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseEditProfile(String response, boolean responseStatus,
			String responseResultMsg);
	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseEditProfile(Throwable throwable, String response,
			boolean resposeStatus, String responseResultMessage);
}

