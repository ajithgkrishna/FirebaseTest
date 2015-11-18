package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.AllergiMedicineModel;
import com.mobtecnica.medirect.docter.models.MedicinsListModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddedAlergiesAdapter extends BaseAdapter {

	
	Context context;
	ArrayList<AllergiMedicineModel> prescriptionMedicineModel;

	public AddedAlergiesAdapter(Context activity,
			ArrayList<AllergiMedicineModel> patModel) {
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

			convertView = inflater.inflate(R.layout.added_alergy_adapter, null);
			holder = new ViewHolder();
			holder.medicineName = (TextView) convertView
					.findViewById(R.id.txtMedicineName);
			
			holder.imgDelete = (TextView) convertView
					.findViewById(R.id.imgDelete);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (prescriptionMedicineModel.size() <= 0) {
			// ToDo Add no data view
		} else {
			AllergiMedicineModel medicine = prescriptionMedicineModel.get(position);
			holder.medicineName.setText(medicine.getMedicine_name());
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

	public static class ViewHolder {

		public TextView medicineName;

		public TextView imgDelete;

	}
}
