package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;

public interface OnHtttpResponseListenerForDiagnosticsTests {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param appointmentslist
	 */
	void onHttpSuccessfulDiagnosticsTests(String response,
			boolean responseStatus,
			ArrayList<DiagnosticsTestModel> diagnosticsTypeLis);
}
