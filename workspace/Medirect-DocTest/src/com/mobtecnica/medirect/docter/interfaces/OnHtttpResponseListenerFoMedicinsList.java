package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import android.app.ProgressDialog;

import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerFoMedicinsList {

//	void onHttpSuccessfulMedicinsList(String response, Boolean responseStatus,
//			ArrayList<MedicinsListModel> medicinslist);
	void onHttpSuccessfulMedicinsList(String response, Boolean responseStatus,
			ArrayList<MedicinsListModel> medicinslist);
	void onHttpFailedMedicinsList(String response, Throwable throwable,
			boolean responseStatus);
}
