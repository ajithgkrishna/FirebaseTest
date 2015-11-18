package com.mobtecnica.medirect.docter.connection;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForSaveHistory;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForMyHistory;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;


public class HttpRequestHelperForSaveHistory {

	public static void SaveHistory(final Activity activity,
			MyHistoryModel myhistory,final Profile_Model profile,
			final ArrayList<DiagnosticsModelForAddPrescription> diagNosticModel,
			final ArrayList<MedicinsModel> addedMedicinesList) {
		Config.LogError(HttpRequestHelper.TAG, "@SaveHistory");
		/* Log.e(TAG, "@change_password"); */
		RequestParams params = new RequestParams();

		params.put("id", myhistory.getId());
		params.put("patient_id", myhistory.getUser_id());

		params.put("UserHistory[family_history]", myhistory.getFamily_history());
		params.put("UserHistory[surgical_history]",
				myhistory.getSurgical_history());
		params.put("UserHistory[dm_status]", myhistory.getDm_status());
		params.put("UserHistory[htn_status]", myhistory.getHtn_status());
		params.put("UserHistory[thyroid_status]", myhistory.getThyroid_status());
		params.put("UserHistory[asthma_status]", myhistory.getAsthma_status());
		params.put("UserHistory[tumor_status]", myhistory.getTumor_status());
		params.put("UserHistory[ca_status]", myhistory.getCa_status());
		for (int i = 0; i < myhistory.getAllergies().size(); i++) {
			params.put("allergy[" + i + "]", myhistory.getAllergies().get(i)
					.getMedicine_id());
		}

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

				Config.LogError(HttpRequestHelper.TAG + "@api/save-history",
						"FAILED  >" + response);
				/* Log.e(TAG + "@api/change-password", "FAILED  >" + response); */
				OnHtttpResponseListenerForSaveHistory login = (OnHtttpResponseListenerForSaveHistory) activity;
				login.onHttpFailedSaveHistory(response, throwable, JsonParser.getInstance().checkresponsestatus(response), response);
			}

			@Override
			public void onSuccess(String response) {

				super.onSuccess(response);
				Config.LogError(HttpRequestHelper.TAG + "@api/save-history",
						"SUCCESS  >" + response);
				/* Log.e(TAG + "@api/change-password", "SUCCESS  >" + response); */
				OnHtttpResponseListenerForSaveHistory login = (OnHtttpResponseListenerForSaveHistory) activity;
				login.onHttpSuccessfulSaveHistory(response,JsonParser.getInstance().checkresponsestatus(response),response,JsonParserForMyHistory.getNewInstance().getMyHistory(
						response),profile,diagNosticModel,addedMedicinesList);
			}

		};
		HttpRequestHelper.post(activity, params, handler, "api/save-history");
	}
}
