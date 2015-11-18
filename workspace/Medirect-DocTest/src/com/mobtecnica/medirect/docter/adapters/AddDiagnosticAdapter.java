package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.DiagnosticsModelForAddPrescription;
import com.mobtecnica.medirect.docter.models.DiagnosticsTestModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AddDiagnosticAdapter extends BaseAdapter {
	Context context;
	ArrayList<DiagnosticsModelForAddPrescription> prescriptionMedicineModel;

	public AddDiagnosticAdapter(Context activity,
			ArrayList<DiagnosticsModelForAddPrescription> patModel) {
		// TODO Auto-generated constructor stub
		context = activity;
		this.prescriptionMedicineModel = patModel;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return prescriptionMedicineModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.diagnostic_list_item, null);
			holder = new ViewHolder();
			holder.type = (TextView) convertView
					.findViewById(R.id.txtType);
			holder.testname = (TextView) convertView
					.findViewById(R.id.txtTextName);
			holder.sample = (TextView) convertView
					.findViewById(R.id.txtSample);
			holder.Delete = (TextView) convertView
					.findViewById(R.id.txtDelete);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionMedicineModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			DiagnosticsModelForAddPrescription medicine = prescriptionMedicineModel.get(position);
			holder.type.setText(medicine.getDiagnosticsType());
			
			holder.testname.setText(medicine.getDiagnosticsTestName());
			holder.sample.setText(medicine.getSample());
			
		}

		holder.Delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				prescriptionMedicineModel.remove(position);
				notifyDataSetChanged();
			}
		});
		return convertView;
	}
	
	public static class ViewHolder {

		public TextView type;
		public TextView testname;
		public TextView sample;
		public TextView Delete;

	}
}
