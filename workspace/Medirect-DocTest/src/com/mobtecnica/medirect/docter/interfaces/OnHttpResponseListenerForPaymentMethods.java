package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.mobtecnica.medirect.docter.models.PaymentMethodModels;

public interface OnHttpResponseListenerForPaymentMethods {

	/*****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param prescriptionAddressPaymentId
	 */
	void onHttpSuccessfulResponsePaymentMethod(String response,
			boolean responseStatus,
			HashMap<String, String> prescriptionAddressPaymentId,
			ArrayList<PaymentMethodModels> paymentMethodLists);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponsePaymentMethod(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage);

}
