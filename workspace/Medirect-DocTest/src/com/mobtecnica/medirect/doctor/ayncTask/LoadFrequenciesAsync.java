package com.mobtecnica.medirect.doctor.ayncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.FrequencyModel;
import com.mobtecnica.medirect.docter.utils.Config;

public class LoadFrequenciesAsync extends
		AsyncTask<Void, Void, ArrayList<FrequencyModel>> {
	/*private ProgressDialog progress;*/
	private Activity activity;

	public LoadFrequenciesAsync(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		/*progress = ProgressDialog.show(activity, "Loading", "Loading...", true,
				true, new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});*/
	}

	@Override
	protected ArrayList<FrequencyModel> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		HttpPost httppost;

		/* public void uploadPatientProfile(PatientAddModel profile) { */
		ArrayList<FrequencyModel> frequencyModel = new ArrayList<FrequencyModel>();

		String result = "";
		HttpClient httpclient = new DefaultHttpClient();

		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		httppost = new HttpPost(HttpRequestHelper.BASE_URL_MEDICINE_FREQUENCIES);
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
				frequencyModel = JsonParser.getInstance().getAllFrequencies(
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
		return frequencyModel;
	}

	@Override
	protected void onPostExecute(ArrayList<FrequencyModel> result) {
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
