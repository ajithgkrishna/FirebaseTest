package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;







import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailViewModelnew;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PrescriptionDetailAdapterNew extends BaseAdapter {

	Activity context;
	ArrayList<PrescriptionDetailViewModelnew> prescriptionDetailList;
	boolean all_visible;
	boolean doctor_notes_visible;
	boolean medicines_visible;
	boolean diagnosis_visible;

	public PrescriptionDetailAdapterNew(Activity activity,
			ArrayList<PrescriptionDetailViewModelnew> patModel,
			boolean all_visible, boolean doctor_notes_visible,
			boolean medicines_visible, boolean diagnosis_visible) {
		// TODO Auto-generated constructor stub
		context = activity;
		this.prescriptionDetailList = patModel;
		this.all_visible = all_visible;
		this.doctor_notes_visible = doctor_notes_visible;
		this.medicines_visible = medicines_visible;
		this.diagnosis_visible = diagnosis_visible;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return prescriptionDetailList.size();
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
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.prescription_itemnew, null);
			holder = new ViewHolder();
			holder.textViewDate = (TextView) convertView
					.findViewById(R.id.textViewDate);
			holder.textViewDoctorName = (TextView) convertView
					.findViewById(R.id.textViewDoctorName);
			holder.buttonOrderThis = (Button) convertView
					.findViewById(R.id.buttonOrderThis);
			holder.textViewSymptomsValue = (TextView) convertView
					.findViewById(R.id.textViewSymptomsValue);
			holder.textViewDiagnosisValue = (TextView) convertView
					.findViewById(R.id.textViewDiagnosisValue);
			holder.listViewMedicines = (ListView) convertView
					.findViewById(R.id.listViewMedicines);
			holder.listViewDiagnostics = (ListView) convertView
					.findViewById(R.id.listViewDiagnostics);

			holder.diagnosishead = (TextView) convertView
					.findViewById(R.id.diagnosishead);
			holder.SymptomsHead = (TextView) convertView
					.findViewById(R.id.SymptomsHead);
			holder.txtDoctorNotesHead = (TextView) convertView
					.findViewById(R.id.txtDoctorNotesHead);
			holder.medicinesHead = (TextView) convertView
					.findViewById(R.id.medicinesHead);
			holder.DiagnosticsHead = (TextView) convertView
					.findViewById(R.id.DiagnosticsHead);
			holder.DiagnosticsHeadLayout = (LinearLayout) convertView
					.findViewById(R.id.DiagnosticsHeadLayout);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionDetailList.size() <= 0) {
			// ToDo Add no data view
		} else {
			PrescriptionDetailViewModelnew prescr = prescriptionDetailList
					.get(position);
			holder.textViewDate.setText("Doctor: \n" + prescr.getDoctor_name());
			holder.textViewDoctorName
					.setText("Date: \n" + prescr.getAdded_on());
			// holder.buttonOrderThis
			holder.diagnosishead.setVisibility(View.VISIBLE);
			holder.SymptomsHead.setVisibility(View.VISIBLE);
			holder.txtDoctorNotesHead.setVisibility(View.VISIBLE);
			holder.medicinesHead.setVisibility(View.VISIBLE);
			holder.DiagnosticsHead.setVisibility(View.VISIBLE);
			holder.textViewSymptomsValue.setVisibility(View.VISIBLE);
			holder.textViewDiagnosisValue.setVisibility(View.VISIBLE);
			holder.listViewMedicines.setVisibility(View.VISIBLE);
			holder.listViewDiagnostics.setVisibility(View.VISIBLE);
			holder.DiagnosticsHeadLayout.setVisibility(View.VISIBLE);
			if (all_visible == true && doctor_notes_visible == true) {
				holder.textViewSymptomsValue.setText(prescr.getSymptoms());
				holder.textViewDiagnosisValue.setText(prescr.getDiagnosis());
			} else if (all_visible == true && doctor_notes_visible == false) {
				holder.textViewSymptomsValue.setVisibility(View.GONE);
				holder.textViewDiagnosisValue.setVisibility(View.GONE);
				holder.diagnosishead.setVisibility(View.GONE);
				holder.SymptomsHead.setVisibility(View.GONE);
				holder.txtDoctorNotesHead.setVisibility(View.GONE);
			}

			if (all_visible == true && medicines_visible == true) {
				MedicinsListAdapterInsidePrescriptionDetail prescriptionDetail = new MedicinsListAdapterInsidePrescriptionDetail(
						context, prescriptionDetailList.get(position)
								.getMedicinesArray());
				if (prescriptionDetailList.get(position).getMedicinesArray()
						.isEmpty()) {
					holder.medicinesHead.setVisibility(View.GONE);
				} else {
					holder.medicinesHead.setVisibility(View.VISIBLE);
					holder.listViewMedicines.setAdapter(prescriptionDetail);
					Utilities.getInstance(context).setListViewHeightBasedOnChildren(
									holder.listViewMedicines);
				}
			
			} else if (all_visible == true && medicines_visible == false) {
				holder.listViewMedicines.setVisibility(View.GONE);
				holder.medicinesHead.setVisibility(View.GONE);
			}
			if (all_visible == true && diagnosis_visible == true) {
				DiagnosticListAdapterInsidePrescriptionDetail diagnosticDetail = new DiagnosticListAdapterInsidePrescriptionDetail(
						context, prescriptionDetailList.get(position)
								.getDiagnosticArray());
				
				if (prescriptionDetailList.get(position).getDiagnosticArray()
						.isEmpty()) {
					holder.DiagnosticsHead.setVisibility(View.GONE);
					holder.DiagnosticsHeadLayout.setVisibility(View.GONE);
				} else {
					holder.DiagnosticsHead.setVisibility(View.VISIBLE);
					holder.DiagnosticsHeadLayout.setVisibility(View.VISIBLE);
					holder.listViewDiagnostics.setAdapter(diagnosticDetail);
					Utilities.getInstance(context)
					.setListViewHeightBasedOnChildren(
							holder.listViewDiagnostics);
				}
				
			} else if (all_visible == true && diagnosis_visible == false) {
				holder.listViewDiagnostics.setVisibility(View.GONE);
				holder.DiagnosticsHead.setVisibility(View.GONE);
				holder.DiagnosticsHeadLayout.setVisibility(View.GONE);

			}
		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView textViewDate;
		public TextView textViewDoctorName;
		public Button buttonOrderThis;
		public TextView textViewSymptomsValue;
		public TextView textViewDiagnosisValue;
		public ListView listViewMedicines;
		public ListView listViewDiagnostics;
		public TextView medicinesHead;
		public TextView diagnosishead;
		public TextView SymptomsHead;
		public TextView txtDoctorNotesHead;
		public TextView DiagnosticsHead;
		public LinearLayout DiagnosticsHeadLayout;

	}
}
