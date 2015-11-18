package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AllergiMedicineModel implements Parcelable {

	String medicine_name;
	String medicine_id;

	public AllergiMedicineModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllergiMedicineModel(String medicine_name, String medicine_id) {
		super();
		this.medicine_name = medicine_name;
		this.medicine_id = medicine_id;
	}

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}

	public String getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}

	public static Parcelable.Creator<AllergiMedicineModel> getCreator() {
		return CREATOR;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(medicine_name);
		dest.writeString(medicine_id);

	}

	public AllergiMedicineModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.medicine_name = source.readString();
		this.medicine_id = source.readString();

	}

	public static final Parcelable.Creator<AllergiMedicineModel> CREATOR = new Creator<AllergiMedicineModel>() {

		@Override
		public AllergiMedicineModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new AllergiMedicineModel[size];
		}

		@Override
		public AllergiMedicineModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new AllergiMedicineModel(source);
		}
	};

}
