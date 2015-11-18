package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import android.app.ProgressDialog;

import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;

public interface OnHttpResponseListenerforSaveShippingAddressFromProfile {

	/****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param PrescriptionId
	 * @param purchaseOrderModel
	 * @param purchasePAtientDetails
	 */
	void onHttpSuccessfulResponseShippingAddressFromProfile(String response,
			boolean responseStatus, String PrescriptionId,
			ArrayList<PurchaseOrderModel> purchaseOrderModel,
			String shippingId,ProgressDialog progress);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseShippingAddressFromProfile(Throwable throwable,
			String response, boolean resposeStatus,ProgressDialog progress);
}
