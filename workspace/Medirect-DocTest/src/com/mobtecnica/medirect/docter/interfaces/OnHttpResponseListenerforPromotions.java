package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.PromotionModel;

public interface OnHttpResponseListenerforPromotions {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param promotionsList
	 */
	void onHttpSuccessfulResponsePromotions(String response,
			boolean responseStatus,
			ArrayList<PromotionModel> promotionsList);

	/***
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 */
	void onHttpFailedResponsePromotions(Throwable throwable, String response,
			boolean resposeStatus);
}
