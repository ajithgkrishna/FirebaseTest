package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.UnitsModel;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerFoMedicinsUnitList {

	void onHttpSuccessfulMedicinsUnitList(String response,
			Boolean responseStatus, ArrayList<UnitsModel> unitsModelList,
			Profile_Model profile,
			ArrayList<MedicinsListModel> MedicinsModelList);

	void onHttpFailedMedicinsUnitList(String response, Throwable throwable,
			boolean responseStatus);
}
