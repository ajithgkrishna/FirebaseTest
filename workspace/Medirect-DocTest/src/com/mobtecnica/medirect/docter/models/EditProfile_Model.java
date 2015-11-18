package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EditProfile_Model implements Parcelable {
	String id;
	String firstname;
	String lastname;
	String address1;
	String address2;
	String countryid;
	String cityid;
	String stateid;
	String gender;
	String dob;
	String image;
	String pincode;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getStateid() {
		return stateid;
	}

	public EditProfile_Model(String id, String firstname, String lastname,
			String address1, String address2, String countryid, String cityid,
			String stateid, String gender, String dob, String image,
			String pincode) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address1 = address1;
		this.address2 = address2;
		this.countryid = countryid;
		this.cityid = cityid;
		this.stateid = stateid;
		this.gender = gender;
		this.dob = dob;
		this.image = image;
		this.pincode = pincode;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public EditProfile_Model(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();

		this.firstname = source.readString();
		this.lastname = source.readString();
		this.address1 = source.readString();
		this.address2 = source.readString();
		this.cityid = source.readString();
		this.countryid = source.readString();
		this.dob = source.readString();
		this.gender = source.readString();
		this.stateid = source.readString();
		this.image = source.readString();
		this.pincode = source.readString();

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public EditProfile_Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

		dest.writeString(id);
		dest.writeString(address1);
		dest.writeString(firstname);
		dest.writeString(lastname);
		dest.writeString(address2);
		dest.writeString(cityid);
		dest.writeString(countryid);
		dest.writeString(stateid);
		dest.writeString(dob);
		dest.writeString(gender);
		dest.writeString(image);
		dest.writeString(pincode);
	}

	@Override
	public String toString() {
		return "EditDoctorProfile [id=" + id + ", first_name=" + firstname
				+ ", last_name=" + lastname + ", address2=" + address2
				+ ", address1=" + address1 + ", country_id=" + countryid
				+ ", state_id=" + stateid + ", dob=" + dob + ", city_id="
				+ cityid + ", gender=" + gender + "]";
	}

}
