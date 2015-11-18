package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;

public interface OnHttpResponseListnerforFetchAllCities {
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseFetchAllCities(String response, boolean responseStatus,
			ArrayList<GetAllCitiesModel> rescentcityList,Boolean showDialog);
	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param arrayList
	 */
	void onHttpFailedResponseFetchAllCities(Throwable throwable, String response,
			boolean resposeStatus);
}

