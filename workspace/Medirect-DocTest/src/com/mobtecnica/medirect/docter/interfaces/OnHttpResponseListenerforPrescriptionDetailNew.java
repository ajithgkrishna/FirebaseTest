package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailViewModelnew;
import com.mobtecnica.medirect.docter.models.Profile_Model;



public interface OnHttpResponseListenerforPrescriptionDetailNew {
	/****
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param purchaseOrderId
	 * @param transactionId
	 */
	void onHttpSuccessfulResponsePrescriptionDetailNew(String response,
			boolean responseStatus, String responseResultMsg,
			ArrayList<PrescriptionDetailViewModelnew> prescriptionDetailViewList,Profile_Model profile, MyHistoryModel myHistory,Boolean isScroll);
	/****
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponsePrescriptionDetailNew(Throwable throwable,
			 boolean resposeStatus, String responseResultMessage);
}
