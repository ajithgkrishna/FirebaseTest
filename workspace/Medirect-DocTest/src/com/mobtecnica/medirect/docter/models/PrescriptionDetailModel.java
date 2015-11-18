package com.mobtecnica.medirect.docter.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PrescriptionDetailModel implements Parcelable {
	String id;
	String doctor_id;
	String patient_id;
	String prescription_type_id;
	String chronic;
	String expiry_date;
	String added_by;
	String added_on;
	String modified_by;
	String modified_on;
	String prescription_status_id;
	String doctor_name;
	ArrayList<MedicinsModel> medicinsModel;

	public PrescriptionDetailModel(String id, String doctor_id,
			String patient_id, String prescription_type_id, String chronic,
			String expiry_date, String added_by, String added_on,
			String modified_by, String modified_on,
			String prescription_status_id, String doctor_name,
			ArrayList<MedicinsModel> medicinsModel) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.prescription_type_id = prescription_type_id;
		this.chronic = chronic;
		this.expiry_date = expiry_date;
		this.added_by = added_by;
		this.added_on = added_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.prescription_status_id = prescription_status_id;
		this.doctor_name = doctor_name;
		this.medicinsModel = medicinsModel;
	}

	public PrescriptionDetailModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.doctor_id = source.readString();
		this.patient_id = source.readString();
		this.prescription_type_id = source.readString();
		this.chronic = source.readString();
		this.expiry_date = source.readString();
		this.added_by = source.readString();
		this.added_on = source.readString();
		this.modified_by = source.readString();
		this.modified_on = source.readString();
		this.prescription_status_id = source.readString();
		this.doctor_name = source.readString();
		source.readTypedList(medicinsModel, MedicinsModel.CREATOR);

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

	public ArrayList<MedicinsModel> getMedicinsModel() {
		return medicinsModel;
	}

	public void setMedicinsModel(ArrayList<MedicinsModel> medicinsModel) {
		this.medicinsModel = medicinsModel;
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
		dest.writeString(chronic);
		dest.writeString(expiry_date);
		dest.writeString(added_by);
		dest.writeString(added_on);
		dest.writeString(modified_by);
		dest.writeString(modified_on);
		dest.writeString(prescription_status_id);
		dest.writeString(doctor_name);
		dest.writeTypedList(medicinsModel);
	}

	public static final Parcelable.Creator<PrescriptionDetailModel> CREATOR = new Creator<PrescriptionDetailModel>() {

		@Override
		public PrescriptionDetailModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PrescriptionDetailModel[size];
		}

		@Override
		public PrescriptionDetailModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PrescriptionDetailModel(source);
		}
	};
}
