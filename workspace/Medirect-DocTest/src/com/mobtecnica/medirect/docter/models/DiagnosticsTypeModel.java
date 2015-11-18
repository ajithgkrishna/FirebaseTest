package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DiagnosticsTypeModel implements Parcelable {
	private String id;
	private String name;

	public DiagnosticsTypeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiagnosticsTypeModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public static Parcelable.Creator<DiagnosticsTypeModel> getCreator() {
		return CREATOR;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(name);

	}

	public DiagnosticsTypeModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.name = source.readString();

	}

	public static final Parcelable.Creator<DiagnosticsTypeModel> CREATOR = new Creator<DiagnosticsTypeModel>() {

		@Override
		public DiagnosticsTypeModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DiagnosticsTypeModel[size];
		}

		@Override
		public DiagnosticsTypeModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DiagnosticsTypeModel(source);
		}
	};
}
