package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RecentPrescriptions implements Parcelable {
	String id;
	String patientname;
	String time;
	String date;

	public RecentPrescriptions(String id, String patientname, String time, String date) {
		super();
		this.id = id;
		this.patientname = patientname;
		this.time = time;
		this.date = date;
	}

	public RecentPrescriptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
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

	@Override
	public String toString() {
		return "Prescriptions [id=" + id + ", patientname=" + patientname
				+ ", time=" + time + ", date=" + date + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(patientname);
		dest.writeString(time);
		dest.writeString(date);

	}

	public RecentPrescriptions(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel in) {
		this.id = in.readString();
		this.patientname = in.readString();
		this.time = in.readString();
		this.date = in.readString();

	}

	public static final Parcelable.Creator<RecentPrescriptions> CREATOR = new Creator<RecentPrescriptions>() {

		@Override
		public RecentPrescriptions[] newArray(int size) {
			// TODO Auto-generated method stub
			return new RecentPrescriptions[size];
		}

		@Override
		public RecentPrescriptions createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new RecentPrescriptions(source);
		}
	};
}
