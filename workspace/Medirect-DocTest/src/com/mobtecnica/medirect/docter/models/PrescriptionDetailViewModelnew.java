package com.mobtecnica.medirect.docter.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PrescriptionDetailViewModelnew implements Parcelable {

	String id;
	String doctor_id;
	String patient_id;
	String prescription_type_id;
	String symptoms;
	String diagnosis;
	String chronic;
	String expiry_date;
	String added_by;
	String added_on;
	String modified_by;
	String modified_on;
	String prescription_status_id;
	String doctor_name;
	ArrayList<MedicineModelinPrescriptionDetail> medicinesArray = new ArrayList<MedicineModelinPrescriptionDetail>();
	ArrayList<DiagnosticModel> diagnosticArray = new ArrayList<DiagnosticModel>();

	public PrescriptionDetailViewModelnew() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getPrescription_type_id() {
		return prescription_type_id;
	}

	public void setPrescription_type_id(String prescription_type_id) {
		this.prescription_type_id = prescription_type_id;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getChronic() {
		return chronic;
	}

	public void setChronic(String chronic) {
		this.chronic = chronic;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
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

	public String getPrescription_status_id() {
		return prescription_status_id;
	}

	public void setPrescription_status_id(String prescription_status_id) {
		this.prescription_status_id = prescription_status_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public ArrayList<MedicineModelinPrescriptionDetail> getMedicinesArray() {
		return medicinesArray;
	}

	public void setMedicinesArray(ArrayList<MedicineModelinPrescriptionDetail> medicinesArray) {
		this.medicinesArray = medicinesArray;
	}

	public ArrayList<DiagnosticModel> getDiagnosticArray() {
		return diagnosticArray;
	}

	public void setDiagnosticArray(ArrayList<DiagnosticModel> diagnosticArray) {
		this.diagnosticArray = diagnosticArray;
	}

	public PrescriptionDetailViewModelnew(String id, String doctor_id,
			String patient_id, String prescription_type_id, String symptoms,
			String diagnosis, String chronic, String expiry_date,
			String added_by, String added_on, String modified_by,
			String modified_on, String prescription_status_id,
			String doctor_name, ArrayList<MedicineModelinPrescriptionDetail> medicinesArray,
			ArrayList<DiagnosticModel> diagnosticArray) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.prescription_type_id = prescription_type_id;
		this.symptoms = symptoms;
		this.diagnosis = diagnosis;
		this.chronic = chronic;
		this.expiry_date = expiry_date;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.prescription_status_id = prescription_status_id;
		this.doctor_name = doctor_name;
		this.medicinesArray = medicinesArray;
		this.diagnosticArray = diagnosticArray;
	}

	public static Parcelable.Creator<PrescriptionDetailViewModelnew> getCreator() {
		return CREATOR;
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
		dest.writeString(patient_id);
		dest.writeString(prescription_type_id);
		dest.writeString(symptoms);
		dest.writeString(diagnosis);
		dest.writeString(chronic);
		dest.writeString(expiry_date);
		dest.writeString(added_by);
		dest.writeString(added_on);
		dest.writeString(modified_by);
		dest.writeString(modified_on);
		dest.writeString(prescription_status_id);
		dest.writeString(doctor_name);
		dest.writeTypedList(medicinesArray);
		dest.writeTypedList(diagnosticArray);
		
	}

	public PrescriptionDetailViewModelnew(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.doctor_id = source.readString();
		this.patient_id = source.readString();
		this.prescription_type_id = source.readString();
		this.symptoms = source.readString();
		this.diagnosis = source.readString();
		this.chronic = source.readString();
		this.expiry_date = source.readString();
		this.added_by = source.readString();
		this.added_on = source.readString();
		this.modified_by = source.readString();
		this.modified_on = source.readString();
		this.prescription_status_id = source.readString();
		this.doctor_name = source.readString();
		source.readTypedList(medicinesArray, MedicineModelinPrescriptionDetail.CREATOR);
		source.readTypedList(diagnosticArray, DiagnosticModel.CREATOR);
		

	}

	public static final Parcelable.Creator<PrescriptionDetailViewModelnew> CREATOR = new Creator<PrescriptionDetailViewModelnew>() {

		@Override
		public PrescriptionDetailViewModelnew[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PrescriptionDetailViewModelnew[size];
		}

		@Override
		public PrescriptionDetailViewModelnew createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PrescriptionDetailViewModelnew(source);
		}
	};

}
