package com.mobtecnica.medirect.docter.interfaces;

public interface OnHttpResponseListenerforPlaceOrder {
	/****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param purchaseOrderId
	 * @param transactionId
	 */
	void onHttpSuccessfulResponsePlaceOrder(String response,
			boolean responseStatus, String responseResultMsg,
			String purchaseOrderId, String transactionId);
	/****
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponsePlaceOrder(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage);
}
