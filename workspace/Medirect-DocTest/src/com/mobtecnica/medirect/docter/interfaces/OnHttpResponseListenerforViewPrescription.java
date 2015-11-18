package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Appointments;

public interface OnHttpResponseListenerforViewPrescription {

	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param appointmentslist
	 */
	void onHttpSuccessfulResponseAppointments(String response,
			boolean responseStatus,
			
			ArrayList<Appointments> appointmentslist,String Date);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseAppointments(Throwable throwable, String response,
			boolean resposeStatus);

}
