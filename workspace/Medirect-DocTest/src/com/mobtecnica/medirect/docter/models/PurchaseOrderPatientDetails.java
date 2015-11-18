package com.mobtecnica.medirect.docter.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PurchaseOrderPatientDetails implements Parcelable {

	private String id;
	private String name;
	private String address;
	private String phone;

	
	
	public PurchaseOrderPatientDetails(String id, String name, String address,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		dest.writeString(address);
		dest.writeString(phone);
	}
	public PurchaseOrderPatientDetails(Parcel source) {
		readFromParcel(source);
	}
	
	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.name = source.readString();
		this.address = source.readString();
		this.phone = source.readString();
		
	}
	
	public static final Parcelable.Creator<PurchaseOrderPatientDetails> CREATOR = new Creator<PurchaseOrderPatientDetails>() {

		@Override
		public PurchaseOrderPatientDetails[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PurchaseOrderPatientDetails[size];
		}

		@Override
		public PurchaseOrderPatientDetails createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PurchaseOrderPatientDetails(source);
		}
	};

}
