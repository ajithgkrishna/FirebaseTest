package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;
import com.mobtecnica.medirect.docter.models.WalletHistory;

public interface OnHtttpResponseListenerForWalletHistory {

	void onHttpSuccessfulWalletHistory(String response, boolean responseStatus,
			String final_balance, ArrayList<WalletHistory> walletHistory_list);

	void onHttpFailedWalletHistory(String response, Throwable throwable,
			boolean responseStatus);
}
