package com.mobtecnica.medirect.docter.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.dateSlider.labeler.HourLabeler;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.utils.Config;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class AppointmentsHomeAdapter extends BaseAdapter {

	private Activity activity;

	ArrayList<Appointments> appointments_model;
	int count = 0;


	public AppointmentsHomeAdapter(Activity activity,
			ArrayList<Appointments> appointments_model_) {
		super();
		this.activity = activity;
		this.appointments_model = appointments_model_;
	
		// inflater = (LayoutInflater) activity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return appointments_model.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		// create views
		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.appintment_list_home_item,
					null);
			holder = new ViewHolder();

			holder.patientName = (TextView) convertView
					.findViewById(R.id.txtPatientName); // title

			holder.status = (TextView) convertView.findViewById(R.id.txtStatus);
			holder.time = (TextView) convertView.findViewById(R.id.txtTime);
			holder.date = (TextView) convertView.findViewById(R.id.txtDate);
			/*holder.linearButtons = (RelativeLayout) convertView
					.findViewById(R.id.linearButtons);
			holder.cancel = (TextView) convertView.findViewById(R.id.txtCancel);
			holder.reshedule = (TextView) convertView
					.findViewById(R.id.txtReschedule);
			holder.confirm = (TextView) convertView
					.findViewById(R.id.txtConfirm);*/
			holder.note = (TextView) convertView.findViewById(R.id.txtNote);
			

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (appointments_model.size() <= 0) {
			holder.patientName.setText("No Patients Added ");

		} else {

			final Appointments appo_item = appointments_model.get(position);
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
			
			holder.patientName.setText(appo_item.getPatient_name());
			holder.status.setText(appo_item.getAppointment_status());
			holder.date
					.setText(appo_item.getDatetime().toString().split(" ")[0]);
			holder.time.setText(date);
			holder.note.setText("Note :"+ appo_item.getNote());
			/*if (appo_item.getAppointment_status_id().equals("1")) {
				holder.linearButtons.setVisibility(View.VISIBLE);
			} else {
				holder.linearButtons.setVisibility(View.GONE);
			}*/
			/* holder.appointmentTime.setText(); */
			/*holder.cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					HttpRequestHelper.updateAppoinment(
							activity,
							activity.getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""),
							appo_item.getId(), "3", date, position);
				}
			});*/

			/*holder.reshedule.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					new DateTimeDialog(activity, position, appo_item.getId())
							.show();
				}
			});
			holder.confirm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					HttpRequestHelper.updateAppoinment(
							activity,
							activity.getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""),
							appo_item.getId(), "2", date, position);
				}
			});*/

		}

		return convertView;
	}

	/****
	 * 
	 * datetime Dialog class
	 * 
	 *
	 */

	private class DateTimeDialog extends Dialog implements
			android.view.View.OnClickListener {
		
		DatePicker datePicker;
		TimePicker tp;
		int position;
		String itemId;

		public DateTimeDialog(Activity context, int position, String ItemIdd) {
			super(context);
			// TODO Auto-generated constructor stub
			this.position = position;
			this.itemId = ItemIdd;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.date_time_dialog);
			tp = (TimePicker) findViewById(R.id.timePicker1);
			datePicker = (DatePicker) findViewById(R.id.datePicker1);
			Button btnset = (Button) findViewById(R.id.btnSet);
			btnset.setOnClickListener(this);
			Button btnCancel = (Button) findViewById(R.id.btnCancel);
			btnCancel.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnCancel) {
				dismiss();
			}
			if (v.getId() == R.id.btnSet) {
				/***
				 * get date
				 */
				int year = datePicker.getYear();
				int month = datePicker.getMonth() + 1;
				int day = datePicker.getDayOfMonth();
				String datePicked = year + "-" + month + "-" + day;
				Config.LogError("Date", datePicked);
				/***
				 * get time
				 */
				String time = tp.getCurrentHour() + ":" + tp.getCurrentMinute()
						+ ":00";
				Config.LogError("Time", time);
				String dateTime = datePicked + " " + time;
				Config.LogError("dateTime", dateTime);
				/***
				 * format date
				 */

				SimpleDateFormat srcDf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat srcDftest = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				try {
					/* srcDftest.parse(dateTime); */
					Date testdate = srcDftest.parse(dateTime);
					dateTime = srcDf.format(testdate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Config.LogError("dateTimeFormated", dateTime);
				HttpRequestHelper.updateAppoinment(
						activity,
						activity.getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""), itemId, "4",
						dateTime.trim(), position);
				dismiss();

			}
		}

	}

	/*
	 * private OnDateSetListener mDateSetListener = new OnDateSetListener() {
	 * public void onDateSet(DatePicker view, int year, int monthOfYear, int
	 * dayOfMonth) { new TimePickerDialog(activity, callBack, hourOfDay, minute,
	 * is24HourView) int month = monthOfYear + 1; datePicked = year +"-" + month
	 * +"-"+dayOfMonth; new TimePickerDialog(activity, mTimeListner,
	 * Calendar.HOUR_OF_DAY, Calendar.MINUTE, false).show();;
	 * Toast.makeText(activity, date, Toast.LENGTH_LONG).show(); } };
	 */

	public static class ViewHolder {

		private TextView patientName;
		private TextView status;
		private TextView time;
		private TextView date;
	/*	private TextView cancel;
		private TextView reshedule;
		private TextView confirm;*/
		private TextView note;
/*		private RelativeLayout linearButtons;*/

	}
}