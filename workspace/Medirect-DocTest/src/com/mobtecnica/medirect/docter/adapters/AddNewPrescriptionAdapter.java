package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import org.apache.http.util.TextUtils;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.PatientListHomeAdapter.ViewHolder;
import com.mobtecnica.medirect.docter.models.MedicinsModel;
import com.mobtecnica.medirect.docter.models.PatientModel;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewPrescriptionAdapter extends BaseAdapter {

	Context context;
	ArrayList<MedicinsModel> prescriptionMedicineModel;

	public AddNewPrescriptionAdapter(Context activity,
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.medicine_list_item, null);
			holder = new ViewHolder();
			holder.medicineName = (TextView) convertView
					.findViewById(R.id.txtMedicineName);

			holder.medicineFrequency = (TextView) convertView
					.findViewById(R.id.txtMedicineDosage);
			holder.noOfDays = (TextView) convertView
					.findViewById(R.id.txtNoOfDays);
			holder.refill = (TextView) convertView.findViewById(R.id.txtRefill);
			holder.notes = (TextView) convertView.findViewById(R.id.txtDialogNotes);
			holder.imgDelete = (TextView) convertView
					.findViewById(R.id.txtDelete);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionMedicineModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			MedicinsModel medicine = prescriptionMedicineModel.get(position);
			holder.medicineName.setText(medicine.medicine_name);
			holder.medicineName.setSelected(true);
			holder.medicineFrequency.setText(/*medicine.dose_per_day+ "/"*/
					/*+*/ medicine.getFrequencyName() + "(" + addFoodSelected(medicine)/*+ medicine.after_food + */+")");
			holder.noOfDays.setText(medicine.total_day);
			holder.refill.setText(medicine.refill_number);
			holder.notes.setText(medicine.getNotes());
		}

		holder.imgDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				prescriptionMedicineModel.remove(position);
				notifyDataSetChanged();
			}
		});

		return convertView;
	}
	
	public static String addFoodSelected(MedicinsModel model){
		String addedDetail= "";
		if (model.medicines_per_dose.isEmpty()) {
			if (!TextUtils.isEmpty(model.mornig)) {
				addedDetail = "M: " +model.getMornig() + " ," ;
			}
			if (!TextUtils.isEmpty(model.afterNoon)) {
				addedDetail = addedDetail + "A:"+ model.getAfterNoon()  + " ," ;
			}
			if (!TextUtils.isEmpty(model.evening)) {
				addedDetail = addedDetail+ "E:" +model.getEvening()  + " ,"  ;
			}
			if (!TextUtils.isEmpty(model.night)) {
				addedDetail = addedDetail + "N:"+ model.getNight();
			}
			if (addedDetail.endsWith(",")) {
				addedDetail = addedDetail.substring(0 , addedDetail.length() - 1);
			}
			/*if (!TextUtils.isEmpty(addedDetail)) {
				addedDetail =  addedDetail + " - " ;
			}*/
		}else {
			addedDetail = model.medicines_per_dose;
		}
		
		return addedDetail;
	}

	public static class ViewHolder {

		public TextView medicineName;
		public TextView medicineFrequency;
		public TextView noOfDays;
		public TextView refill;
		public TextView notes;
		public TextView imgDelete;

	}

}
