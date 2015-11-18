package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;

public interface OnHttpResponseListnerforFetchAllCountries {
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseFetchAllCountries(String response, boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList);
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseFetchAllCountriesForEditProfile(String response, boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList,Profile_Model doctor);
	
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseFetchAllCountriesForDeliveryAddress(String response, boolean responseStatus,
			ArrayList<GetAllCountryModel> rescentCountryList,String prescription_id);
	
	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param arrayList
	 */
	void onHttpFailedResponseFetchAllCountries(Throwable throwable, String response,
			boolean resposeStatus);
}

