package com.mobtecnica.medirect.docter.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForMyHistory;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForMyHistory;
import com.mobtecnica.medirect.docter.models.Profile_Model;


public class HttpRequestHelperForMyHistory {

	public static void MyHistory(final Activity activity, String patientId,final Profile_Model profile) {
		
		RequestParams params = new RequestParams();
		params.put("id", patientId);

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

				
				/* Log.e(TAG + "@api/change-password", "FAILED  >" + response); */
				OnHtttpResponseListenerForMyHistory login = (OnHtttpResponseListenerForMyHistory) activity;
				login.onHttpFailedMyHistory(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				
				/* Log.e(TAG + "@api/change-password", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForMyHistory login = (OnHtttpResponseListenerForMyHistory) activity;
				login.onHttpSuccessfulMyHistory(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParserForMyHistory.getNewInstance().getMyHistory(
								response),profile);

			}

		};
		HttpRequestHelper.post(activity, params, handler,
				"api/get-history");
	}
}
