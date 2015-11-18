package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;





import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.MedicineModelinPrescriptionDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MedicinsListAdapterInsidePrescriptionDetail extends BaseAdapter {

	Context context;
	ArrayList<MedicineModelinPrescriptionDetail> prescriptionMedicineModel;

	public MedicinsListAdapterInsidePrescriptionDetail(Context activity,
			ArrayList<MedicineModelinPrescriptionDetail> patModel) {
		// TODO Auto-generated constructor stub
		this.context = activity;
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

			convertView = inflater.inflate(R.layout.medicines_item_pres_det, null);
			holder = new ViewHolder();

			holder.textViewMedicineName = (TextView) convertView
					.findViewById(R.id.txtMedicineName);
			holder.textViewtiming = (TextView) convertView
					.findViewById(R.id.txtMedicineDosage);
			holder.textViewfoodPreference = (TextView) convertView
					.findViewById(R.id.textViewfoodPreference);
			holder.textViewtotalDays = (TextView) convertView
					.findViewById(R.id.txtNoOfDays);
			holder.textViewRefill = (TextView) convertView
					.findViewById(R.id.txtRefill);
			holder.textViewNotes = (TextView) convertView
					.findViewById(R.id.txtDialogNotes);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionMedicineModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			MedicineModelinPrescriptionDetail medicine = prescriptionMedicineModel
					.get(position);
			holder.textViewMedicineName.setText(medicine.getMedicine_name()+"("+medicine.getMedicine_unit()+")");
			holder.textViewtiming.setText(medicine.getTimings());
			holder.textViewfoodPreference
					.setText(medicine.getFood_preference());
			holder.textViewtotalDays.setText("Days: "+medicine.getTotal_day());
			holder.textViewRefill.setText("Refill: "+medicine.getRefill_number());
			holder.textViewNotes.setText("Note: "+medicine.getNotes());
		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView textViewMedicineName;
		public TextView textViewtiming;
		public TextView textViewfoodPreference;
		public TextView textViewtotalDays;
		public TextView textViewRefill;
		public TextView textViewNotes;

	}
}
