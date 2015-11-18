package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.PatientModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PatientListAdapter extends BaseAdapter {

	private Activity activity;

	ArrayList<PatientModel> pati_model;
	int count = 0;

	public PatientListAdapter(Activity activity,
			ArrayList<PatientModel> pati_model_) {
		super();
		this.activity = activity;
		this.pati_model = pati_model_;
		// inflater = (LayoutInflater) activity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pati_model.size();
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

	/**
	 * getView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		// create views
		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.patient_list_item, null);
			holder = new ViewHolder();

			holder.patientId = (TextView) convertView
					.findViewById(R.id.textViewPatientId); // title

			holder.patientName = (TextView) convertView
					.findViewById(R.id.textViewPatientName);
			holder.place = (TextView) convertView
					.findViewById(R.id.textViewPlace);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (pati_model.size() <= 0) {
			holder.patientId.setText("No Patients Added ");

		} else {

			PatientModel pat_item = pati_model.get(position);
			holder.patientId.setText(pat_item.getPatientId());
			holder.patientName.setText(pat_item.getPatientname());
			holder.place.setText(pat_item.getPlace());
		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView patientId;
		public TextView patientName;
		public TextView place;

	}
}