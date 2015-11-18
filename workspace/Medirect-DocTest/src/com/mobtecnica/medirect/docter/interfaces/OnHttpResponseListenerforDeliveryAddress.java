package com.mobtecnica.medirect.docter.interfaces;

public interface OnHttpResponseListenerforDeliveryAddress {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseDeliveryAddress(String response,
			boolean responseStatus, String responseResultMsg,
			String prescriptionId, String addressId);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseDeliveryAddress(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage);

	void onHttpSuccessfulResponseDeliveryAddressData(String addressId,
			String PrescriptionId);
}
