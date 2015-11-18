package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointments implements Parcelable {
	String id;
	String doctor_id;
	String clinic_id;
	String patient_id;
	String datetime;
	String appointment_status_id;
	String added_by;
	String added_on;
	String modified_by;
	String modified_on;
	String appointment_status;
	String patient_name;
	String note;
	String gender;
	String lastVisit;
	String age;
	String patientPhone;
	String patientEmail;
	String photo;
	String patient_accountNo;
	String address;
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPatient_accountNo() {
		return patient_accountNo;
	}

	public void setPatient_accountNo(String patient_accountNo) {
		this.patient_accountNo = patient_accountNo;
	}

	public String getAge() {
		return age;
	}

	

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

	public Appointments(String id, String doctor_id, String clinic_id,
			String patient_id, String datetime, String appointment_status_id,
			String added_by, String added_on, String modified_by,
			String modified_on, String appointment_status, String patient_name,
			String note, String gender, String lastVisit, String age,
			String patientPhone, String patientEmail, String photo,
			String patient_accountNo, String address) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.clinic_id = clinic_id;
		this.patient_id = patient_id;
		this.datetime = datetime;
		this.appointment_status_id = appointment_status_id;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.appointment_status = appointment_status;
		this.patient_name = patient_name;
		this.note = note;
		this.gender = gender;
		this.lastVisit = lastVisit;
		this.age = age;
		this.patientPhone = patientPhone;
		this.patientEmail = patientEmail;
		this.photo = photo;
		this.patient_accountNo = patient_accountNo;
		this.address = address;
	}

	/*public Appointments(String id, String doctor_id, String clinic_id,
			String patient_id, String datetime, String appointment_status_id,
			String added_by, String added_on, String modified_by,
			String modified_on, String appointment_status, String patient_name ,String note,String gender,String lastVisit,String age) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.clinic_id = clinic_id;
		this.patient_id = patient_id;
		this.datetime = datetime;
		this.appointment_status_id = appointment_status_id;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.appointment_status = appointment_status;
		this.patient_name = patient_name;
		this.note = note;
		this.gender = gender;
		this.lastVisit = lastVisit;
		this.age = age;
	}*/
	
	/*public Appointments(String id, String doctor_id, String clinic_id,
			String patient_id, String datetime, String appointment_status_id,
			String added_by, String added_on, String modified_by,
			String modified_on) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.clinic_id = clinic_id;
		this.patient_id = patient_id;
		this.datetime = datetime;
		this.appointment_status_id = appointment_status_id;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getAppointment_status_id() {
		return appointment_status_id;
	}

	public void setAppointment_status_id(String appointment_status_id) {
		this.appointment_status_id = appointment_status_id;
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

	public String getAppointment_status() {
		return appointment_status;
	}

	public void setAppointment_status(String appointment_status) {
		this.appointment_status = appointment_status;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(doctor_id);
		dest.writeString(clinic_id);
		dest.writeString(patient_id);
		dest.writeString(datetime);
		dest.writeString(appointment_status_id);
		dest.writeString(added_by);
		dest.writeString(added_on);
		dest.writeString(modified_by);
		dest.writeString(modified_on);
		dest.writeString(appointment_status);
		dest.writeString(patient_name);
		dest.writeString(note);
		dest.writeString(lastVisit);
		dest.writeString(gender);
		dest.writeString(age);
		dest.writeString(patientPhone);
		dest.writeString(patientEmail);
		dest.writeString(patient_accountNo);
		dest.writeString(photo);
	}

	public Appointments(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.doctor_id = source.readString();
		this.clinic_id = source.readString();
		this.patient_id = source.readString();
		this.datetime = source.readString();
		this.appointment_status_id = source.readString();
		this.added_by = source.readString();
		this.added_on = source.readString();
		this.modified_by = source.readString();
		this.modified_on = source.readString();
		this.appointment_status = source.readString();
		this.patient_name = source.readString();
		this.note = source.readString();
		this.lastVisit = source.readString();
		this.gender = source.readString();
		this.age = source.readString();
		this.patientPhone = source.readString();
		this.patientEmail= source.readString();
		this.patient_accountNo = source.readString();
		this.photo= source.readString();
	}

	public static final Parcelable.Creator<Appointments> CREATOR = new Creator<Appointments>() {

		@Override
		public Appointments[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Appointments[size];
		}

		@Override
		public Appointments createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Appointments(source);
		}
	};

}
