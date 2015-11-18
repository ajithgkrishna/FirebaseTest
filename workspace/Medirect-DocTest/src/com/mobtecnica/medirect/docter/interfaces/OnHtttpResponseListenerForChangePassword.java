package com.mobtecnica.medirect.docter.interfaces;

import com.mobtecnica.medirect.docter.models.Profile_Model;

public interface OnHtttpResponseListenerForChangePassword {

	void onHttpSuccessfulChangePassword(String response,
			boolean responsestatus, String responseResult, Profile_Model docter);

	void onHttpFailedChangePassword(String response, Throwable throwable,
			boolean responsestatus);
}
