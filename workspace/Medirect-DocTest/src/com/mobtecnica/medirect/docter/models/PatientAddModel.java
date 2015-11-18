package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PatientAddModel implements Parcelable {
	String id;
	String first_name;
	String imageFile;
	String last_name;
	String password;
	String email;
	String phone;
	String address1;
	String address2;
	String city_id;
	String state_id;
	String country_id;
	String pincode;
	String dob;
	String gender;

	/**
	 * 
	 * @param patientId
	 * @param patientname
	 * @param condition
	 * @param time
	 * @param date
	 * @param place
	 */

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PatientAddModel(String id, String first_name, String imageFile,
			String last_name, String password, String email, String phone,
			String address1, String address2, String city_id, String state_id,
			String country_id, String pincode, String dob, String gender) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.imageFile = imageFile;
		this.last_name = last_name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.city_id = city_id;
		this.state_id = state_id;
		this.country_id = country_id;
		this.pincode = pincode;
		this.dob = dob;
		this.gender = gender;
	}

	public PatientAddModel(String first_name, String imageFile, String last_name,
			String password, String email, String phone, String address1,
			String address2, String city_id, String state_id,
			String country_id, String pincode, String dob, String gender) {
		super();
		this.first_name = first_name;
		this.imageFile = imageFile;
		this.last_name = last_name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.city_id = city_id;
		this.state_id = state_id;
		this.country_id = country_id;
		this.pincode = pincode;
		this.dob = dob;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static Parcelable.Creator<PatientAddModel> getCreator() {
		return CREATOR;
	}

	public void readFromParcel(Parcel in) {

		// this.propertyId = in.readString();
		this.id = in.readString();
		this.first_name = in.readString();
		this.imageFile = in.readString();
		this.last_name = in.readString();
		this.password = in.readString();
		this.phone = in.readString();
		this.address1 = in.readString();
		this.address2 = in.readString();
		this.city_id = in.readString();
		this.state_id = in.readString();
		this.country_id = in.readString();
		this.pincode = in.readString();
		this.dob = in.readString();
		this.gender = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(first_name);
		dest.writeString(imageFile);
		dest.writeString(last_name);
		dest.writeString(password);
		dest.writeString(phone);
		dest.writeString(address1);
		dest.writeString(address2);
		dest.writeString(city_id);
		dest.writeString(state_id);
		dest.writeString(country_id);
		dest.writeString(pincode);
		dest.writeString(dob);
		dest.writeString(gender);
	}

	public PatientAddModel(Parcel source) {
		super();
		readFromParcel(source);
	}

	public static final Parcelable.Creator<PatientAddModel> CREATOR = new Creator<PatientAddModel>() {

		@Override
		public PatientAddModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PatientAddModel[size];
		}

		@Override
		public PatientAddModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PatientAddModel(source);
		}
	};
}
