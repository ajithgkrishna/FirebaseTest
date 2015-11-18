package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PatientListHomeAdapter extends BaseAdapter {

	private Activity activity;

	private ArrayList<Profile_Model> profileList;
	int count = 0;

	public PatientListHomeAdapter(Activity activity,
			ArrayList<Profile_Model> profileList) {
		super();
		this.activity = activity;
		this.profileList = profileList;
		// inflater = (LayoutInflater) activity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return profileList.size();
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

			convertView = inflater.inflate(R.layout.patient_list_home_item,
					null);
			holder = new ViewHolder();

			holder.patientId = (TextView) convertView
					.findViewById(R.id.txtPatientListId); // title

			holder.name = (TextView) convertView
					.findViewById(R.id.txtPatientListName);
			holder.lastVisit = (TextView) convertView
					.findViewById(R.id.txtPatientListLastVisit);
			holder.age = (TextView) convertView
					.findViewById(R.id.txtPatientListAge);
			holder.gender = (TextView) convertView
					.findViewById(R.id.txtPatientListGender);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (profileList.size() <= 0) {
			Toast.makeText(activity, "No Patients Added ", Toast.LENGTH_SHORT).show();
		/*	holder.patientname.setText("No Patients Added ");*/

		} else {

			Profile_Model recentPrescript = profileList
					.get(position);
			holder.patientId.setText(recentPrescript.getAccount_no());

			holder.name.setText(recentPrescript.getFirst_name() + " " +recentPrescript.getLast_name() );
			holder.lastVisit.setText(recentPrescript.getLastVisit());
			holder.age.setText(recentPrescript.getAge());
			holder.gender.setText(recentPrescript.getGender());
		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView patientId;
		public TextView name;
		public TextView lastVisit;
		public TextView age;
		public TextView gender;
	}
}