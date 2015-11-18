package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DeliveryAddressModel implements Parcelable{

	private String id;
	private String name;
	private String address1;
	private String address2;
	private String cityId;
	private String stateId;
	private String countryId;
	private String pinCode;
	private String phone;
	private String landMark;
	
	public DeliveryAddressModel(){
		
	}
	public DeliveryAddressModel(String id, String name, String address1,
			String address2, String cityId, String stateId, String countryId,
			String pinCode, String phone, String landMark) {
		super();
		this.id = id;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.cityId = cityId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.pinCode = pinCode;
		this.phone = phone;
		this.landMark = landMark;
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
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
		dest.writeString(address1);
		dest.writeString(address2);
		dest.writeString(cityId);
		dest.writeString(stateId);
		dest.writeString(countryId);
		dest.writeString(pinCode);
		dest.writeString(phone);
		dest.writeString(landMark);
		
	}
	public DeliveryAddressModel(Parcel source) {
		readFromParcel(source);
	}
	
	private void readFromParcel(Parcel source) {
		
		this.id = source.readString();
		this.name = source.readString();
		this.address1 = source.readString();
		this.address2 = source.readString();
		this.cityId = source.readString();
		this.stateId = source.readString();
		this.countryId = source.readString();
		this.pinCode = source.readString();
		this.phone = source.readString();
		this.landMark = source.readString();
		
		
		
	}

	public static final Parcelable.Creator<DeliveryAddressModel> CREATOR = new Creator<DeliveryAddressModel>() {

		@Override
		public DeliveryAddressModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeliveryAddressModel[size];
		}

		@Override
		public DeliveryAddressModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DeliveryAddressModel(source);
		}
	};
}
