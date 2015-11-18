package com.mobtecnica.medirect.docter.interfaces;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForLogin {
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 */
	void onHttpSuccessfulResponseLogin(String response, boolean responseStatus,
			String responseResultMsg);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseLogin(Throwable throwable, String response,
			boolean resposeStatus, String responseResultMessage);
}
