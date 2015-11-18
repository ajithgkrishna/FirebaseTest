package com.mobtecnica.medirect.docter.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/****
 * Adapter for showing appointment adapter in home page.
 * @author Diljith
 *
 */
public class HomeAppoinmentAdapter extends BaseAdapter {

	private Activity activity;

	private ArrayList<Appointments> appointmentList;
	public HomeAppoinmentAdapter(Activity activity,
			ArrayList<Appointments> appointmentList) {
		super();
		this.activity = activity;
		this.appointmentList = appointmentList;
		// inflater = (LayoutInflater) activity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return appointmentList.size();
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
		// create views
		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.appointemnt_home_adapter,
					null);
			holder = new ViewHolder();
			holder.time = (TextView) convertView
					.findViewById(R.id.txtAppointmentTime); 
			holder.patientId = (TextView) convertView
					.findViewById(R.id.txtAppointmentPatientId); 
			holder.name = (TextView) convertView
					.findViewById(R.id.txtAppointmentName); 
			holder.lastVisit = (TextView) convertView
					.findViewById(R.id.txtAppointmentLastVisit); 
			holder.age = (TextView) convertView
					.findViewById(R.id.txtAppointmentAge); 
			holder.gender = (TextView) convertView
					.findViewById(R.id.txtAppointmentGender); 
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		if (appointmentList.size() <= 0) {
			
		} else {
			final Appointments appo_item = appointmentList.get(position);
			SimpleDateFormat f1 = new SimpleDateFormat("HH:mm:ss");
			String date = appo_item.getDatetime().toString().split(" ")[1];
			try {
				Date d = f1.parse(date);
				SimpleDateFormat f2 = new SimpleDateFormat("h:mma");
				date =  f2.format(d);
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			holder.time.setText(date);
			holder.patientId.setText(appo_item.getPatient_id());
			holder.name.setText(appo_item.getPatient_name());
			holder.lastVisit.setText(appo_item.getLastVisit());
			holder.age.setText(appo_item.getAge());
			holder.gender.setText(appo_item.getGender());
		}
		return convertView;
	}
	public static class ViewHolder {

		private TextView time;
		private TextView patientId;
		private TextView name;
		private TextView lastVisit;
		private TextView age;
		private TextView gender;

	}
}
