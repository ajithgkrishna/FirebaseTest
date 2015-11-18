package com.mobtecnica.medirect.docter.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForDiagnosticsTests;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForMyHistory;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForDiagnostics;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForMyHistory;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;

public class HttpRequestHelperForDiagnostics {

	public static void getDiagnostictsTests(final Activity activity,String typeId) {
		
		RequestParams params = new RequestParams();
		params.put("id", typeId);
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

				
				Config.LogError("" + "@api/get-diagnostic-tests", "FAILED  >" + response); 
				OnHtttpResponseListenerForMyHistory login = (OnHtttpResponseListenerForMyHistory) activity;
				login.onHttpFailedMyHistory(response, throwable, JsonParser
						.getInstance().checkresponsestatus(response));

			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				
				 Config.LogError("" + "@api/get-diagnostic-tests", "SUCCESS  >" + response); 
				 OnHtttpResponseListenerForDiagnosticsTests login = (OnHtttpResponseListenerForDiagnosticsTests) activity;
				login.onHttpSuccessfulDiagnosticsTests(
						response,
						JsonParser.getInstance().checkresponsestatus(response),
						JsonParserForDiagnostics.getNewInstance().getDiagnosticsTest(response));

			}

		};
		HttpRequestHelper.post(activity, params, handler,
				"api/get-diagnostic-tests");
	}
}
