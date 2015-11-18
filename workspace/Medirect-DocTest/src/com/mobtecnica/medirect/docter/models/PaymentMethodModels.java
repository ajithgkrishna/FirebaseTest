package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PaymentMethodModels implements Parcelable {
	
	private String id;
	private String name;
	private String statusId;
	 
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public PaymentMethodModels() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentMethodModels(String id, String name, String statusId) {
		super();
		this.id = id;
		this.name = name;
		this.statusId = statusId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
		dest.writeString(name);
		dest.writeString(statusId);
	}
	
	public void readFromParcel(Parcel in) {

		// this.propertyId = in.readString();
		this.id = in.readString();
		this.name = in.readString();
		this.statusId = in.readString();
	}
	
	public PaymentMethodModels(Parcel source) {
		super();
		readFromParcel(source);
	}

	
	public static final Parcelable.Creator<PaymentMethodModels> CREATOR = new Creator<PaymentMethodModels>() {

		@Override
		public PaymentMethodModels[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PaymentMethodModels[size];
		}

		@Override
		public PaymentMethodModels createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PaymentMethodModels(source);
		}
	};

}
