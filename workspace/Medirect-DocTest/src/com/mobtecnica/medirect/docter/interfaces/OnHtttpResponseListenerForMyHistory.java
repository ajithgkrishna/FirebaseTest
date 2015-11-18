package com.mobtecnica.medirect.docter.interfaces;

import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;



public interface OnHtttpResponseListenerForMyHistory {
	/****
	 * 
	 * @param response
	 * @param responsestatus
	 * @param responseResult
	 * @param docter
	 */
	void onHttpSuccessfulMyHistory(String response,
			boolean responsestatus,
			MyHistoryModel myHistory,Profile_Model profile);
	/****
	 * 
	 * @param response
	 * @param throwable
	 * @param responsestatus
	 */
	void onHttpFailedMyHistory(String response, Throwable throwable,
			boolean responsestatus);
}
