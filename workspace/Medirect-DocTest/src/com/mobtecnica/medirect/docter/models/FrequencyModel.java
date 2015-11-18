package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FrequencyModel implements Parcelable {
	private String id;
	private String name;
	
	public FrequencyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FrequencyModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public void readFromParcel(Parcel in) {

		// this.propertyId = in.readString();
		this.id = in.readString();
		this.name = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);

	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public FrequencyModel(Parcel source) {
		super();
		readFromParcel(source);
	}
	public static final Parcelable.Creator<FrequencyModel> CREATOR = new Creator<FrequencyModel>() {

		@Override
		public FrequencyModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new FrequencyModel[size];
		}

		@Override
		public FrequencyModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new FrequencyModel(source);
		}
	};

}
