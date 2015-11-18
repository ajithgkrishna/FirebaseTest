package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnitsModel implements Parcelable {
	String id;
	String name;

	/**
	 * default constructor
	 */
	public UnitsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitsModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel in) {

		this.id = in.readString();
		this.name = in.readString();

	}

	public UnitsModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "UnitsModel [id=" + id + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Parcelable.Creator<UnitsModel> getCreator() {
		return CREATOR;
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

	public static final Parcelable.Creator<UnitsModel> CREATOR = new Creator<UnitsModel>() {

		@Override
		public UnitsModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new UnitsModel[size];
		}

		@Override
		public UnitsModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new UnitsModel(source);
		}
	};
}
