package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Profile_Model;

public interface OnHttpResponseListnerforSearchAllUsers {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseSearchAllUsers(String response,
			boolean responseStatus, ArrayList<Profile_Model> profile_model_list);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseSearchAllUsers(Throwable throwable,
			String response, boolean resposeStatus);
}
