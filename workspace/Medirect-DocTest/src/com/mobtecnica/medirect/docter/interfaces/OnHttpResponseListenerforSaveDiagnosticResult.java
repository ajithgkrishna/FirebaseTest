package com.mobtecnica.medirect.docter.interfaces;



public interface OnHttpResponseListenerforSaveDiagnosticResult {
	/****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param purchaseOrderId
	 * @param transactionId
	 */
	void onHttpSuccessfulResponseSaveDiagnosticResult(String response,
			boolean responseStatus, String responseResultMsg);
	/****
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseSaveDiagnosticResult(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage);
}
