package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConfirmPrescriptionAdapter extends BaseAdapter {

	Context context;
	ArrayList<MedicinsModel> prescriptionMedicineModel;

	public ConfirmPrescriptionAdapter(Context activity,
			ArrayList<MedicinsModel> patModel) {
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
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(
					R.layout.dialog_confirmprescription_list, null);
			holder = new ViewHolder();
			holder.medicineName = (TextView) convertView
					.findViewById(R.id.txtDialogMedicineName);
			holder.medicinePerDose = (TextView) convertView
					.findViewById(R.id.txtDialogMedicineDose);
			holder.dosePerDay = (TextView) convertView
					.findViewById(R.id.txtDialogDose);
			holder.noOfDays = (TextView) convertView
					.findViewById(R.id.txtDialogDays);
			holder.refill = (TextView) convertView
					.findViewById(R.id.txtDialogRefill);
			holder.notes = (TextView) convertView
					.findViewById(R.id.txtDialogNotes);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionMedicineModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			MedicinsModel medicine = prescriptionMedicineModel.get(position);
			holder.medicineName.setText(medicine.medicine_name);
			holder.medicinePerDose.setText(medicine.medicines_per_dose);
			holder.dosePerDay.setText(medicine.dose_per_day+ "/"
					+ medicine.getFrequencyName() + "(" + AddNewPrescriptionAdapter.addFoodSelected(medicine)+ medicine.after_food + ")");
			holder.noOfDays.setText(medicine.total_day);
			holder.refill.setText(medicine.refill_number);
			holder.notes.setText(medicine.notes);

		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView medicineName;
		public TextView medicinePerDose;
		public TextView dosePerDay;
		public TextView noOfDays;
		public TextView refill;
		public TextView notes;

	}

}
