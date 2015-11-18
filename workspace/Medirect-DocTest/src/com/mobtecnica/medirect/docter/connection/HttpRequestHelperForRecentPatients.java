package com.mobtecnica.medirect.docter.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforAppointments;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForPatientListHomePage;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForHomePatient;
import com.mobtecnica.medirect.docter.utils.Config;

public class HttpRequestHelperForRecentPatients {
private static String Tag = "appointment";
	
	public static void getSearchPatients(final Activity activity, 
			 String key) {
		RequestParams params = new RequestParams();
		params.put("key", key);

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

				Config.LogError(Tag + " api/search-patients", "FAILED  >"
						+ response);

				OnHtttpResponseListenerForPatientListHomePage appointments = (OnHtttpResponseListenerForPatientListHomePage) activity;

				appointments.onFailedResponseListenerForPatientListHomePage(throwable,
						response,
						JsonParser.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(Tag + " api/search-patients", "SUCCESS  >"
						+ response);

				OnHtttpResponseListenerForPatientListHomePage allcoutries = (OnHtttpResponseListenerForPatientListHomePage) activity;
				allcoutries.onSuccessResponseListenerForPatientListHomePage(response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParserForHomePatient.parsegetRecentMessagesResponseHome(response)
						);

			}

		};
		HttpRequestHelper.post(activity, params, handler, "api/search-patients");
	}
}
