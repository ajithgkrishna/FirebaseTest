package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;

public interface OnHttpResponseListnerforFetchAllStates {
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseFetchAllStates(String response, boolean responseStatus,
			ArrayList<GetAllStateModel> rescentStatesList , boolean showDialog);
	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param arrayList
	 */
	void onHttpFailedResponseFetchAllStates(Throwable throwable, String response,
			boolean resposeStatus);

}

