package com.mobtecnica.medirect.doctor.ayncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.spec.OAEPParameterSpec;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import com.loopj.android.http.RequestParams;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerForPaymentMethods;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforPurchaseOrder;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPatient;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.models.PaymentMethodModels;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;
import com.mobtecnica.medirect.docter.utils.Config;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class LoadPaymentMethodsAsyc extends AsyncTask<Void, Void, ArrayList<PaymentMethodModels>> {
/*	private ProgressDialog progress;*/
	private Activity activity;
	

	public LoadPaymentMethodsAsyc(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	/*	progress = ProgressDialog.show(activity, "Loading", "Loading...", true,
				true, new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});*/
	}

	@Override
	protected ArrayList<PaymentMethodModels> doInBackground(Void... presId) {

		HttpPost httppost = null;
		ArrayList<PaymentMethodModels> paymentMethods = new ArrayList<PaymentMethodModels>();

		/* public void uploadPatientProfile(PatientAddModel profile) { */
		ArrayList<PurchaseOrderModel> purchaseModel = new ArrayList<PurchaseOrderModel>();

		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		httppost = new HttpPost(HttpRequestHelper.BASE_URL_PAYMENT_METHODS);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("id", activity
				.getSharedPreferences(LoginActivity.PREFS_LOGIN_STATUS,
						Context.MODE_PRIVATE).getString(
						LoginActivity.PREFS_USERID, "")));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			StringBuilder builder = new StringBuilder();
			if (resEntity != null) {

				InputStream content = resEntity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);

				}
				result = builder.toString();
				paymentMethods = JsonParser.getInstance().getAllPaymentMethods(
						result);
				/*
				 * purchaseModel =
				 * JsonParser.getInstance().getAllPurchaseOrderItems( result);
				 */
			}

			Config.LogError("RESPONSE", builder.toString());
			/* Log.e("RESPONSE", builder.toString()); */

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Config.LogError("ERROR2", e + "");
			/* Log.e("ERROR2", e + ""); */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Config.LogError("ERROR3", e + "");
			/* Log.e("ERROR3", e + ""); */
		}
		return paymentMethods;
	}

	@Override
	protected void onPostExecute(ArrayList<PaymentMethodModels> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		/*if (progress != null) {
			if (progress.isShowing()) {
				progress.cancel();
				progress.dismiss();
			}
		}*/
	}
}
