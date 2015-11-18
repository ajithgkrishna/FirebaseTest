package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.ViewPromotionModel;

public interface OnHttpResponseListenerforViewPromotion {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param promotionsList
	 */
	void onHttpSuccessfulResponseViewPromotions(String response,
			boolean responseStatus,
			ViewPromotionModel promotion);

	/***
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 */
	void onHttpFailedResponseViewPromotions(Throwable throwable, String response,
			boolean resposeStatus);
}
