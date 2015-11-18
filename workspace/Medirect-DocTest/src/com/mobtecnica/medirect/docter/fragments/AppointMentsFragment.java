package com.mobtecnica.medirect.docter.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.AppointmentsHomeAdapter;
import com.mobtecnica.medirect.docter.adapters.CustomArrayAdapter;
import com.mobtecnica.medirect.docter.adapters.CustomData;
import com.mobtecnica.medirect.docter.adapters.InboxMessageViewAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Appointments;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.mobtecnica.medirect.docter.widgets.dateSlider.SliderContainer;
import com.mobtecnica.medirect.docter.widgets.dateSlider.SliderContainer.OnTimeChangeListener;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AppointMentsFragment extends Fragment {
	private static String AppointmentListTag = "AppointmentList";
	private static String AppointmentListDateTag = "AppointmentListDate";
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;

	static final int DEFAULTDATESELECTOR_ID = 0;
	protected Calendar mInitialTime, minTime, maxTime;
	private TextView dateText;
/*	SliderContainer mContainer;*/
	TextView textViewMonthAndYear;
	TextView textViewAppointmentDate;
	ListView listViewAppointmentList;
	public ArrayList<Appointments> appointments_list = new ArrayList<Appointments>();
	public AppointmentsHomeAdapter appointmentsHomeAdapter;
	protected int minuteInterval;
	private final String PREFS_LOGIN_STATUS = "LOGIN";
	public AppointmentsHomeAdapter appoAdapter;
	private String date;
	int mYear;
	int mMonth;
	int mDay;
	TextView editTextFromDate,menuname;
	TextView editTextToDate;
	Button buttonSearch;

	public  static AppointMentsFragment newInstanceOFAppointMentsFragment(ArrayList<Appointments> appointments_list) {
		AppointMentsFragment fragment = new AppointMentsFragment();
		Bundle bundle= new Bundle();
		bundle.putParcelableArrayList(AppointmentListTag, appointments_list);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.fragment_appointments_fragment, container, false);
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_prescriptions));
		initializeViews(rootView);
		initializeListeners(rootView);
		setDateSlider();
		buildUI();
		updateDisplayFromdate();
		updateDisplayTodate();
		return rootView;
	}

	private void buildUI() {
		Bundle b = getArguments();
		if (b != null) {
			appointments_list = b.getParcelableArrayList(AppointmentListTag);
			date = b.getString(AppointmentListDateTag);
			addDataToListView();
		}
	}

	private void setDateSlider() {
		Calendar initialTime = Calendar.getInstance();
		mInitialTime = Calendar.getInstance(initialTime.getTimeZone());
		mInitialTime.setTimeInMillis(initialTime.getTimeInMillis());
/*
		mContainer.setOnTimeChangeListener(onTimeChangeListener);

		mContainer.setMinuteInterval(minuteInterval);
		mContainer.setTime(mInitialTime);
*/
	}

	private void initializeViews(View rootView) {
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_appointments));
		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		textViewMonthAndYear = (TextView) rootView
				.findViewById(R.id.textViewMonthAndYear);
	/*	mContainer = (SliderContainer) rootView
				.findViewById(R.id.dateSliderContainer);*/
		textViewAppointmentDate = (TextView) rootView
				.findViewById(R.id.textViewAppointmentDate);
		listViewAppointmentList = (ListView) rootView
				.findViewById(R.id.listViewAppointmentList);
		editTextFromDate = (TextView) rootView.findViewById(R.id.txtFromDate);
		editTextToDate = (TextView) rootView.findViewById(R.id.txtToDate);
		buttonSearch = (Button) rootView.findViewById(R.id.btnSearchAppointment);

	}

	private void initializeListeners(View rootView) {
		// imageButtonwallet_nav.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		imageViewuser_nav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getActivity()
						.getSharedPreferences(PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				Toast.makeText(getActivity(),
						"You have been Successfully Logged out !",
						Toast.LENGTH_SHORT).show();
				Intent dashBoardIntent = new Intent(getActivity(),
						LoginActivity.class);
				startActivity(dashBoardIntent);
				getActivity().finish();

			}
		});
		listViewAppointmentList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (!appointments_list.isEmpty()) {
							if (appointments_list.get(position)
									.getAppointment_status_id().equals("1")) {
								new AppointmentAction(getActivity(),
										appointments_list.get(position),
										position).show();
							}
						}
					}
				});
		editTextFromDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(getActivity(),
						mDateSetListenerFromDate, mYear, mMonth, mDay)
						.show();
			}
		});
		editTextToDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(getActivity(),
						mDateSetListenerToDate, mYear, mMonth, mDay)
						.show();
			}
		});
		((Button) rootView.findViewById(R.id.btnSearchAppointment)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					date = new SimpleDateFormat("yyyy-MM-dd")
							.format(Calendar.getInstance().getTime());
					HttpRequestHelper.getAllAppointments(
							getActivity(),
							getActivity().getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, "")/*,
							""
									+ new SimpleDateFormat("yyyy-MM-dd")
											.format(date),*/,false,editTextFromDate.getText().toString().trim(),editTextToDate.getText().toString().trim());
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private OnTimeChangeListener onTimeChangeListener = new OnTimeChangeListener() {

		public void onTimeChange(Calendar time) {
			Config.LogError("Demo: ", "" + time.getTime());
			/* Log.e("Demo: ", "" + time.getTime()); */
			textViewMonthAndYear.setText(String.format(Locale.US, "%tB", time)
					+ " " + time.get(Calendar.YEAR));
			textViewAppointmentDate.setText(time.getDisplayName(
					Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)
					+ " "
					+ String.format(Locale.US, "%tB", time)
					+ " "
					+ time.get(Calendar.DATE) + "," + time.get(Calendar.YEAR));
			if (Utilities.getInstance(getActivity()).isNetAvailable()) {
				date = new SimpleDateFormat("yyyy-MM-dd")
						.format(time.getTime());
				/*HttpRequestHelper.getAllAppointments(
						getActivity(),
						getActivity().getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""),
						""
								+ new SimpleDateFormat("yyyy-MM-dd")
										.format(time.getTime()),false);*/
			} else {
				Toast.makeText(getActivity(), R.string.error_internet,
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	public void setTime(Calendar c) {
		/*mContainer.setTime(c);*/
	}

	public void addDataToListView() {
		if (!appointments_list.isEmpty()) {
			appoAdapter = new AppointmentsHomeAdapter(getActivity(),
					appointments_list);
			listViewAppointmentList.setAdapter(appoAdapter);
		}
	}

	private class AppointmentAction extends Dialog implements
			android.view.View.OnClickListener {
		private Appointments appo_item;
		private int possition;

		public AppointmentAction(Context context, Appointments data,
				int possition) {
			super(context);
			this.appo_item = data;
			this.possition = possition;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.appoinment_action_dialog);
			((TextView) findViewById(R.id.txtCancel)).setOnClickListener(this);
			((TextView) findViewById(R.id.txtConfirm)).setOnClickListener(this);
			((TextView) findViewById(R.id.txtReschedule))
					.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.txtCancel:
				HttpRequestHelper.updateAppoinment(
						getActivity(),
						getActivity().getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""),
						appo_item.getId(), "3", date, possition);
				dismiss();
				break;
			case R.id.txtConfirm:
				HttpRequestHelper.updateAppoinment(
						getActivity(),
						getActivity().getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""),
						appo_item.getId(), "2", date, possition);
				dismiss();
				break;
			case R.id.txtReschedule:
				new DateTimeDialog(getActivity(), possition, appo_item.getId())
				.show();
				dismiss();
				break;
			default:
				break;
			}
		}

	}

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
						getActivity(),
						getActivity().getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, ""), itemId, "4",
						dateTime.trim(), position);
				dismiss();

			}
		}

	}
	private DatePickerDialog.OnDateSetListener mDateSetListenerFromDate = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplayFromdate();

		}

	};

	private void updateDisplayFromdate() {
		if (editTextFromDate != null) {
			editTextFromDate.setText(new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay).append(" "));
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListenerToDate = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplayTodate();

		}

	};

	private void updateDisplayTodate() {
		if (editTextToDate != null) {
			editTextToDate.setText(new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay).append(" "));
		}
	}

}
