package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetAllCitiesModel implements Parcelable {
	String id;
	String state_id;
	String name;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(state_id);
		dest.writeString(name);

	}

	public GetAllCitiesModel(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public String toString() {
		return "GetAllCitiesModel [id=" + id + ", state_id=" + state_id
				+ ", name=" + name + "]";
	}

	public GetAllCitiesModel(String id, String state_id, String name) {
		super();
		this.id = id;
		this.state_id = state_id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Parcelable.Creator<GetAllCitiesModel> getCreator() {
		return CREATOR;
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.state_id = source.readString();
		this.name = source.readString();

	}

	public static final Parcelable.Creator<GetAllCitiesModel> CREATOR = new Creator<GetAllCitiesModel>() {

		@Override
		public GetAllCitiesModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new GetAllCitiesModel[size];
		}

		@Override
		public GetAllCitiesModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new GetAllCitiesModel(source);
		}
	};
}
