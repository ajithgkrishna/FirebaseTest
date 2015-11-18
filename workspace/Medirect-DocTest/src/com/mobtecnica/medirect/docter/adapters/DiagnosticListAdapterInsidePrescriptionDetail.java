package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;







import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.interfaces.OnHttpResponseListenerforSaveDiagnosticResult;
import com.mobtecnica.medirect.docter.models.DiagnosticModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DiagnosticListAdapterInsidePrescriptionDetail extends BaseAdapter
		 {

	Activity context;
	ArrayList<DiagnosticModel> prescriptionDiagnosticsModel;
	DiagnosticModel diagnostics;

	public DiagnosticListAdapterInsidePrescriptionDetail(Activity activity,
			ArrayList<DiagnosticModel> patModel) {
		// TODO Auto-generated constructor stub
		this.context = activity;
		this.prescriptionDiagnosticsModel = patModel;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return prescriptionDiagnosticsModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.diagnostic_item_pres_det,
					null);
			holder = new ViewHolder();

			holder.diagnosticType = (TextView) convertView
					.findViewById(R.id.txtDiagnostictype);
			holder.TestName = (TextView) convertView
					.findViewById(R.id.txtDiagnosticTestName);
			holder.Sample = (TextView) convertView.findViewById(R.id.txtDiagnosticSample);
			holder.ResultValue = (TextView) convertView
					.findViewById(R.id.txtDiagnosticResult);
			holder.ResultValueEnter = (EditText) convertView
					.findViewById(R.id.ResultValueEnter);
			holder.buttonSubmitDiagnosis = (ImageButton) convertView
					.findViewById(R.id.buttonSubmitDiagnosis);

			holder.DiagnosticValue = (TextView) convertView
					.findViewById(R.id.txtValue);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionDiagnosticsModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			diagnostics = prescriptionDiagnosticsModel.get(position);
			holder.diagnosticType.setText(diagnostics.getDiagnostic_type());
			holder.TestName.setText(diagnostics.getTest_name());
			holder.Sample.setText(diagnostics.getSample());
			if (!diagnostics.getResult().equalsIgnoreCase("")) {
				holder.ResultValue.setText("Result : " +diagnostics.getResult());
				holder.ResultValue.setVisibility(View.VISIBLE);
				holder.ResultValueEnter.setVisibility(View.GONE);
				holder.buttonSubmitDiagnosis.setVisibility(View.GONE);
			} else {
				holder.ResultValue.setVisibility(View.VISIBLE);
				holder.ResultValue.setText("Result :");
				holder.ResultValueEnter.setVisibility(View.VISIBLE);
				holder.buttonSubmitDiagnosis.setVisibility(View.VISIBLE);
			}
			holder.DiagnosticValue.setText(diagnostics.getValue());
		}
		holder.buttonSubmitDiagnosis
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

					/*	HttpRequestHelperForSaveDiagnosisResult
								.SaveDiagnosisResult(context, diagnostics
										.getDiagnostic_id(),
										holder.ResultValueEnter.getText()
												.toString(), holder);*/

					}
				});
		return convertView;
	}

	public static class ViewHolder {

		public TextView diagnosticType;
		public TextView TestName;
		public TextView Sample;
		public TextView ResultValue;
		public EditText ResultValueEnter;
		public ImageButton buttonSubmitDiagnosis;
		public TextView DiagnosticValue;

	}

	/*@Override
	public void onHttpSuccessfulResponseSaveDiagnosticResult(String response,
			boolean responseStatus, String responseResultMsg, ViewHolder holder) {
		holder.ResultValue.setVisibility(View.VISIBLE);
		holder.ResultValueEnter.setVisibility(View.GONE);
		holder.buttonSubmitDiagnosis.setVisibility(View.GONE);
		holder.ResultValue
				.setText(holder.ResultValueEnter.getText().toString());

	}

	@Override
	public void onHttpFailedResponseSaveDiagnosticResult(Throwable throwable,
			String response, boolean resposeStatus, String responseResultMessage) {
		if (resposeStatus) {
			Config.showToast("Request Failed, Please Try again later !",
					context);
		} else {
			Config.showToast("Request Failed, Please Try again later !",
					context);
		}

	}*/
}
