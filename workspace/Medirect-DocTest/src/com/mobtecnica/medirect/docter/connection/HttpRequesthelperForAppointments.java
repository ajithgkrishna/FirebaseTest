package com.mobtecnica.medirect.docter.connection;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforAppointments;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.utils.Config;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

public class HttpRequesthelperForAppointments {
	private static String Tag = "appointment";
	
	public static void getTodaysAppointments(final Activity activity, String id,
			 String fromDate, String toDate,final Boolean isHomePage) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("from_date", fromDate);
		params.put("to_date", toDate);
		AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
			private ProgressDialog progress;

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(activity, "Loading",
						"Loading...", true, true,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {

								HttpRequestHelper.client.cancelRequests(activity, true);

							}
						});
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (progress != null) {
					if (progress.isShowing()) {
						progress.cancel();
						progress.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Throwable throwable, String response) {
				super.onFailure(throwable, response);

				Config.LogError(Tag + " api/appointments", "FAILED  >"
						+ response);

				OnHttpResponseListenerforAppointments appointments = (OnHttpResponseListenerforAppointments) activity;

				appointments.onHttpFailedResponseAppointments(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(Tag + " api/appointments", "SUCCESS  >"
						+ response);

				OnHttpResponseListenerforAppointments allcoutries = (OnHttpResponseListenerforAppointments) activity;
				allcoutries.onHttpSuccessfulResponseAppointments(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getAllAppoArrayList(response),
						/*date,*/isHomePage);

			}

		};
		HttpRequestHelper.post(activity, params, handler, "api/appointments");
	}
}
