package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;

public interface OnHttpResponseListenerforPurchaseOrder {

	/****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param PrescriptionId
	 * @param purchaseOrderModel
	 * @param purchasePAtientDetails
	 */
	void onHttpSuccessfulResponsePurchaseOrder(String response,
			boolean responseStatus, String PrescriptionId,
			ArrayList<PurchaseOrderModel> purchaseOrderModel,
			PurchaseOrderPatientDetails purchasePAtientDetails);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponsePurchaseOrder(Throwable throwable,
			String response, boolean resposeStatus);

}
