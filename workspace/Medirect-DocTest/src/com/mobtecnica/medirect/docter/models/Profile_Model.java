package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile_Model implements Parcelable {
	String id;
	String account_no;
	String first_name;
	String last_name;
	String address;
	String email;
	String phone;
	String registration_date;
	String dob;
	String gender;
	String medical_council;
	String registration_number;
	String qualification;
	String university;
	String photo;
	String credit;
	String user_status_id;
	String added_by;
	String added_on;
	String modified_by;
	String modified_on;
	String user_type_id;
	String address1;
	String address2;
	String country_name;
	String state_name;
	String city_name;
	String country_id;
	String state_id;
	String city_id;
	String pincode;
	String age;
	String lastVisit;

	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	/*****
	 * This constructor is used in HomePage fragment for patient lists...
	 * @param id
	 * @param account_no
	 * @param first_name
	 * @param last_name
	 * @param address
	 * @param email
	 * @param phone
	 * @param gender
	 * @param age
	 */
	public Profile_Model(String id, String account_no, String first_name,
			String last_name, String address, String email, String phone,
			String gender, String age,String lastVisit,String photo) {
		super();
		this.id = id;
		this.account_no = account_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.lastVisit = lastVisit;
		this.photo = photo;
	}

	public Profile_Model(String id, String account_no, String first_name,
			String last_name, String address, String email, String phone,
			String registration_date, String dob, String gender,
			String medical_council, String registration_number,
			String qualification, String university, String photo,
			String credit, String user_status_id, String added_by,
			String added_on, String modified_by, String modified_on,
			String user_type_id, String address1, String address2,
			String country_name, String state_name, String city_name,
			String country_id, String state_id, String city_id , String pincode,String age) {
		super();
		this.id = id;
		this.account_no = account_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.registration_date = registration_date;
		this.dob = dob;
		this.gender = gender;
		this.medical_council = medical_council;
		this.registration_number = registration_number;
		this.qualification = qualification;
		this.university = university;
		this.photo = photo;
		this.credit = credit;
		this.user_status_id = user_status_id;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.user_type_id = user_type_id;
		this.address1 = address1;
		this.address2 = address2;
		this.country_name = country_name;
		this.state_name = state_name;
		this.city_name = city_name;
		this.country_id = country_id;
		this.state_id = state_id;
		this.city_id = city_id;
		this.pincode = pincode;
		this.age = age;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public static Parcelable.Creator<Profile_Model> getCreator() {
		return CREATOR;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(account_no);
		dest.writeString(first_name);
		dest.writeString(last_name);
		dest.writeString(address);
		dest.writeString(email);
		dest.writeString(phone);
		dest.writeString(registration_date);
		dest.writeString(dob);
		dest.writeString(gender);
		dest.writeString(medical_council);
		dest.writeString(registration_number);
		dest.writeString(qualification);
		dest.writeString(university);
		dest.writeString(photo);
		dest.writeString(credit);
		dest.writeString(user_status_id);
		dest.writeString(added_by);
		dest.writeString(added_on);
		dest.writeString(modified_by);
		dest.writeString(modified_on);
		dest.writeString(user_type_id);
		dest.writeString(address1);
		dest.writeString(address2);
		dest.writeString(country_name);
		dest.writeString(city_name);
		dest.writeString(state_name);
		dest.writeString(country_id);
		dest.writeString(state_id);
		dest.writeString(city_id);
		dest.writeString(pincode);
		dest.writeString(age);
		dest.writeString(lastVisit);

	}

	public Profile_Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Profile_Model(String id, String account_no, String first_name,
			String last_name, String address, String email, String phone,
			String registration_date, String dob, String gender,
			String medical_council, String registration_number,
			String qualification, String university, String photo,
			String credit, String user_status_id, String added_by,
			String added_on, String modified_by, String modified_on,
			String user_type_id,String age ) {
		super();
		this.id = id;
		this.account_no = account_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.registration_date = registration_date;
		this.dob = dob;
		this.gender = gender;
		this.medical_council = medical_council;
		this.registration_number = registration_number;
		this.qualification = qualification;
		this.university = university;
		this.photo = photo;
		this.credit = credit;
		this.user_status_id = user_status_id;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.user_type_id = user_type_id;
		this.age = age;
		this.lastVisit = lastVisit;
	}
	
	public Profile_Model(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.account_no = source.readString();
		this.first_name = source.readString();
		this.last_name = source.readString();
		this.address = source.readString();
		this.email = source.readString();
		this.phone = source.readString();
		this.registration_date = source.readString();
		this.dob = source.readString();
		this.gender = source.readString();
		this.medical_council = source.readString();
		this.registration_number = source.readString();
		this.qualification = source.readString();
		this.university = source.readString();
		this.photo = source.readString();
		this.credit = source.readString();
		this.user_status_id = source.readString();
		this.added_by = source.readString();
		this.added_on = source.readString();
		this.modified_by = source.readString();
		this.modified_on = source.readString();
		this.user_type_id = source.readString();
		this.address1 = source.readString();
		this.address2 = source.readString();
		this.country_name = source.readString();
		this.city_name = source.readString();
		this.state_name = source.readString();
		this.country_id = source.readString();
		this.city_id = source.readString();
		this.state_id = source.readString();
		this.pincode = source.readString();
		this.age = source.readString();
		this.lastVisit = source.readString();
	}

	@Override
	public String toString() {
		return "DoctorProfile [id=" + id + ", account_no=" + account_no
				+ ", first_name=" + first_name + ", last_name=" + last_name
				+ ", address=" + address + ", email=" + email + ", phone="
				+ phone + ", registration_date=" + registration_date + ", dob="
				+ dob + ", gender=" + gender + ", medical_council="
				+ medical_council + ", registration_number="
				+ registration_number + ", qualification=" + qualification
				+ ", university=" + university + ", photo=" + photo
				+ ", credit=" + credit + ", user_status_id=" + user_status_id
				+ ", added_by=" + added_by + ", added_on=" + added_on
				+ ", modified_by=" + modified_by + ", modified_on="
				+ modified_on + ", user_type_id=" + user_type_id + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
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

	public String getMedical_council() {
		return medical_council;
	}

	public void setMedical_council(String medical_council) {
		this.medical_council = medical_council;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getUser_status_id() {
		return user_status_id;
	}

	public void setUser_status_id(String user_status_id) {
		this.user_status_id = user_status_id;
	}

	public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	public String getAdded_on() {
		return added_on;
	}

	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(String user_type_id) {
		this.user_type_id = user_type_id;
	}

	public static final Parcelable.Creator<Profile_Model> CREATOR = new Creator<Profile_Model>() {

		@Override
		public Profile_Model[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Profile_Model[size];
		}

		@Override
		public Profile_Model createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Profile_Model(source);
		}
	};
}
