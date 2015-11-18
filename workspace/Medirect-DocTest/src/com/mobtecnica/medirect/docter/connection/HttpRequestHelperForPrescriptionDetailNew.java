package com.mobtecnica.medirect.docter.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPrescriptionDetailNew;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForPrescriptionViewNew;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;



public class HttpRequestHelperForPrescriptionDetailNew {

	public static void prescriptionDetailNew(final Activity activity,
			String id, String from_date, String to_date, String page,final Profile_Model profile, final MyHistoryModel myHistory,final Boolean isScroll) {
		Config.LogError(HttpRequestHelper.TAG, "@prescriptionDetailNew");
		/* Log.e(TAG, "@change_password"); */
		RequestParams params = new RequestParams();

		params.put("id", id);
		if (!from_date.equalsIgnoreCase("")) {
			params.put("from_date", from_date);
		}
		if (!to_date.equalsIgnoreCase("")) {
			params.put("to_date", to_date);
		}
		params.put("page", page);

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

								HttpRequestHelper.client.cancelRequests(
										activity, true);

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

				Config.LogError(HttpRequestHelper.TAG
						+ "@api/prescriptionDetailNew", "FAILED  >" + response);
				/* Log.e(TAG + "@api/change-password", "FAILED  >" + response); */
				OnHttpResponseListenerforPrescriptionDetailNew login = (OnHttpResponseListenerforPrescriptionDetailNew) activity;
				login.onHttpFailedResponsePrescriptionDetailNew(
						throwable,
						JsonParser.getInstance().checkresponsestatus(response),
						response);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(HttpRequestHelper.TAG
						+ "@api/prescriptionDetailNew", "SUCCESS  >" + response);
				/* Log.e(TAG + "@api/change-password", "SUCCESS  >" + response); */
				OnHttpResponseListenerforPrescriptionDetailNew login = (OnHttpResponseListenerforPrescriptionDetailNew) activity;
				login.onHttpSuccessfulResponsePrescriptionDetailNew(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParser.getInstance().getResponseStringMessage(
								response),
						JsonParserForPrescriptionViewNew.getNewInstance()
								.getPrescriptionViewNew(response),profile,myHistory,isScroll);

			}

		};
		HttpRequestHelper.post(activity, params, handler, "api/prescriptions");
	}
}
