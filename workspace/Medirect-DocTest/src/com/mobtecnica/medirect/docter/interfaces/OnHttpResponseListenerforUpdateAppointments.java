package com.mobtecnica.medirect.docter.interfaces;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.models.Appointments;

public interface OnHttpResponseListenerforUpdateAppointments {
	/**
	 * 
	 * @param response
	 * @param responseStatus
	 * @param responseResultMsg
	 * @param appointmentslist
	 */
	void onHttpSuccessfulResponseUpdateAppointments(String date, int possition,
			boolean resposeStatus, String appoinmentStatusId,String appointmentStatusMessage,String message);

	/**
	 * 
	 * @param throwable
	 * @param response
	 * @param resposeStatus
	 * @param responseResultMessage
	 */
	void onHttpFailedResponseUpdateAppointments(Throwable throwable,
			String response, boolean resposeStatus);
	
	void onHttpSuccessfulResponseUpdateAppointmentsClose();
}
