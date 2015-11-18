package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.RecentMessageModel;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerFoRecentMessage {

	void onHttpSuccessfulRecentMessage(String response, Boolean responseStatus,
			ArrayList<RecentMessageModel> recentMessageModellist);
	void onHttpFailedRecentMessage(String response, Throwable throwable,
			boolean responseStatus);
}
