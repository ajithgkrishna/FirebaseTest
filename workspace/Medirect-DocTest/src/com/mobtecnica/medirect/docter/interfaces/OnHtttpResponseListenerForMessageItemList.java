package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.MedicinsListModel;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.models.UnitsModel;

/**
 * 
 * @author MOBTECNICA DEV #114
 *
 */
public interface OnHtttpResponseListenerForMessageItemList {

	void onHttpSuccessfulMessageItemList(String response,
			Boolean responseStatus,
			ArrayList<RecentMessageModel> RecentMessageItemModelList,
			RecentMessageModel profile,int clickedpos);

	void onHttpFailedMessageItemList(String response, Throwable throwable,
			boolean responseStatus);
}
