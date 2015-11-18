package com.mobtecnica.medirect.doctor.ayncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForDiagnostics;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParserForMyHistory;
import com.mobtecnica.medirect.docter.models.DiagnosticsTypeModel;
import com.mobtecnica.medirect.docter.models.MyHistoryModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.utils.Config;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class LoadMyHistory extends AsyncTask<Void, Void, MyHistoryModel> {

	private ProgressDialog progress;
	private Activity activity;
	private String patientId;
	
	public LoadMyHistory(Activity activity,String patientId) {
		this.activity = activity;
		this.patientId = patientId;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progress = ProgressDialog.show(activity, "Loading", "Loading...", true,
				true, new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});
	}
	@Override
	protected MyHistoryModel doInBackground(Void... params) {
		// TODO Auto-generated method stub
		HttpPost httppost = null;
		MyHistoryModel resultArray = null;
		

		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		httppost = new HttpPost(HttpRequestHelper.BASE_URL_GET_MY_HISTORY);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("id",patientId));

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
				resultArray = JsonParserForMyHistory.getNewInstance().getMyHistory(
						result);
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
		return resultArray;
	}
	@Override
	protected void onPostExecute(MyHistoryModel result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		if (progress != null) {
			if (progress.isShowing()) {
				progress.cancel();
				progress.dismiss();
			}
		}
	}
}
