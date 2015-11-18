package com.mobtecnica.medirect.docter.interfaces;

public interface OnHtttpResponseListenerForSendMessage {

	void onHttpSuccessfulSendMessage(String response, boolean responsestatus,
			String responseResult, String reply_message);
	void onHttpSuccessfulSendNewMessage(String response, boolean responsestatus,
			String responseResult, String reply_message);

	void onHttpFailedSendMessage(String response, Throwable throwable,
			boolean responsestatus);
}
