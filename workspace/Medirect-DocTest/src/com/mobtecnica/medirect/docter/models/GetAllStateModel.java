package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetAllStateModel implements Parcelable {
	String id;
	String country_id;
	String name;

	public GetAllStateModel(String id, String country_id, String name) {
		super();
		this.id = id;
		this.country_id = country_id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GetAllStateModel [id=" + id + ", country_id=" + country_id
				+ ", name=" + name + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(country_id);
		dest.writeString(name);

	}

	public GetAllStateModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.country_id = source.readString();
		this.name = source.readString();

	}

	public static final Parcelable.Creator<GetAllStateModel> CREATOR = new Creator<GetAllStateModel>() {

		@Override
		public GetAllStateModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new GetAllStateModel[size];
		}

		@Override
		public GetAllStateModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new GetAllStateModel(source);
		}
	};
}
