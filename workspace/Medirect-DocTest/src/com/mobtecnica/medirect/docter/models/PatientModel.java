package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PatientModel implements Parcelable {
	String patientId;
	String patientname;
	String condition;
	String time;
	String date;
	String place;

	/**
	 * default constructor
	 */
	public PatientModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param patientId
	 * @param patientname
	 * @param condition
	 * @param time
	 * @param date
	 * @param place
	 */
	public PatientModel(String patientId, String patientname, String condition,
			String time, String date, String place) {
		super();
		this.patientId = patientId;
		this.patientname = patientname;
		this.condition = condition;
		this.time = time;
		this.date = date;
		this.place = place;
	}

	/**
	 * 
	 * @param patientname
	 * @param condition
	 * @param time
	 * @param date
	 * @param place
	 */
	public PatientModel(String patientname, String condition, String time,
			String date, String place) {
		super();
		this.patientname = patientname;
		this.condition = condition;
		this.time = time;
		this.date = date;
		this.place = place;
	}

	public PatientModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel in) {

		this.patientId = in.readString();
		this.patientname = in.readString();
		this.condition = in.readString();
		this.time = in.readString();
		this.date = in.readString();
		this.place = in.readString();

	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "PatientModel [patientId=" + patientId + ", patientname="
				+ patientname + ", condition=" + condition + ", time=" + time
				+ ", date=" + date + ", place=" + place + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	public static final Parcelable.Creator<PatientModel> CREATOR = new Creator<PatientModel>() {

		@Override
		public PatientModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PatientModel[size];
		}

		@Override
		public PatientModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PatientModel(source);
		}
	};
}
